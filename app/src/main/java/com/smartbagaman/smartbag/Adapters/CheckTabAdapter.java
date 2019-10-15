package com.smartbagaman.smartbag.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.smartbagaman.smartbag.R;
import com.smartbagaman.smartbag.Response.CheckResponse.Datum;

import java.util.List;

public class CheckTabAdapter extends RecyclerView.Adapter<CheckTabAdapter.ViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Datum> itemlist;


    public CheckTabAdapter(Context applicationContext, List<Datum> itemList) {
        this.context=applicationContext;
        this.layoutInflater=LayoutInflater.from(context);
        this.itemlist=itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.card, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i)
    {
        holder.tabname.setText(itemlist.get(i).getName());
        holder.tabtime.setText(itemlist.get(i).getTime());
        //final String Aman="https://www.google.com/maps/place/Nari+Semari+Devi+Temple/@27.6694282,77.5361556,17z/data=!3m1!4b1!4m5!3m4!1s0x397314ea1b37fbbb:0x6994493ccb82d5f4!8m2!3d27.6694282!4d77.5383443";
        final String location=itemlist.get(i).getLocation().toString();
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (!location.equals(""))
                {
                    String uri=location;
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    context.startActivity(intent);
                }
                else
                {
                    Toast.makeText(context,"No Location Found...",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tabname,tabtime;
        Button button;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tabname=itemView.findViewById(R.id.tab_name);
            tabtime=itemView.findViewById(R.id.tab_time);
            button=itemView.findViewById(R.id.location);
        }
    }
}
