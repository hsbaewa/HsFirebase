package kr.co.hs.firebase.content;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.Iterator;
import java.util.Map;

import kr.co.hs.content.HsPreferences;

/**
 * Created by Bae on 2016-12-31.
 */

public class HsFirebaseRemoteConfigPreference extends HsPreferences implements OnCompleteListener<Void> {

    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    public HsFirebaseRemoteConfigPreference(SharedPreferences mSharedPreferences) {
        super(mSharedPreferences);

        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings settings = new FirebaseRemoteConfigSettings.Builder()
                .build();
        mFirebaseRemoteConfig.setConfigSettings(settings);
    }

    public FirebaseRemoteConfig getRemoteConfig(){
        return this.mFirebaseRemoteConfig;
    }

    private Task<Void> fetch(){
        return this.mFirebaseRemoteConfig.fetch();
    }
    private Task<Void> fetch(long cacheExpire){
        return this.mFirebaseRemoteConfig.fetch(cacheExpire);
    }
    private boolean activateFetched(){
        return this.mFirebaseRemoteConfig.activateFetched();
    }
    public void syncFetched(){
        syncFetched(-1);
    }
    public void syncFetched(long cacheExpire){
        synchronized (this){
            Map<String, Object> defaults = getCacheDataMap();
            if(defaults.size() > 0)
                getRemoteConfig().setDefaults(getCacheDataMap());
        }
        if(cacheExpire < 0)
            this.fetch().addOnCompleteListener(this);
        else
            this.fetch(cacheExpire).addOnCompleteListener(this);
    }

    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if(task.isSuccessful()){
            activateFetched();
            synchronized (this){
                Map<String, ?> allData = getAll();
                Iterator<String> keySet = allData.keySet().iterator();
                while(keySet.hasNext()){
                    String key = keySet.next();
                    Object obj = allData.get(key);
                    if(obj instanceof String) {
                        String str = getRemoteConfig().getString(key);
                        set(key, str);
                    } else if(obj instanceof Integer) {
                        long l = getRemoteConfig().getLong(key);
                        set(key, (int)l);
                    } else if(obj instanceof Long) {
                        long l = getRemoteConfig().getLong(key);
                        set(key, l);
                    } else if(obj instanceof Float) {
                        double d = getRemoteConfig().getDouble(key);
                        set(key, (float)d);
                    } else if(obj instanceof Boolean) {
                        boolean b = getRemoteConfig().getBoolean(key);
                        set(key, b);
                    }
                }
                commit();
            }
        }
    }
}