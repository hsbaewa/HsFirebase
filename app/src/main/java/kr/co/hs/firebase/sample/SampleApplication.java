package kr.co.hs.firebase.sample;

import kr.co.hs.firebase.app.HsFirebaseMultiDexApplication;

/**
 * Created by Bae on 2016-12-31.
 */

public class SampleApplication extends HsFirebaseMultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        subscribe("lobby");
    }
}
