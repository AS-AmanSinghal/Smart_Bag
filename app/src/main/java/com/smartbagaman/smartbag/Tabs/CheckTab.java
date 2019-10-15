package com.smartbagaman.smartbag.Tabs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.smartbagaman.smartbag.Adapters.CheckTabAdapter;
import com.smartbagaman.smartbag.MainActivity;
import com.smartbagaman.smartbag.Models.AppInterface;
import com.smartbagaman.smartbag.R;
import com.smartbagaman.smartbag.Response.CheckResponse.Datum;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckTab extends Fragment
{
    Button button;
    private RecyclerView recyclerView;
    private CheckTabAdapter adapter;
    private List<Datum> itemList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_checktab, container, false);
        button=root.findViewById(R.id.refresh);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getContext(), MainActivity.class));
                getActivity().finish();
            }
        });
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.tab_check_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),RecyclerView.VERTICAL,false));
        getItem();
        adapter=new CheckTabAdapter(getContext(),itemList);
        recyclerView.setAdapter(adapter);
    }

    private void getItem()
    {
        Retrofit retrofit=new Retrofit
                .Builder()
                .baseUrl(AppInterface.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        AppInterface api=retrofit.create(AppInterface.class);
        Call<JsonObject> call=api.items("aman");
        call.enqueue(new Callback<JsonObject>()
        {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response)
            {
                if (response.body()!=null)
                {
                    JsonObject jsonObject=response.body();
                    JSONObject jsonObject1=null;
                    try {
                        jsonObject1= new JSONObject(jsonObject.toString());
                        JSONArray jsonArray=jsonObject1.getJSONArray("data");
                        for(int i=0;i<jsonArray.length();i++)
                        {
                            Datum datum=new Datum();
                            JSONObject jsonObject2=jsonArray.getJSONObject(i);
                            datum.setName(jsonObject2.optString("name"));
                            datum.setTime(jsonObject2.optString("time"));
                            datum.setLocation(jsonObject2.optString("location"));
                            itemList.add(datum);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    adapter=new CheckTabAdapter(getContext(),itemList);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t)
            {
                Toast.makeText(getContext(),"Network Connection Failed....",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
