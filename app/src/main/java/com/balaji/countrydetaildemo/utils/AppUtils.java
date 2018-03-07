package com.balaji.countrydetaildemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.balaji.countrydetaildemo.model.Country;
import com.balaji.countrydetaildemo.model.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility methods for application.
 */
public class AppUtils {

    /**
     * Method to remove the empty data of country.
     *
     * @param data Country object from api.
     * @return Country object after empty filter.
     */
    public static Country removeEmptyData(Country data) {

        List<Row> oldRows = data.getRows();
        List<Row> newRows = new ArrayList<>();

        for (Row row : oldRows) {
            if (!TextUtils.isEmpty(row.getTitle()) ||
                    !TextUtils.isEmpty(row.getTitle()) ||
                    !TextUtils.isEmpty(row.getTitle())) {
                newRows.add(row);
            }
        }

        data.setRows(newRows);
        return data;
    }

    /**
     * Method to check is network available.
     *
     * @param context Context from activity.
     * @return boolean for is connectivity.
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    /**
     * Method to form network error text.
     *
     * @param errorMessage error message from request.
     * @return String complete error message.
     */
    public static String formNetworkErrorText(String errorMessage) {
        return new StringBuilder().append(errorMessage).append("\n Pull to Refresh").toString();
    }
}
