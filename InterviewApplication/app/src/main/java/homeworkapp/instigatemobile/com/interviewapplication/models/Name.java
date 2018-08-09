
package homeworkapp.instigatemobile.com.interviewapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("title")
    @Expose
    private final String title;
    @SerializedName("first")
    @Expose
    private final String first;
    @SerializedName("last")
    @Expose
    private final String last;

    public Name(String title, String first, String last) {
        this.title = title;
        this.first = first;
        this.last = last;
    }

    public String getTitle() {
        return title;
    }


    public String getFirst() {
        return first;
    }


    public String getLast() {
        return last;
    }


}
