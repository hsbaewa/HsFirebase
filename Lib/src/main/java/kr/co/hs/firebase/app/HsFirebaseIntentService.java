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

import kr.co.hs.app.HsIntentService;
import kr.co.hs.firebase.IHsFirebaseService;
import kr.co.hs.firebase.auth.HsFirebaseAuth;
import kr.co.hs.firebase.cloudmessaging.HsFirebaseMessagingInfo;

/**
 * 생성된 시간 2017-01-19, Bae 에 의해 생성됨
 * 프로젝트 이름 : HsFirebase
 * 패키지명 : kr.co.hs.firebase.app
 */

public abstract class HsFirebaseIntentService extends HsIntentService implements IHsFirebaseService{

    @Override
    public HsFirebaseApplication getHsFirebaseApplication() {
        return (HsFirebaseApplication) getApplicationContext();
    }

    @Override
    public String getFirebaseToken() {
        if(getHsFirebaseApplication() != null)
            return getHsFirebaseApplication().getFirebaseToken();
        return null;
    }

    @Override
    public boolean sendFirebaseAnalyticsLogEvent(String name, Bundle data) {
        if(getHsFirebaseApplication() != null)
            return getHsFirebaseApplication().sendFirebaseAnalyticsLogEvent(name, data);
        return false;
    }

    @Override
    public boolean setFirebaseAnalyticsUserId(String id) {
        if(getHsFirebaseApplication() != null)
            getHsFirebaseApplication().setFirebaseAnalyticsUserId(id);
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
    public FirebaseStorage getFirebaseStorage() {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.getFirebaseStorage();
        }
        return null;
    }

    @Override
    public StorageReference getStorageReference(String Url) {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.getStorageReference(Url);
        }
        return null;
    }
}