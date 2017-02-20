package kr.co.hs.firebase.app;


import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Map;

import kr.co.hs.app.HsActivity;
import kr.co.hs.firebase.IHsFirebaseActivity;
import kr.co.hs.firebase.IHsFirebaseApplication;
import kr.co.hs.firebase.auth.HsFirebaseAuth;
import kr.co.hs.firebase.cloudmessaging.HsFirebaseMessagingInfo;

/**
 * Created by Bae on 2016-12-25.
 */

public class HsFirebaseActivity extends HsActivity implements IHsFirebaseActivity {

    @Override
    public String getFirebaseToken() {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            return application.getFirebaseToken();

        return null;
    }

    @Override
    public boolean sendFirebaseAnalyticsLogEvent(String name, Bundle data) {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            return application.sendFirebaseAnalyticsLogEvent(name, data);
        return false;
    }

    @Override
    public boolean setFirebaseAnalyticsUserId(String id) {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            return application.setFirebaseAnalyticsUserId(id);
        return false;
    }

    @Override
    public boolean setFirebaseAnalyticsCurrentScreen(Activity activity, String screenName, String screenClassOverride) {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            return application.setFirebaseAnalyticsCurrentScreen(activity, screenName, screenClassOverride);
        return false;
    }

    @Override
    public HsFirebaseAuth getFirebaseAuth() {
        IHsFirebaseApplication application = getHsFirebaseApplication();
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
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            application.createUserWithEmailAndPassword(email, password, listener);
    }

    @Override
    public void signInWithEmailAndPassword(String email, String password, HsFirebaseAuth.OnFirebaseAuthResultListener listener) {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            application.signInWithEmailAndPassword(email, password, listener);
    }

    @Override
    public void signOut() {
        signOut(null);
    }

    @Override
    public void signOut(HsFirebaseAuth.OnFirebaseSignOutResultListener listener) {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            application.signOut(listener);
    }

    @Override
    public void signInWithCredential(AuthCredential credential, HsFirebaseAuth.OnFirebaseAuthResultListener listener) {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            application.signInWithCredential(credential, listener);
    }

    @Override
    public boolean subscribe(String topic) {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.subscribe(topic);
        }
        return false;
    }

    @Override
    public boolean unsubscribe(String topic) {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.unsubscribe(topic);
        }
        return false;
    }

    @Override
    public boolean send(RemoteMessage remoteMessage) {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.send(remoteMessage);
        }
        return false;
    }

    @Override
    public HsFirebaseMessagingInfo getFirebaseMessagingInfo(String serverKey) {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.getFirebaseMessagingInfo(serverKey);
        }
        return null;
    }

    @Override
    public boolean send(String serverKey, String to, Map<String, String> payload, HsFirebaseMessagingInfo.OnSendResultListener listener) {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.send(serverKey, to, payload, listener);
        }
        return false;
    }

    @Override
    public boolean send(String serverKey, String[] to, Map<String, String> payload, HsFirebaseMessagingInfo.OnSendResultListener listener) {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.send(serverKey, to, payload, listener);
        }
        return false;
    }

    @Override
    public boolean sendToTopic(String serverKey, String to, Map<String, String> payload, HsFirebaseMessagingInfo.OnSendResultListener listener) {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.sendToTopic(serverKey, to, payload, listener);
        }
        return false;
    }

    @Override
    public FirebaseDatabase getFirebaseDatabase() {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.getFirebaseDatabase();
        }
        return null;
    }

    @Override
    public DatabaseReference getDatabaseReference() {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.getDatabaseReference();
        }
        return null;
    }

    @Override
    public DatabaseReference child(String key) {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.child(key);
        }
        return null;
    }

    @Override
    public Task<Void> updateChildren(Map<String, Object> map) {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.updateChildren(map);
        }
        return null;
    }

    @Override
    public void updateChildren(Map<String, Object> map, DatabaseReference.CompletionListener listener) {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            application.updateChildren(map, listener);
        }
    }

    @Override
    public FirebaseStorage getFirebaseStorage() {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.getFirebaseStorage();
        }
        return null;
    }

    @Override
    public StorageReference getStorageReference(String Url) {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.getStorageReference(Url);
        }
        return null;
    }

    @Override
    public IHsFirebaseApplication getHsFirebaseApplication() {
        IHsFirebaseApplication application = (IHsFirebaseApplication) getHsApplication();
        return application;
    }
}
