package homeworkapp.instigatemobile.com.interviewapplication.providers;

import java.util.ArrayList;
import java.util.List;

import homeworkapp.instigatemobile.com.interviewapplication.models.Result;

public class DataProvider {

    private final static List<Result> list = new ArrayList();
    private final static String MY_KEY = "FFR437G";

    public static String getMyKey() {
        return MY_KEY;
    }

    public static List<Result> getList() {
        return list;
    }



}
