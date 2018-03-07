package com.balaji.countrydetaildemo.component;

import com.balaji.countrydetaildemo.activity.MainActivity;
import com.balaji.countrydetaildemo.module.CountryDetailModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by balaji on 18/12/17.
 */

@Singleton
@Component(modules = {CountryDetailModule.class,})
public interface AppComponent {
    void inject(MainActivity target);
}