package com.balaji.countrydetaildemo.presenter;

import com.balaji.countrydetaildemo.model.Country;
import com.balaji.countrydetaildemo.mvp.CountryDetailMVP;
import com.balaji.countrydetaildemo.service.CountryDetailService;
import com.balaji.countrydetaildemo.utils.AppUtils;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by balaji on 7/3/18.
 */

public class CountryDetailPresenter {

    private CountryDetailService countryDetailService;
    private CountryDetailMVP countryDetailMVP;
    private CompositeSubscription subscriptions;

    @Inject
    public CountryDetailPresenter(CountryDetailService service) {
        this.countryDetailService = service;
        this.subscriptions = new CompositeSubscription();
    }

    /**
     * Method to get data from country data API.
     */
    public void getCountryDataList() {
        countryDetailMVP.showLoader();

        Subscription subscription = countryDetailService.getCountryDetailList(new CountryDetailService.GetCountryDetailListCallback() {
            @Override
            public void onSuccess(Country countryData) {
                countryDetailMVP.hideLoader();
                if (countryData != null) {
                    countryDetailMVP.setTitle(countryData.getTitle());
                    AppUtils.removeEmptyData(countryData);
                    countryDetailMVP.setCountryData(countryData);
                }
            }

            @Override
            public void onError(String networkError) {
                countryDetailMVP.hideLoader();
                countryDetailMVP.onFailure(networkError);
            }
        });

        subscriptions.add(subscription);
    }


    public void onStop() {
        subscriptions.unsubscribe();
    }

    public void setView(CountryDetailMVP countryDetailMVP) {
        this.countryDetailMVP = countryDetailMVP;
    }

}


