package one.kafuuchino.piller.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Junseok Oh on 2017-04-07.
 */

public class NetworkHelper {
    private final static String url = "http://169.56.126.158";
    private final static int port = 6974;

    private static Retrofit retrofit;

    public static NetworkAPI getNetworkInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url + ":" + port)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(NetworkAPI.class);
    }

    public static boolean returnNetworkState(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
