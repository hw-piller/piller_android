package one.kafuuchino.piller.models;

import java.util.ArrayList;

/**
 * Created by Junseok Oh on 2017-07-19.
 */

public class Allergy {
    private String token;
    private ArrayList<Object> list;

    public Allergy(String token, ArrayList<Object> list) {
        this.token = token;
        this.list = list;
    }

    public Allergy() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ArrayList<Object> getList() {
        return list;
    }

    public void setList(ArrayList<Object> list) {
        this.list = list;
    }
}
