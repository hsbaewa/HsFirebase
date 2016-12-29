package kr.co.hs.firebase.app;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import kr.co.hs.firebase.IHsFirebaseApplication;
import kr.co.hs.firebase.content.HsFirebaseIntent;


/**
 * Created by Bae on 2016-12-25.
 */

public class HsFirebaseInstanceIdService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        HsFirebaseApplication application = (HsFirebaseApplication) getHsFirebaseApplication();
        if(application != null)
            application.setFirebaseToken(token);

        Intent intent = new Intent(HsFirebaseIntent.ACTION_TOKEN_REFRESH);
        intent.putExtra(HsFirebaseIntent.EXTRA_TOKEN, token);
        sendPendingBroadcast(0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    protected IHsFirebaseApplication getHsFirebaseApplication(){
        Context context = getApplicationContext();
        if(context != null){
            return (IHsFirebaseApplication) context;
        }
        return null;
    }

    protected boolean sendPendingBroadcast(int requestCode, Intent intent, int flags){
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), requestCode, intent, flags);
        try {
            pendingIntent.send();
            return true;
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
            return false;
        }
    }
}
