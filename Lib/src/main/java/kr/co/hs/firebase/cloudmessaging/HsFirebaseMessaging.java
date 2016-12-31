package kr.co.hs.firebase.cloudmessaging;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import kr.co.hs.net.HsRestClient;

/**
 * Created by Bae on 2016-12-31.
 */

public class HsFirebaseMessaging {
    private static final String FCM_SEND_HOST = "https://fcm.googleapis.com/fcm/send";
    private ExecutorService mExecutorService;

    private static HsFirebaseMessaging instance = null;
    public static HsFirebaseMessaging getInstance(){
        if(instance == null)
            instance = new HsFirebaseMessaging();
        return instance;
    }

    private HsFirebaseMessaging(){
        mExecutorService = Executors.newSingleThreadExecutor();
    }

    public ExecutorService getExecutor(){
        return mExecutorService;
    }

    public void subscribe(String topic){
        FirebaseMessaging.getInstance().subscribeToTopic(topic);
    }

    public void unsubscribe(String topic){
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
    }

    public void send(RemoteMessage remoteMessage){
        FirebaseMessaging.getInstance().send(remoteMessage);
    }

    public String send(String serverKey, String to, Map<String, String> payload){
        HsRestClient client = new HsRestClient();
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Authorization", "key="+serverKey);

        JSONObject payloadObj = new JSONObject();
        Iterator<String> keySet = payload.keySet().iterator();
        while(keySet.hasNext()){
            String key = keySet.next();
            String val = payload.get(key);
            payloadObj.put(key, val);
        }

        JSONObject object = new JSONObject();
        object.put("to", to);
        object.put("message", payloadObj.toJSONString());

        String response = client.post(FCM_SEND_HOST, header, object.toJSONString(), 10000);

        return response;
    }

    public String send(String serverKey, String[] to, Map<String, String> payload){
        HsRestClient client = new HsRestClient();
        Map<String, String> header = new HashMap<>();
        header.put("Content-Type", "application/json");
        header.put("Authorization", "key="+serverKey);

        JSONObject payloadObj = new JSONObject();
        Iterator<String> keySet = payload.keySet().iterator();
        while(keySet.hasNext()){
            String key = keySet.next();
            String val = payload.get(key);
            payloadObj.put(key, val);
        }

        String cond = "";
        if(to.length > 0){
            cond += "'"+to[0]+"' in topics";

            for(int i=1 ;i<to.length;i++){
                cond += " || '"+to[i]+"' in topics";
            }
        }

        JSONObject object = new JSONObject();
        object.put("condition", cond);
        object.put("message", payloadObj.toJSONString());

        String response = client.post(FCM_SEND_HOST, header, object.toJSONString(), 10000);

        return response;
    }

    public static class CloudMessagingSendJob implements Runnable{
        private String serverKey;
        private String[] to;
        private Map<String, String> map;
        private HsFirebaseMessagingInfo.OnSendResultListener listener;
        private HsFirebaseMessaging mFirebaseMessaging;

        public CloudMessagingSendJob(
                HsFirebaseMessaging firebaseMessaging,
                String serverKey,
                String[] to,
                Map<String, String> map,
                HsFirebaseMessagingInfo.OnSendResultListener listener) {
            this.mFirebaseMessaging = firebaseMessaging;
            this.serverKey = serverKey;
            this.to = to;
            this.map = map;
            this.listener = listener;
        }

        @Override
        public void run() {
            String response;
            if(to.length == 1){
                response = this.mFirebaseMessaging.send(serverKey, to[0], map);
            }else{
                response = this.mFirebaseMessaging.send(serverKey, to, map);
            }

            if(response == null || response.length() == 0){
                listener.onFail(null);
            }else{
                JSONParser parser = new JSONParser();
                try {
                    JSONObject object = (JSONObject) parser.parse(response);

                    if(object.containsKey("message_id")){
                        String msg_id = (String) object.get("message_id");
                        long msgId = Long.parseLong(msg_id);
                        listener.onSuccess(msgId);
                    }else if(object.containsKey("error")){
                        String error = (String) object.get("error");
                        listener.onFail(error);
                    }else{
                        listener.onFail(null);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    listener.onFail(e.getMessage());
                }
            }
        }
    }
}
