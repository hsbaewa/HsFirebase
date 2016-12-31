package kr.co.hs.firebase.sample;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;

import kr.co.hs.firebase.app.HsFirebaseRemoteConfigActivity;
import kr.co.hs.firebase.remoteconfig.HsFirebaseRemoteConfig;

/**
 * Created by Bae on 2016-12-31.
 */

public class RemoteConfigSampleActivity extends HsFirebaseRemoteConfigActivity {
    private static final String VALUE_TEXTVIEW = "value_textview";
    private static final String VALUE_EDIT_HINT = "value_edit_hint";
    private static final String VALUE_BUTTON_TEXT = "value_button_text";
    private static final int HD_SET_VALUE = 100;


    private Toolbar mToolbar;
    TextView textView;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_remoteconfigsample);
        super.onCreate(savedInstanceState);

        mToolbar = (Toolbar) findViewById(R.id.Toolbar);
        if(mToolbar != null)
            setSupportActionBar(mToolbar);

        textView = (TextView) findViewById(R.id.TextView);
        editText = (EditText) findViewById(R.id.EditText);
        button = (Button) findViewById(R.id.Button);

        textView.setText(getRemoteConfigString(VALUE_TEXTVIEW));
        editText.setHint(getRemoteConfigString(VALUE_EDIT_HINT));
        button.setText(getRemoteConfigString(VALUE_BUTTON_TEXT));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setFirebaseAnalyticsCurrentScreen(this, "RemoteConfigSampleActivity", null);
    }

    @Override
    protected void setDefaults(Map<String, Object> map) {
        map.put(VALUE_TEXTVIEW, "default");
        map.put(VALUE_EDIT_HINT, "default");
        map.put(VALUE_BUTTON_TEXT, "default");
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(super.handleMessage(msg))
            return true;

        switch (msg.what){
            case HD_SET_VALUE:{

                Bundle data = msg.getData();
                String textview = data.getString(VALUE_TEXTVIEW);
                String edit = data.getString(VALUE_EDIT_HINT);
                String button = data.getString(VALUE_BUTTON_TEXT);

                textView.setText(textview);
                editText.setHint(edit);
                this.button.setText(button);

                return true;
            }
            default:return false;
        }
    }

    @Override
    public void onSuccessfulActivateFetched(HsFirebaseRemoteConfig remoteConfig) {
        String btnText = getRemoteConfigString(VALUE_BUTTON_TEXT);
        String textviewText = getRemoteConfigString(VALUE_TEXTVIEW);
        String editHint = getRemoteConfigString(VALUE_EDIT_HINT);

        Bundle bundle = new Bundle();
        bundle.putString(VALUE_BUTTON_TEXT, btnText);
        bundle.putString(VALUE_TEXTVIEW, textviewText);
        bundle.putString(VALUE_EDIT_HINT, editHint);
        sendMessage(HD_SET_VALUE, bundle);
    }

    @Override
    public void onFailureActivateFetched(HsFirebaseRemoteConfig remoteConfig) {

    }
}
