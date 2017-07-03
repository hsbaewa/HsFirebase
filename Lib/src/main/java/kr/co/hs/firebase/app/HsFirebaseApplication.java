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
import kr.co.hs.app.HsApplication;
import kr.co.hs.firebase.IHsFirebaseApplication;
import kr.co.hs.firebase.auth.HsFirebaseAuth;
import kr.co.hs.firebase.cloudmessaging.HsFirebaseMessaging;
import kr.co.hs.firebase.cloudmessaging.HsFirebaseMessagingInfo;
import kr.co.hs.net.HsRestClient;

/**
 * Created by Bae on 2016-12-25.
 */

public class HsFirebaseApplication extends HsApplication implements IHsFirebaseApplication {
    private String mFirebaseToken = null;
    private FirebaseAnalytics mFirebaseAnalytics = null;
    private HsFirebaseAuth mFirebaseAuth = null;
    private HsFirebaseMessaging mFirebaseMessaging = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mFirebaseToken = FirebaseInstanceId.getInstance().getToken();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
//        setFirebaseAnalyticsUserProperty();
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
    public FirebaseAnalytics getFirebaseAnalytics() {
        return mFirebaseAnalytics;
    }

    @Override
    public boolean setFirebaseAnalyticsUserProperty(String key, String value) {
        if(this.mFirebaseAnalytics != null){
            this.mFirebaseAnalytics.setUserProperty(key, value);
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

    @Override
    public void onFirebaseTokenRefresh(String token) {

    }

    void setFirebaseToken(String token){
        this.mFirebaseToken = token;
        onFirebaseTokenRefresh(this.mFirebaseToken);
    }
    public void setFirebaseAnalyticsUserProperty(){
        try{
//            setFirebaseAnalyticsUserProperty(VERSION_BASEOS, Build.VERSION.BASE_OS);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            setFirebaseAnalyticsUserProperty(VERSION_CODENAME, Build.VERSION.CODENAME);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            setFirebaseAnalyticsUserProperty(VERSION_INCREMENTAL, Build.VERSION.INCREMENTAL);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            setFirebaseAnalyticsUserProperty(VERSION_RELEASE, Build.VERSION.RELEASE);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            setFirebaseAnalyticsUserProperty(VERSION_SDK_INT, ""+Build.VERSION.SDK_INT);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            setFirebaseAnalyticsUserProperty(BOARD, Build.BOARD);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            setFirebaseAnalyticsUserProperty(BOOTLOADER, Build.BOOTLOADER);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            setFirebaseAnalyticsUserProperty(BRAND, Build.BRAND);
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            setFirebaseAnalyticsUserProperty(DEVICE, Build.DEVICE);
        }catch (Exception e){

        }
        try{
            setFirebaseAnalyticsUserProperty(DISPLAY, Build.DISPLAY);
        }catch (Exception e){

        }
        try{
            setFirebaseAnalyticsUserProperty(FINGERPRINT, Build.FINGERPRINT);
        }catch (Exception e){

        }
        try{
            setFirebaseAnalyticsUserProperty(HARDWARE, Build.HARDWARE);
        }catch (Exception e){

        }
        try{
            setFirebaseAnalyticsUserProperty(HOST, Build.HOST);
        }catch (Exception e){

        }
        try{
            setFirebaseAnalyticsUserProperty(ID, Build.ID);
        }catch (Exception e){

        }
        try{
            setFirebaseAnalyticsUserProperty(MANUFACTURER, Build.MANUFACTURER);
        }catch (Exception e){

        }
        try{
            setFirebaseAnalyticsUserProperty(MODEL, Build.MODEL);
        }catch (Exception e){

        }
        try{
            setFirebaseAnalyticsUserProperty(PRODUCT, Build.PRODUCT);
        }catch (Exception e){

        }
        try{
            setFirebaseAnalyticsUserProperty(SERIAL, Build.SERIAL);
        }catch (Exception e){

        }
        try{
            setFirebaseAnalyticsUserProperty(TAGS, Build.TAGS);
        }catch (Exception e){

        }
        try{
            setFirebaseAnalyticsUserProperty(TYPE, Build.TYPE);
        }catch (Exception e){

        }
        try{
            setFirebaseAnalyticsUserProperty(USER, Build.USER);
        }catch (Exception e){

        }
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            setFirebaseAnalyticsUserProperty(TIME, sdf.format(Build.TIME));
        }catch (Exception e){

        }
    }


}

