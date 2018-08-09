
package homeworkapp.instigatemobile.com.interviewapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Picture {

    @SerializedName("large")
    @Expose
    private final String large;
    @SerializedName("medium")
    @Expose
    private final String medium;
    @SerializedName("thumbnail")
    @Expose
    private final String thumbnail;

    public Picture(String large, String medium, String thumbnail) {
        this.large = large;
        this.medium = medium;
        this.thumbnail = thumbnail;
    }

    public final String getLarge() {
        return large;
    }

    public String getMedium() {
        return medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }


}
