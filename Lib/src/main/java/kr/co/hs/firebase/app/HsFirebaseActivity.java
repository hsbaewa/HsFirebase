package kr.co.hs.firebase.app;


import android.app.Activity;
import android.os.Bundle;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;

import kr.co.hs.app.HsActivity;
import kr.co.hs.firebase.IHsFirebaseActivity;
import kr.co.hs.firebase.auth.HsFirebaseAuth;

/**
 * Created by Bae on 2016-12-25.
 */

public class HsFirebaseActivity extends HsActivity implements IHsFirebaseActivity {

    @Override
    public String getFirebaseToken() {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            return application.getFirebaseToken();

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

    @Override
    public HsFirebaseAuth getFirebaseAuth() {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            return application.getFirebaseAuth();
        return null;
    }

    @Override
    public FirebaseUser getFirebaseUser() {
        HsFirebaseAuth auth = getFirebaseAuth();
        if(auth != null)
            return auth.getCurrentUser();
        else
            return null;
    }

    @Override
    public void createUserWithEmailAndPassword(String email, String password, HsFirebaseAuth.OnFirebaseAuthResultListener listener) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            application.createUserWithEmailAndPassword(email, password, listener);
    }

    @Override
    public void signInWithEmailAndPassword(String email, String password, HsFirebaseAuth.OnFirebaseAuthResultListener listener) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            application.signInWithEmailAndPassword(email, password, listener);
    }

    @Override
    public void signOut() {
        signOut(null);
    }

    @Override
    public void signOut(HsFirebaseAuth.OnFirebaseSignOutResultListener listener) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            application.signOut(listener);
    }

    @Override
    public void signInWithCredential(AuthCredential credential, HsFirebaseAuth.OnFirebaseAuthResultListener listener) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            application.signInWithCredential(credential, listener);
    }

    @Override
    public HsFirebaseApplication getHsFirebaseApplication() {
        HsFirebaseApplication application = (HsFirebaseApplication) getHsApplication();
        return application;
    }
}
