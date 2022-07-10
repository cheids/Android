package android.bignerdranch.lost.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetIsConnUtil {


    public static boolean netIsConn(Context context){
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getActiveNetworkInfo();


        if (info == null) {
            return false;
        }
        return info.isConnected();
    }
}