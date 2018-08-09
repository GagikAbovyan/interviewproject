
package homeworkapp.instigatemobile.com.interviewapplication.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("uuid")
    @Expose
    private final String uuid;
    @SerializedName("username")
    @Expose
    private final String username;
    @SerializedName("password")
    @Expose
    private final String password;
    @SerializedName("salt")
    @Expose
    private final String salt;
    @SerializedName("md5")
    @Expose
    private final String md5;
    @SerializedName("sha1")
    @Expose
    private final String sha1;
    @SerializedName("sha256")
    @Expose
    private final String sha256;

    public String getUuid() {
        return uuid;
    }


    public Login(String uuid, String username, String password, String salt, String md5, String sha1, String sha256) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.md5 = md5;
        this.sha1 = sha1;
        this.sha256 = sha256;
    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    public String getSalt() {
        return salt;
    }

    public String getMd5() {
        return md5;
    }


    public String getSha1() {
        return sha1;
    }


    public String getSha256() {
        return sha256;
    }


}
