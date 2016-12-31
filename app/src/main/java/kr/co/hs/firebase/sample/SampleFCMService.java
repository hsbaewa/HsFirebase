package kr.co.hs.firebase.sample;

import com.google.firebase.messaging.RemoteMessage;

import kr.co.hs.firebase.cloudmessaging.HsFirebaseMessagingService;
import kr.co.hs.util.Logger;

/**
 * Created by Bae on 2016-12-31.
 */

public class SampleFCMService extends HsFirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

//        Logger.d(remoteMessage.get);

    }

    @Override
    public void onSendError(String s, Exception e) {
        super.onSendError(s, e);
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
    }
}
