package com.balaji.countrydetaildemo.service;

import com.balaji.countrydetaildemo.model.Country;

import javax.inject.Inject;

import retrofit2.http.GET;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by balaji on 7/3/18.
 */

public class CountryDetailService {

    private final CountryServiceAPI serviceAPI;

    @Inject
    public CountryDetailService(CountryServiceAPI serviceAPI) {
        this.serviceAPI = serviceAPI;
    }

    public Subscription getCountryDetailList(final GetCountryDetailListCallback callback) {

        return serviceAPI.getCountryData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Country>>() {
                    @Override
                    public Observable<? extends Country> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<Country>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError("error");
                    }

                    @Override
                    public void onNext(Country countryDataResponse) {
                        callback.onSuccess(countryDataResponse);

                    }
                });
    }

   public interface GetCountryDetailListCallback {
        void onSuccess(Country cityListResponse);

        void onError(String networkError);
    }

   public interface CountryServiceAPI {
        @GET("s/2iodh4vg0eortkl/facts.json")
        Observable<Country> getCountryData();
    }
}
