package com.techone.cryptoconverter;

import com.google.gson.annotations.SerializedName;


public class ConverterModel {
    @SerializedName("time")
    private String time;
    @SerializedName("asset_id_base")
    private String asset_id_base;
    @SerializedName("asset_id_quote")
    private String asset_id_quote;
    @SerializedName("rate")
    private double rate;

    public ConverterModel(){

    }

    public ConverterModel(double rate, String time, String asset_id_base, String asset_id_quote) {
        this.rate = rate;
        this.time = time;
        this.asset_id_base = asset_id_base;
        this.asset_id_quote = asset_id_quote;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAsset_id_base() {
        return asset_id_base;
    }

    public void setAsset_id_base(String asset_id_base) {
        this.asset_id_base = asset_id_base;
    }

    public String getAsset_id_quote() {
        return asset_id_quote;
    }

    public void setAsset_id_quote(String asset_id_quote) {
        this.asset_id_quote = asset_id_quote;
    }


    public Double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
