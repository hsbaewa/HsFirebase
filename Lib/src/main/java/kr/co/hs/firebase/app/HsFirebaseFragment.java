package kr.co.hs.firebase.app;


import android.app.Activity;
import android.os.Bundle;

import kr.co.hs.app.HsFragment;
import kr.co.hs.firebase.IHsFirebaseApplication;
import kr.co.hs.firebase.IHsFirebaseFragment;

/**
 * Created by Bae on 2016-12-25.
 */

public abstract class HsFirebaseFragment extends HsFragment implements IHsFirebaseFragment {

    @Override
    public HsFirebaseApplication getHsFirebaseApplication() {
        HsFirebaseApplication application = (HsFirebaseApplication) getHsApplication();
        return application;
    }

    @Override
    public String getFirebaseToken() {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.getFirebaseToken();
        }
        return null;
    }

    @Override
    public boolean sendFirebaseAnalyticsLogEvent(String name, Bundle data) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            return application.sendFirebaseAnalyticsLogEvent(name, data);
        return false;
    }

    @Override
    public boolean setFirebaseAnalyticsUserId(String id) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            return application.setFirebaseAnalyticsUserId(id);
        return false;
    }

    @Override
    public boolean setFirebaseAnalyticsCurrentScreen(Activity activity, String screenName, String screenClassOverride) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            return application.setFirebaseAnalyticsCurrentScreen(activity, screenName, screenClassOverride);
        return false;
    }
}
