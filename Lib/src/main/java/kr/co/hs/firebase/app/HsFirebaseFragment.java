package kr.co.hs.firebase.app;


import android.app.Activity;
import android.os.Bundle;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import kr.co.hs.app.HsFragment;
import kr.co.hs.firebase.IHsFirebaseApplication;
import kr.co.hs.firebase.IHsFirebaseFragment;
import kr.co.hs.firebase.auth.HsFirebaseAuth;
import kr.co.hs.firebase.cloudmessaging.HsFirebaseMessagingInfo;

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
    public void signInWithCredential(AuthCredential credential, HsFirebaseAuth.OnFirebaseAuthResultListener listener) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            application.signInWithCredential(credential, listener);
    }

    @Override
    public boolean subscribe(String topic) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.subscribe(topic);
        }
        return false;
    }

    @Override
    public boolean unsubscribe(String topic) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.unsubscribe(topic);
        }
        return false;
    }

    @Override
    public boolean send(RemoteMessage remoteMessage) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.send(remoteMessage);
        }
        return false;
    }

    @Override
    public HsFirebaseMessagingInfo getFirebaseMessagingInfo(String serverKey) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.getFirebaseMessagingInfo(serverKey);
        }
        return null;
    }

    @Override
    public boolean send(String serverKey, String to, Map<String, String> payload, HsFirebaseMessagingInfo.OnSendResultListener listener) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.send(serverKey, to, payload, listener);
        }
        return false;
    }

    @Override
    public boolean send(String serverKey, String[] to, Map<String, String> payload, HsFirebaseMessagingInfo.OnSendResultListener listener) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.send(serverKey, to, payload, listener);
        }
        return false;
    }
}
