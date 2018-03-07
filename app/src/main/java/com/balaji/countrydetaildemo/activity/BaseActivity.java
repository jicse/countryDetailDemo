package com.balaji.countrydetaildemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.balaji.countrydetaildemo.component.AppComponent;
import com.balaji.countrydetaildemo.component.DaggerAppComponent;
import com.balaji.countrydetaildemo.module.CountryDetailModule;

/**
 * Created by balaji on 20/12/17.
 */

public class BaseActivity extends AppCompatActivity {

    AppComponent component;
    public static final String BASE_URL = "https://dl.dropboxusercontent.com/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = DaggerAppComponent.builder()
                .countryDetailModule(new CountryDetailModule(BASE_URL))
                .build();    }

    public AppComponent getComponent() {
        return component;
    }
}
