package one.kafuuchino.piller.models;

/**
 * Created by Junseok Oh on 2017-07-19.
 */

public class User {
    private String name, id, password, age, sex, token;

    public User(String name, String id, String password, String age, String sex, String token) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.age = age;
        this.sex = sex;
        this.token = token;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
