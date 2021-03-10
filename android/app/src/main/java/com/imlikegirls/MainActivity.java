package com.imlikegirls;

import android.os.Bundle;

import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactActivityDelegate;
import com.facebook.react.ReactRootView;
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView;

import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import expo.modules.splashscreen.singletons.SplashScreen;
import expo.modules.splashscreen.SplashScreenImageResizeMode;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends ReactActivity {  
   private static final String AF_DEV_KEY = "qrtbzRUU9YA46wKnGsw47e";
@Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // SplashScreen.show(...) has to be called after super.onCreate(...)
    // Below line is handled by '@expo/configure-splash-screen' command and it's discouraged to modify it manually
    SplashScreen.show(this, SplashScreenImageResizeMode.CONTAIN, ReactRootView.class, false);

    AppsFlyerConversionListener conversionListener = new AppsFlyerConversionListener() {
        @Override
        public void onConversionDataSuccess(Map<String, Object> conversionData) {

            for (String attrName : conversionData.keySet()) {
                Log.d("LOG_TAG", "attribute: " + attrName + " = " + conversionData.get(attrName));
            }
        }

        @Override
        public void onConversionDataFail(String errorMessage) {
            Log.d("LOG_TAG", "error getting conversion data: " + errorMessage);
        }

        @Override
        public void onAppOpenAttribution(Map<String, String> attributionData) {
            for (String attrName : attributionData.keySet()) {
                Log.d("LOG_TAG", "attribute: " + attrName + " = " + attributionData.get(attrName));
            }
        }

        @Override
        public void onAttributionFailure(String errorMessage) {
            Log.d("LOG_TAG", "error onAttributionFailure : " + errorMessage);
        }
    };

    AppsFlyerLib.getInstance().init(AF_DEV_KEY, conversionListener, this);
    AppsFlyerLib.getInstance().start(this);

}


    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "main";
    }

    @Override
    protected ReactActivityDelegate createReactActivityDelegate() {
        return new ReactActivityDelegate(this, getMainComponentName()) {
            @Override
            protected ReactRootView createRootView() {
                return new RNGestureHandlerEnabledRootView(MainActivity.this);
            }
        };
    }
}
