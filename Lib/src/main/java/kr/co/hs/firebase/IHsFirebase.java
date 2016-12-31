package kr.co.hs.firebase;


import android.app.Activity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import kr.co.hs.app.IHs;
import kr.co.hs.firebase.auth.HsFirebaseAuth;

/**
 * Created by Bae on 2016-12-25.
 */

public interface IHsFirebase extends IHs {
    String VERSION_BASEOS = "BASE_OS";
    String VERSION_CODENAME = "CODENAME";
    String VERSION_INCREMENTAL = "INCREMENTAL";
    String VERSION_RELEASE = "RELEASE";
    String VERSION_SDK_INT = "SDK_INT";
    String BOARD = "BOARD";
    String BOOTLOADER = "BOOTLOADER";
    String BRAND = "BRAND";
    String DEVICE = "DEVICE";
    String DISPLAY = "DISPLAY";
    String FINGERPRINT = "FINGERPRINT";
    String HARDWARE = "HARDWARE";
    String HOST = "HOST";
    String ID = "ID";
    String MANUFACTURER = "MANUFACTURER";
    String MODEL = "MODEL";
    String PRODUCT = "PRODUCT";
    String SERIAL = "SERIAL";
    String TAGS = "TAGS";
    String TYPE = "TYPE";
    String USER = "USER";
    String TIME = "TIME";
    String SUPPORTED_ABIS = "SUPPORTED_ABIS";
    String SUPPORTED_32_BIT_ABIS = "SUPPORTED_32_BIT_ABIS";
    String SUPPORTED_64_BIT_ABIS = "SUPPORTED_64_BIT_ABIS";

    String getFirebaseToken();

    //Analytics 관련
    boolean sendFirebaseAnalyticsLogEvent(String name, Bundle data);
    boolean setFirebaseAnalyticsUserId(String id);
    boolean setFirebaseAnalyticsCurrentScreen(Activity activity, String screenName, String screenClassOverride);

    //인증 관련
    HsFirebaseAuth getFirebaseAuth();
    FirebaseUser getFirebaseUser();
    void createUserWithEmailAndPassword(String email, String password, HsFirebaseAuth.OnFirebaseAuthResultListener listener);
    void signInWithEmailAndPassword(String email, String password, HsFirebaseAuth.OnFirebaseAuthResultListener listener);
    void signOut();
}
