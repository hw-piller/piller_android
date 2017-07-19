package one.kafuuchino.piller.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by Junseok Oh on 2017-07-16.
 */

public class AppController extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

}
