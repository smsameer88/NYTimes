package com.util;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PojoResults implements Parcelable {

    @SerializedName("url")
    private String url;
    @SerializedName("adx_keywords")
    private String adx_keywords;
    @SerializedName("column")
    private String column;
    @SerializedName("section")
    private String section;
    @SerializedName("byline")
    private String byline;
    @SerializedName("type")
    private String type;
    @SerializedName("title")
    private String title;
    @SerializedName("abstract")
    private String abstract1;
    @SerializedName("published_date")
    private String published_date;
    @SerializedName("source")
    private String source;
    @SerializedName("id")
    private String id;
    @SerializedName("asset_id")
    private String asset_id;
    @SerializedName("views")
    private int views;
    private String brandLogoURL;

    public String getBrandLogoURL() {
        return brandLogoURL;
    }

    public void setBrandLogoURL(String brandLogoURL) {
        this.brandLogoURL = brandLogoURL;
    }

    public String getUrl() {
        return url;
    }

    public String getAdx_keywords() {
        return adx_keywords;
    }

    public String getColumn() {
        return column;
    }

    public String getSection() {
        return section;
    }

    public String getByline() {
        return byline;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getAbstract1() {
        return abstract1;
    }

    public String getPublished_date() {
        return published_date;
    }

    public String getSource() {
        return source;
    }

    public String getId() {
        return id;
    }

    public String getAsset_id() {
        return asset_id;
    }

    public int getViews() {
        return views;
    }

    protected PojoResults(Parcel in) {
        url = in.readString();
        adx_keywords = in.readString();
        column = in.readString();
        section = in.readString();
        byline = in.readString();
        type = in.readString();
        title = in.readString();
        abstract1 = in.readString();
        published_date = in.readString();
        source = in.readString();
        id = in.readString();
        asset_id = in.readString();
        views = in.readInt();
    }

    public static final Creator<PojoResults> CREATOR = new Creator<PojoResults>() {
        @Override
        public PojoResults createFromParcel(Parcel in) {
            return new PojoResults(in);
        }

        @Override
        public PojoResults[] newArray(int size) {
            return new PojoResults[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
        parcel.writeString(adx_keywords);
        parcel.writeString(column);
        parcel.writeString(section);
        parcel.writeString(byline);
        parcel.writeString(type);
        parcel.writeString(title);
        parcel.writeString(abstract1);
        parcel.writeString(published_date);
        parcel.writeString(source);
        parcel.writeString(id);
        parcel.writeString(asset_id);
        parcel.writeInt(views);
    }
}
