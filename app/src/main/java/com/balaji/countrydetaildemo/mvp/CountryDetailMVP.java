package com.balaji.countrydetaildemo.mvp;

import com.balaji.countrydetaildemo.model.Country;

/**
 * Created by balaji on 7/3/18.
 */

public interface CountryDetailMVP {

    void showLoader();

    void hideLoader();

    void onFailure(String errorMessage);

    void setCountryData(Country weatherList);
}
