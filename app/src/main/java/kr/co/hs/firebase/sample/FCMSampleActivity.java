package kr.co.hs.firebase.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

import kr.co.hs.firebase.app.HsFirebaseActivity;

/**
 * Created by Bae on 2016-12-31.
 */

public class FCMSampleActivity extends HsFirebaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        RemoteMessage remoteMessage = new RemoteMessage.Builder("384964015838@gcm.googleapis.com")
//                .setMessageId("1")
//                .setData(map).build();


        Map<String, String> map = new HashMap<>();
        map.put("key","data");
        String server = "AAAAWaGktt4:APA91bEefJMUTM_hkEev-3ULLt52T-j7O2HUDlTNN21RI88nCMXu1YOpUE27oYc10MXEQ-3Eq91Ur-CBf3N32w_tuzUrrJEBt8TuEo_2bmvlBmnBGnwiaLYod8EQzpm_ML9fxXlZdN_8g600rpp88wndI1TsD3Cndw";
        send(server, "/topics/lobby", map, null);

    }
}
