package kr.co.hs.firebase.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvingResultCallbacks;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import kr.co.hs.firebase.app.HsFirebaseAuthActivity;
import kr.co.hs.firebase.app.HsFirebaseGoogleAuthActivity;
import kr.co.hs.util.Logger;

/**
 * Created by Bae on 2016-12-31.
 */

public class GoogleAuthSampleActivity extends HsFirebaseGoogleAuthActivity implements GoogleApiClient.OnConnectionFailedListener,View.OnClickListener {

    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_googleauthsample);
        super.onCreate(savedInstanceState);

        btn = (Button) findViewById(R.id.Button);

        btn.setOnClickListener(this);

        if(getFirebaseUser() == null){
            btn.setText("로그인");
        }else{
            btn.setText(getFirebaseUser().getDisplayName());
        }
    }

    @Override
    protected String getWebClientId() {
        return getString(R.string.default_web_client_id);
    }

    @Override
    public void onSuccessful(AuthResult authResult) {
        String name = authResult.getUser().getDisplayName();
        String email = authResult.getUser().getEmail();

        btn.setText(name);

        Logger.d("a");
    }

    @Override
    public void onFailure(Exception exception) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Button:{

                if(getFirebaseUser() == null){
                    signInWithGoogleCredential();
                }else{
                    signOut();
                }

                break;
            }
        }
    }

    @Override
    public void onSignOut() {
        btn.setText("로그인");
    }
}
