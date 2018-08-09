
package homeworkapp.instigatemobile.com.interviewapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Dob {

    @SerializedName("date")
    @Expose
    private final String date;
    @SerializedName("age")
    @Expose
    private final Integer age;

    public Dob(String date, Integer age) {
        this.date = date;
        this.age = age;
    }

    public final String getDate() {
        return date;
    }



    public Integer getAge() {
        return age;
    }



}
