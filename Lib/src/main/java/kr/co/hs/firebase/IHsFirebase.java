package kr.co.hs.firebase;


import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.RemoteMessage;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Map;

import kr.co.hs.app.IHs;
import kr.co.hs.firebase.auth.HsFirebaseAuth;
import kr.co.hs.firebase.cloudmessaging.HsFirebaseMessagingInfo;

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

    String EXTRA_URI = "Uri";
    String EXTRA_FILE = "File";


    String getFirebaseToken();

    //Analytics 관련
    boolean sendFirebaseAnalyticsLogEvent(String name, Bundle data);
    boolean setFirebaseAnalyticsUserId(String id);
    boolean setFirebaseAnalyticsCurrentScreen(Activity activity, String screenName, String screenClassOverride);
    FirebaseAnalytics getFirebaseAnalytics();
    boolean setFirebaseAnalyticsUserProperty(String key, String value);

    //인증 관련
    HsFirebaseAuth getFirebaseAuth();
    FirebaseUser getFirebaseUser();
    void createUserWithEmailAndPassword(String email, String password, HsFirebaseAuth.OnFirebaseAuthResultListener listener);
    void signInWithEmailAndPassword(String email, String password, HsFirebaseAuth.OnFirebaseAuthResultListener listener);
    void signOut();
    void signOut(HsFirebaseAuth.OnFirebaseSignOutResultListener listener);

    void signInWithCredential(AuthCredential credential, HsFirebaseAuth.OnFirebaseAuthResultListener listener);

    //FCM 관련
    boolean subscribe(String topic);
    boolean unsubscribe(String topic);
    boolean send(RemoteMessage remoteMessage);
    HsFirebaseMessagingInfo getFirebaseMessagingInfo(String serverKey);
    boolean send(String serverKey, String to, Map<String, String> payload, HsFirebaseMessagingInfo.OnSendResultListener listener);
    boolean send(String serverKey, String[] to, Map<String, String> payload, HsFirebaseMessagingInfo.OnSendResultListener listener);
    boolean sendToTopic(String serverKey, String to, Map<String, String> payload, HsFirebaseMessagingInfo.OnSendResultListener listener);



    //FirebaseDatabse 관련
    FirebaseDatabase getFirebaseDatabase();
    DatabaseReference getDatabaseReference();
    DatabaseReference child(String key);
    Task<Void> updateChildren(Map<String, Object> map);
    void updateChildren(Map<String, Object> map , DatabaseReference.CompletionListener listener);



    //FirebaseStorage 관련
    FirebaseStorage getFirebaseStorage();
    StorageReference getStorageReference(String Url);
}
