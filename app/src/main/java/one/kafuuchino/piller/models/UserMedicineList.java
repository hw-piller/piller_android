package one.kafuuchino.piller.models;

import java.util.ArrayList;

/**
 * Created by Junseok Oh on 2017-07-19.
 */

public class UserMedicineList {
    private String token;
    private ArrayList<Object> time, name;

    public UserMedicineList(String token, ArrayList<Object> time, ArrayList<Object> name) {
        this.token = token;
        this.time = time;
        this.name = name;
    }

    public UserMedicineList() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<Object> getTime() {
        return time;
    }

    public void setTime(ArrayList<Object> time) {
        this.time = time;
    }

    public ArrayList<Object> getName() {
        return name;
    }

    public void setName(ArrayList<Object> name) {
        this.name = name;
    }
}
