
package homeworkapp.instigatemobile.com.interviewapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    @SerializedName("street")
    @Expose
    private final String street;
    @SerializedName("city")
    @Expose
    private final String city;
    @SerializedName("state")
    @Expose
    private final String state;
    @SerializedName("postcode")
    @Expose
    private final Integer postcode;
    @SerializedName("coordinates")
    @Expose
    private final Coordinates coordinates;
    @SerializedName("timezone")
    @Expose
    private final Timezone timezone;

    public Location(String street, String city, String state, Integer postcode, Coordinates coordinates, Timezone timezone) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.postcode = postcode;
        this.coordinates = coordinates;
        this.timezone = timezone;
    }

    public String getStreet() {
        return street;
    }


    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }


    public Timezone getTimezone() {
        return timezone;
    }


}
