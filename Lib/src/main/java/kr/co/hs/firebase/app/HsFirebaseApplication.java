package kr.co.hs.firebase.app;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;

import java.text.SimpleDateFormat;

import kr.co.hs.app.HsApplication;
import kr.co.hs.firebase.IHsFirebaseApplication;
import kr.co.hs.firebase.auth.HsFirebaseAuth;

/**
 * Created by Bae on 2016-12-25.
 */

public class HsFirebaseApplication extends HsApplication implements IHsFirebaseApplication {

    private String mFirebaseToken = null;
    private FirebaseAnalytics mFirebaseAnalytics = null;
    private HsFirebaseAuth mFirebaseAuth = null;

    @Override
    public void init() {
        super.init();
        mFirebaseToken = FirebaseInstanceId.getInstance().getToken();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
        setFirebaseAnalyticsUserProperty();
        mFirebaseAuth = HsFirebaseAuth.getInstance();
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
            /*
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                String[] abis = Build.SUPPORTED_ABIS;
                String strABIS = "";
                if(abis.length > 0){
                    strABIS += abis[0];
                    for(int i=1;i<abis.length;i++)
                        strABIS += ", "+abis[i];
                }

                String[] abis32 = Build.SUPPORTED_32_BIT_ABIS;
                String strABIS32 = "";
                if(abis32.length > 0){
                    strABIS32 += abis32[0];
                    for(int i=0;i<abis32.length;i++)
                        strABIS32 += ", "+abis32[i];
                }

                String[] abis64 = Build.SUPPORTED_64_BIT_ABIS;
                String strABIS64 = "";
                if(abis64.length > 0){
                    strABIS64 = abis64[0];
                    for(int i=0;i<abis64.length;i++)
                        strABIS64 += ", "+abis64[i];
                }
            }
            &*/
        }
    }
}

