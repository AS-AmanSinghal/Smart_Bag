
package com.smartbagaman.smartbag.Response.CheckResponse;

import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("created")
    private String mCreated;
    @SerializedName("id")
    private String mId;
    @SerializedName("location")
    private String mLocation;
    @SerializedName("name")
    private String mName;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("time")
    private String mTime;

    public String getCreated() {
        return mCreated;
    }

    public void setCreated(String created) {
        mCreated = created;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

}
