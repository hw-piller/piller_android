package one.kafuuchino.piller.utils;

/**
 * Created by Junseok Oh on 2017-07-18.
 */


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.util.Pair;

import com.google.gson.Gson;

import one.kafuuchino.piller.models.User;


public class CredentialsManager {

    /* Login Type
    * 0 Facebook
    * 1: Native
    * */

    /* Data Keys */
    private static String USER_SCHEMA = "user_schema";
    private static String HAS_ACTIVE_USER = "hasactive";
    private static String FACEBOOK_TOKEN = "facebook_token";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;
    static CredentialsManager manager;

    public static CredentialsManager getInstance() {
        if (manager == null) manager = new CredentialsManager();
        return manager;
    }

    private CredentialsManager() {
        context = AppController.getContext();
        preferences = context.getSharedPreferences("Cardline", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void save(String key, String data) {
        editor.putString(key, data);
        editor.apply();
    }

    public void saveUserInfo(User user) {
        editor.putString(USER_SCHEMA, new Gson().toJson(user));
        editor.putBoolean(HAS_ACTIVE_USER, true);
        editor.apply();
    }
    public void updateUserInfo(User user) {
        saveUserInfo(user);
    }

    public Pair<Boolean, User> getActiveUser() {
        if (preferences.getBoolean(HAS_ACTIVE_USER, false)) {
            User user = new Gson().fromJson(preferences.getString(USER_SCHEMA, ""), User.class);
            return Pair.create(true, user);
        } else return Pair.create(false, null);
    }

    public void removeAllData() {
        editor.clear();
        editor.apply();
    }

    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public boolean getBoolean(String key) {
        return preferences.getBoolean(key, false);
    }

    public boolean isFirst() {
        return preferences.getBoolean("IS_FIRST", true);
    }

    public void notFirst() {
        editor.putBoolean("IS_FIRST", false);
        editor.apply();
    }

    public long getLong(String key) {
        return preferences.getLong(key, 0);
    }

}