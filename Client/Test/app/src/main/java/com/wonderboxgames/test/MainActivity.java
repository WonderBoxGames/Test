package com.wonderboxgames.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends Activity {

    // [START declare_analytics]
    private FirebaseAnalytics mFirebaseAnalytics;
    // [END declare_analytics]

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Activity activityThis = this;

        // [START shared_app_measurement]
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        // [END shared_app_measurement]

        MobileAds.initialize(this);

        TextView textView = findViewById(R.id.info);
        findViewById(R.id.btn_test_1)
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WLog.Log("btn1");
                                textView.setText("btn1");
                                recordImageView();
                            }
                        }
                );

        findViewById(R.id.btn_test_2)
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WLog.Log("btn2");
                                textView.setText("btn2");
                                loadAd();
                            }
                        }
                );

        findViewById(R.id.btn_test_3)
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WLog.Log("btn3");
                                textView.setText("btn3");
                            }
                        }
                );

        findViewById(R.id.btn_test_4)
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WLog.Log("btn4");
                                textView.setText("btn4");
                            }
                        }
                );
    }

    //Analytics
    private void recordImageView() {
        String id = "Image ID";
        String name = "Image Title";

        // [START image_view_event]
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        // [END image_view_event]
    }

    //AdMob
    private boolean adIsLoading;
    private InterstitialAd interstitialAd;
    private static final String AD_UNIT_ID = "ca-app-pub-9349817576427439/9554722705";
    private static final String TAG = "Interstitial Ads";

    public void loadAd() {
        // Request a new ad if one isn't already loaded.
        if (adIsLoading || interstitialAd != null) {
            return;
        }
        adIsLoading = true;
        // [START load_ad]
        InterstitialAd.load(
                this,
                AD_UNIT_ID,
                new AdRequest.Builder().build(),
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        WLog.Debug(TAG, "Ad was loaded.");
                        MainActivity.this.interstitialAd = interstitialAd;
                        // [START_EXCLUDE silent]
                        adIsLoading = false;
                        Toast.makeText(MainActivity.this, "onAdLoaded()", Toast.LENGTH_SHORT).show();
                        // [START set_fullscreen_callback]
                        interstitialAd.setFullScreenContentCallback(
                                new FullScreenContentCallback() {
                                    @Override
                                    public void onAdDismissedFullScreenContent() {
                                        // Called when fullscreen content is dismissed.
                                        WLog.Debug(TAG, "The ad was dismissed.");
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        MainActivity.this.interstitialAd = null;
                                    }

                                    @Override
                                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                                        // Called when fullscreen content failed to show.
                                        WLog.Debug(TAG, "The ad failed to show.");
                                        // Make sure to set your reference to null so you don't
                                        // show it a second time.
                                        MainActivity.this.interstitialAd = null;
                                    }

                                    @Override
                                    public void onAdShowedFullScreenContent() {
                                        // Called when fullscreen content is shown.
                                        WLog.Debug(TAG, "The ad was shown.");
                                    }

                                    @Override
                                    public void onAdImpression() {
                                        // Called when an impression is recorded for an ad.
                                        WLog.Debug(TAG, "The ad recorded an impression.");
                                    }

                                    @Override
                                    public void onAdClicked() {
                                        // Called when ad is clicked.
                                        WLog.Debug(TAG, "The ad was clicked.");
                                    }
                                });
                        // [END set_fullscreen_callback]
                        // [END_EXCLUDE]
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        WLog.Debug(TAG, loadAdError.getMessage());
                        interstitialAd = null;
                        // [START_EXCLUDE silent]
                        adIsLoading = false;
                        String error =
                                String.format(
                                        java.util.Locale.US,
                                        "domain: %s, code: %d, message: %s",
                                        loadAdError.getDomain(),
                                        loadAdError.getCode(),
                                        loadAdError.getMessage());
                        Toast.makeText(
                                        MainActivity.this, "onAdFailedToLoad() with error: " + error, Toast.LENGTH_SHORT)
                                .show();
                        WLog.Error(error);
                    }
                    // [END_EXCLUDE]
                });
        // [END load_ad]
    }
}
