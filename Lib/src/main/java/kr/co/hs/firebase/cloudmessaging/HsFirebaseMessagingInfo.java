package kr.co.hs.firebase.cloudmessaging;

import android.util.Log;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Bae on 2016-12-25.
 */

public class HsFirebaseMessagingInfo {
    public static final String HOST_IID = "https://iid.googleapis.com/iid/info/%s?details=true";
    public static final String ATTEST_STATUS_UNKNOWN = "UNKNOWN";
    public static final String CONNECTION_TYPE_MOBILE = "MOBILE";
    public static final String PLATFORM_ANDROID = "ANDROID";

    String applicationVersion;
    String connectDate;
    String attestStatus;
    String application;
    String scope;
    String authorizedEntity;
    String connectionType;
    String appSigner;
    String platform;
    List<Topic> topics;

    public static final String EXTRA_APPLICATION_VERSION = "applicationVersion";
    public static final String EXTRA_CONNECT_DATE = "connectDate";
    public static final String EXTRA_ATTEST_STATUS = "attestStatus";
    public static final String EXTRA_APPLICATION = "application";
    public static final String EXTRA_SCOPE = "scope";
    public static final String EXTRA_AUTHORIZED_ENTITY = "authorizedEntity";
    public static final String EXTRA_CONNECTION_TYPE = "connectionType";
    public static final String EXTRA_APP_SIGNER = "appSigner";
    public static final String EXTRA_PLATFORM = "platform";

    public HsFirebaseMessagingInfo(String response) throws ParseException {
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(response);

        applicationVersion = getString(object, EXTRA_APPLICATION_VERSION);
        connectDate = getString(object, EXTRA_CONNECT_DATE);
        attestStatus = getString(object, EXTRA_ATTEST_STATUS);
        application = getString(object, EXTRA_APPLICATION);
        scope = getString(object, EXTRA_SCOPE);
        authorizedEntity = getString(object, EXTRA_AUTHORIZED_ENTITY);
        connectionType = getString(object, EXTRA_CONNECTION_TYPE);
        appSigner = getString(object, EXTRA_APP_SIGNER);
        platform = getString(object, EXTRA_PLATFORM);

        JSONObject objRel = getObject(object, "rel");
        JSONObject objTopics = getObject(objRel, "topics");
        if(objTopics != null){
            topics = new ArrayList<>();
            Iterator<String> topicsIterator = objTopics.keySet().iterator();
            while(topicsIterator.hasNext()){
                String topic = topicsIterator.next();
                JSONObject objTopicInfo = getObject(objTopics, topic);
                topics.add(new Topic(this, topic, objTopicInfo));
            }
        }
        Log.d("aa","aa");
    }



    String getString(JSONObject object, String key){
        String result = null;
        try{
            result = (String) object.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    JSONObject getObject(JSONObject object, String key){
        JSONObject result = null;
        try{
            result = (JSONObject) object.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public String getApplicationVersion() {
        return applicationVersion;
    }

    public String getConnectDate() {
        return connectDate;
    }

    public String getAttestStatus() {
        return attestStatus;
    }

    public String getApplication() {
        return application;
    }

    public String getScope() {
        return scope;
    }

    public String getAuthorizedEntity() {
        return authorizedEntity;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public String getAppSigner() {
        return appSigner;
    }

    public String getPlatform() {
        return platform;
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public static class Topic{
        public static final String EXTRA_ADD_DATE = "addDate";

        String topic;
        String addDate;
        public Topic(HsFirebaseMessagingInfo info, String topic, JSONObject objTopicInfo) {
            this.topic = topic;
            addDate = info.getString(objTopicInfo, EXTRA_ADD_DATE);
        }

        public String getTopic() {
            return topic;
        }

        public String getAddDate() {
            return addDate;
        }
    }


    public interface OnSendResultListener{
        void onSuccess(long message_id);
        void onFail(String message);
    }
}
