
package homeworkapp.instigatemobile.com.interviewapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Id {

    @SerializedName("name")
    @Expose
    private final String name;
    @SerializedName("value")
    @Expose
    private final String value;

    public Id(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }


}
