
package homeworkapp.instigatemobile.com.interviewapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registered {

    @SerializedName("date")
    @Expose
    private final String date;
    @SerializedName("age")
    @Expose
    private final Integer age;

    public Registered(String date, Integer age) {
        this.date = date;
        this.age = age;
    }

    public String getDate() {
        return date;
    }

    public Integer getAge() {
        return age;
    }


}
