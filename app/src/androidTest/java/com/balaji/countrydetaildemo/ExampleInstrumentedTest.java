package com.balaji.countrydetaildemo;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.balaji.countrydetaildemo.model.Country;
import com.balaji.countrydetaildemo.model.Row;
import com.balaji.countrydetaildemo.utils.AppUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.balaji.countrydetaildemo", appContext.getPackageName());
    }

    @Test
    public void testNetworkErrorWithNetwork() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        if(AppUtils.isNetworkAvailable(appContext)) {
            assertEquals("Not Found",AppUtils.formNetworkErrorText(appContext,"Not Found"));
        }

    }

    @Test
    public void testNetworkErrorWithoutNetwork() throws Exception{
        Context appContext = InstrumentationRegistry.getTargetContext();
        if(!AppUtils.isNetworkAvailable(appContext)) {
            assertEquals("Check Network", AppUtils.formNetworkErrorText(appContext,"Not Found"));
        }
    }
}
