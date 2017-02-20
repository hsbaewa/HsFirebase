package kr.co.hs.firebase.app;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.json.simple.parser.ParseException;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import kr.co.hs.app.HsMultiDexApplication;
import kr.co.hs.firebase.IHsFirebaseApplication;
import kr.co.hs.firebase.auth.HsFirebaseAuth;
import kr.co.hs.firebase.cloudmessaging.HsFirebaseMessaging;
import kr.co.hs.firebase.cloudmessaging.HsFirebaseMessagingInfo;
import kr.co.hs.net.HsRestClient;

/**
 * 생성된 시간 2017-02-20, Bae 에 의해 생성됨
 * 프로젝트 이름 : HsFirebase
 * 패키지명 : kr.co.hs.firebase.app
 */

public class HsFirebaseMultiDexApplication extends HsMultiDexApplication implements IHsFirebaseApplication{
    private String mFirebaseToken = null;
    private FirebaseAnalytics mFirebaseAnalytics = null;
    private HsFirebaseAuth mFirebaseAuth = null;
    private HsFirebaseMessaging mFirebaseMessaging = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mFirebaseToken = FirebaseInstanceId.getInstance().getToken();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
        setFirebaseAnalyticsUserProperty();
        mFirebaseAuth = HsFirebaseAuth.getInstance();
        mFirebaseMessaging = HsFirebaseMessaging.getInstance();
    }

    @Override
    public String getFirebaseToken() {
        return this.mFirebaseToken;
    }

    @Override
    public boolean sendFirebaseAnalyticsLogEvent(String name, Bundle data) {
        if(this.mFirebaseAnalytics != null){
            this.mFirebaseAnalytics.logEvent(name, data);
            return true;
        }
        return false;
    }

    @Override
    public boolean setFirebaseAnalyticsUserId(String id) {
        if(this.mFirebaseAnalytics != null){
            this.mFirebaseAnalytics.setUserId(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean setFirebaseAnalyticsCurrentScreen(Activity activity, String screenName, String screenClassOverride) {
        if(this.mFirebaseAnalytics != null){
            this.mFirebaseAnalytics.setCurrentScreen(activity, screenClassOverride, screenClassOverride);
            return true;
        }
        return false;
    }

    @Override
    public HsFirebaseAuth getFirebaseAuth() {
        return this.mFirebaseAuth;
    }

    @Override
    public FirebaseUser getFirebaseUser() {
        return getFirebaseAuth().getCurrentUser();
    }

    @Override
    public void createUserWithEmailAndPassword(String email, String password, HsFirebaseAuth.OnFirebaseAuthResultListener listener) {
        if(getFirebaseAuth() != null)
            getFirebaseAuth().createUserWithEmailAndPassword(email, password, listener);
        else{
            if(listener != null)
                listener.onFailure(new NullPointerException("FirebaseAuth is Null"));
        }
    }

    @Override
    public void signInWithEmailAndPassword(String email, String password, HsFirebaseAuth.OnFirebaseAuthResultListener listener) {
        if(getFirebaseAuth() != null)
            getFirebaseAuth().signInWithEmailAndPassword(email, password, listener);
        else{
            if(listener != null)
                listener.onFailure(new NullPointerException("FirebaseAuth is Null"));
        }
    }

    @Override
    public void signOut() {
        signOut(null);
    }

    @Override
    public void signOut(HsFirebaseAuth.OnFirebaseSignOutResultListener listener) {
        if(getFirebaseAuth() != null)
            getFirebaseAuth().signOut(listener);
    }

    @Override
    public void signInWithCredential(AuthCredential credential, HsFirebaseAuth.OnFirebaseAuthResultListener listener) {
        if(getFirebaseAuth() != null)
            getFirebaseAuth().signInWithCredential(credential, listener);
        else{
            if(listener != null)
                listener.onFailure(new NullPointerException("FirebaseAuth is Null"));
        }
    }

    @Override
    public boolean subscribe(String topic) {
        HsFirebaseMessaging.getInstance().subscribe(topic);
        return true;
    }

    @Override
    public boolean unsubscribe(String topic) {
        HsFirebaseMessaging.getInstance().unsubscribe(topic);
        return true;
    }

    @Override
    public boolean send(RemoteMessage remoteMessage) {
        HsFirebaseMessaging.getInstance().send(remoteMessage);
        return true;
    }

    @Override
    public HsFirebaseMessagingInfo getFirebaseMessagingInfo(String serverKey) {
        HsRestClient client = new HsRestClient();
        Map<String, String> header = new HashMap<>();
        header.put("Authorization", "key="+serverKey);
        String response = client.get(String.format(HsFirebaseMessagingInfo.HOST_IID, getFirebaseToken()), header, 10000);
        try {
            return new HsFirebaseMessagingInfo(response);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean send(String serverKey, String to, Map<String, String> payload, HsFirebaseMessagingInfo.OnSendResultListener listener) {
        String target[] = new String[1];
        target[0] = to;
        HsFirebaseMessaging.CloudMessagingSendJob job = new HsFirebaseMessaging.CloudMessagingSendJob(mFirebaseMessaging, serverKey, target, payload, listener);
        mFirebaseMessaging.getExecutor().execute(job);
        return true;
    }

    @Override
    public boolean send(String serverKey, String[] to, Map<String, String> payload, HsFirebaseMessagingInfo.OnSendResultListener listener) {
        HsFirebaseMessaging.CloudMessagingSendJob job = new HsFirebaseMessaging.CloudMessagingSendJob(mFirebaseMessaging, serverKey, to, payload, listener);
        mFirebaseMessaging.getExecutor().execute(job);
        return true;
    }

    @Override
    public boolean sendToTopic(String serverKey, String to, Map<String, String> payload, HsFirebaseMessagingInfo.OnSendResultListener listener) {
        String target[] = new String[1];
        target[0] = String.format("/topics/%s",to);
        HsFirebaseMessaging.CloudMessagingSendJob job = new HsFirebaseMessaging.CloudMessagingSendJob(mFirebaseMessaging, serverKey, target, payload, listener);
        mFirebaseMessaging.getExecutor().execute(job);
        return true;
    }


    @Override
    public FirebaseDatabase getFirebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }

    @Override
    public DatabaseReference getDatabaseReference() {
        return getFirebaseDatabase().getReference();
    }

    @Override
    public DatabaseReference child(String key) {
        return getDatabaseReference().child(key);
    }

    @Override
    public Task<Void> updateChildren(Map<String, Object> map) {
        return getDatabaseReference().updateChildren(map);
    }

    @Override
    public void updateChildren(Map<String, Object> map, DatabaseReference.CompletionListener listener) {
        getDatabaseReference().updateChildren(map, listener);
    }

    @Override
    public FirebaseStorage getFirebaseStorage() {
        return FirebaseStorage.getInstance();
    }

    @Override
    public StorageReference getStorageReference(String url) {
        return getFirebaseStorage().getReferenceFromUrl(url);
    }


    void setFirebaseToken(String token){
        this.mFirebaseToken = token;
    }
    private void setFirebaseAnalyticsUserProperty(){
        if(mFirebaseAnalytics != null){
            mFirebaseAnalytics.setUserProperty(VERSION_BASEOS, Build.VERSION.BASE_OS);
            mFirebaseAnalytics.setUserProperty(VERSION_CODENAME, Build.VERSION.CODENAME);
            mFirebaseAnalytics.setUserProperty(VERSION_INCREMENTAL, Build.VERSION.INCREMENTAL);
            mFirebaseAnalytics.setUserProperty(VERSION_RELEASE, Build.VERSION.RELEASE);
            mFirebaseAnalytics.setUserProperty(VERSION_SDK_INT, ""+Build.VERSION.SDK_INT);
            mFirebaseAnalytics.setUserProperty(BOARD, Build.BOARD);
            mFirebaseAnalytics.setUserProperty(BOOTLOADER, Build.BOOTLOADER);
            mFirebaseAnalytics.setUserProperty(BRAND, Build.BRAND);
            mFirebaseAnalytics.setUserProperty(DEVICE, Build.DEVICE);
            mFirebaseAnalytics.setUserProperty(DISPLAY, Build.DISPLAY);
            mFirebaseAnalytics.setUserProperty(FINGERPRINT, Build.FINGERPRINT);
            mFirebaseAnalytics.setUserProperty(HARDWARE, Build.HARDWARE);
            mFirebaseAnalytics.setUserProperty(HOST, Build.HOST);
            mFirebaseAnalytics.setUserProperty(ID, Build.ID);
            mFirebaseAnalytics.setUserProperty(MANUFACTURER, Build.MANUFACTURER);
            mFirebaseAnalytics.setUserProperty(MODEL, Build.MODEL);
            mFirebaseAnalytics.setUserProperty(PRODUCT, Build.PRODUCT);
            mFirebaseAnalytics.setUserProperty(SERIAL, Build.SERIAL);
            mFirebaseAnalytics.setUserProperty(TAGS, Build.TAGS);
            mFirebaseAnalytics.setUserProperty(TYPE, Build.TYPE);
            mFirebaseAnalytics.setUserProperty(USER, Build.USER);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            mFirebaseAnalytics.setUserProperty(TIME, sdf.format(Build.TIME));
        }
    }
}
