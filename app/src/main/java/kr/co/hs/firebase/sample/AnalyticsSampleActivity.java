package kr.co.hs.firebase.sample;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import kr.co.hs.firebase.app.HsFirebaseActivity;

/**
 * Created by Bae on 2016-12-31.
 */

public class AnalyticsSampleActivity extends HsFirebaseActivity {

    TextView tv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_analyticssample);
        super.onCreate(savedInstanceState);

        tv = (TextView) findViewById(R.id.TextView);

        tv.setText("");

        tv.append(Build.VERSION.BASE_OS+"\n");
        tv.append(Build.VERSION.CODENAME+"\n");
        tv.append(Build.VERSION.INCREMENTAL+"\n");
        tv.append(Build.VERSION.RELEASE+"\n");
        tv.append(Build.VERSION.SDK_INT+"\n\n");
        tv.append(Build.BOARD+"\n");
        tv.append(Build.BOOTLOADER+"\n");
        tv.append(Build.BRAND+"\n");
        tv.append(Build.DEVICE+"\n");
        tv.append(Build.DISPLAY+"\n");
        tv.append(Build.FINGERPRINT+"\n");
        tv.append(Build.HARDWARE+"\n");
        tv.append(Build.HOST+"\n");
        tv.append(Build.ID+"\n");
        tv.append(Build.MANUFACTURER+"\n");
        tv.append(Build.MODEL+"\n");
        tv.append(Build.PRODUCT+"\n");
        tv.append(Build.SERIAL+"\n");
        tv.append(Build.TAGS+"\n");
        tv.append(Build.TYPE+"\n");
        tv.append(Build.USER+"\n");
        tv.append(Build.TIME+"\n\n");
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            String[] abis = Build.SUPPORTED_ABIS;
            for(int i=0;i<abis.length;i++){
                tv.append(abis[i]+"\n");
            }
            tv.append("\n");
            String[] abis32 = Build.SUPPORTED_32_BIT_ABIS;
            for(int i=0;i<abis32.length;i++){
                tv.append(abis32[i]+"\n");
            }
            tv.append("\n");
            String[] abis64 = Build.SUPPORTED_64_BIT_ABIS;
            for(int i=0;i<abis64.length;i++){
                tv.append(abis64[i]+"\n");
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        setFirebaseAnalyticsCurrentScreen(this, "AnalyticsSampleActivity", null);
    }
}
