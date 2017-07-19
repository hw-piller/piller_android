package one.kafuuchino.piller.models;

/**
 * Created by Junseok Oh on 2017-07-19.
 */

public class Eat {
    private String token, breakfirst, launch, dinner;

    public Eat(String token, String breakfirst, String launch, String dinner) {
        this.token = token;
        this.breakfirst = breakfirst;
        this.launch = launch;
        this.dinner = dinner;
    }

    public Eat() {

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBreakfirst() {
        return breakfirst;
    }

    public void setBreakfirst(String breakfirst) {
        this.breakfirst = breakfirst;
    }

    public String getLaunch() {
        return launch;
    }

    public void setLaunch(String launch) {
        this.launch = launch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }
}

