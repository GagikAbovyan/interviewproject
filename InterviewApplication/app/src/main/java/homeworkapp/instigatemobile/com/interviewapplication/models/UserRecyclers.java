
package homeworkapp.instigatemobile.com.interviewapplication.models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserRecyclers {

    @SerializedName("results")
    @Expose
    private final List<Result> results = null;
    @SerializedName("info")
    @Expose
    private final Info info;

    public UserRecyclers(Info info) {
        this.info = info;
    }

    public List<Result> getResults() {
        return results;
    }


    public Info getInfo() {
        return info;
    }


}
