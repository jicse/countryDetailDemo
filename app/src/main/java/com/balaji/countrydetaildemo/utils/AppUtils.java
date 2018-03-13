package com.balaji.countrydetaildemo.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

import com.balaji.countrydetaildemo.model.Country;
import com.balaji.countrydetaildemo.model.Row;

import java.util.ArrayList;
import java.util.Iterator;
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

        if (data == null) {
            return null;
        }
        List<Row> rows = data.getRows();
        for (Iterator<Row> it = rows.iterator(); it.hasNext(); ) {
            Row row = it.next();
            if (checkIsRowEmpty(row)) {
                it.remove();
            }
        }

        data.setRows(rows);
        return data;
    }

    /**
     * Method to validate empty row.
     *
     * @param row Row.
     */
    public static boolean checkIsRowEmpty(Row row) {
        if (row == null || (isEmpty(row.getTitle()) && (isEmpty(row.getDescription())
                && (isEmpty(row.getImageHref()))))) {
            return true;
        }
        return false;
    }

    /**
     * Method to validate null or emprty string..
     *
     * @param string String.
     */
    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0;
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
    public static String formNetworkErrorText(Context context, String errorMessage) {
        if (!isNetworkAvailable(context)) {
            return "Check Network";
        } else {
            return errorMessage;
        }
    }
}
