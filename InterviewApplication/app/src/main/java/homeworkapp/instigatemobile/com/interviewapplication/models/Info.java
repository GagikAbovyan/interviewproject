
package homeworkapp.instigatemobile.com.interviewapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info {

    @SerializedName("seed")
    @Expose
    private final String seed;
    @SerializedName("results")
    @Expose
    private final Integer results;
    @SerializedName("page")
    @Expose
    private final Integer page;
    @SerializedName("version")
    @Expose
    private final String version;

    public Info(final String seed, Integer results, final Integer page, final String version) {
        this.seed = seed;
        this.results = results;
        this.page = page;
        this.version = version;
    }

    public String getSeed() {
        return seed;
    }

    public Integer getResults() {
        return results;
    }

    public Integer getPage() {
        return page;
    }


    public String getVersion() {
        return version;
    }


}
