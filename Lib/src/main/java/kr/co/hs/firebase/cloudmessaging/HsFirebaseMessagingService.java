package kr.co.hs.firebase.cloudmessaging;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import kr.co.hs.content.HsPreferences;
import kr.co.hs.firebase.IHsFirebaseService;
import kr.co.hs.firebase.app.HsFirebaseApplication;
import kr.co.hs.firebase.auth.HsFirebaseAuth;

/**
 * Created by Bae on 2016-12-31.
 */

public class HsFirebaseMessagingService extends FirebaseMessagingService implements IHsFirebaseService{
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

    public HsFirebaseApplication getHsFirebaseApplication() {
        HsFirebaseApplication application = (HsFirebaseApplication) getApplicationContext();
        return application;
    }

    @Override
    public HsPreferences getDefaultPreference() {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            return application.getDefaultPreference();
        return null;
    }

    @Override
    public String getDeviceId() {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            application.getDeviceId();
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

    @Override
    public FirebaseDatabase getFirebaseDatabase() {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.getFirebaseDatabase();
        }
        return null;
    }

    @Override
    public DatabaseReference getDatabaseReference() {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.getDatabaseReference();
        }
        return null;
    }

    @Override
    public DatabaseReference child(String key) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.child(key);
        }
        return null;
    }

    @Override
    public Task<Void> updateChildren(Map<String, Object> map) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.updateChildren(map);
        }
        return null;
    }

    @Override
    public void updateChildren(Map<String, Object> map, DatabaseReference.CompletionListener listener) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            application.updateChildren(map, listener);
        }
    }

    @Override
    public String getRemoteClassName(Intent intent) {
        return intent.getStringExtra(EXTRA_REMOTE_CLASS);
    }

    @Override
    public boolean sendPendingBroadcast(int i, Intent intent, int i1) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            return application.sendPendingBroadcast(i, intent, i1);
        else
            return false;
    }

    @Override
    public boolean sendPendingBroadcast(int i, Intent intent) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            return application.sendPendingBroadcast(i, intent);
        else
            return false;
    }

    @Override
    public boolean sendPendingBroadcast(Intent intent) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            return application.sendPendingBroadcast(intent);
        else
            return false;
    }
}
