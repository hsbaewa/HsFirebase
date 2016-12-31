package kr.co.hs.firebase.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

import kr.co.hs.util.Logger;

/**
 * Created by Bae on 2016-12-31.
 */

public abstract class HsFirebaseGoogleAuthActivity extends HsFirebaseAuthActivity implements GoogleApiClient.OnConnectionFailedListener{

    private static final int RC_GOOGLE_SIGN_IN = 10000;

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getWebClientId())
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, options)
                .build();

        mGoogleApiClient.connect();
    }


    protected GoogleApiClient getGoogleApiClient(){
        return this.mGoogleApiClient;
    }

    protected abstract String getWebClientId();

    public void signInWithGoogleCredential(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(getGoogleApiClient());
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
    }

    @Override
    public void signOut() {
        getGoogleApiClient().connect();
        Auth.GoogleSignInApi.signOut(getGoogleApiClient()).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                Logger.d("a");
                HsFirebaseGoogleAuthActivity.super.signOut();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case RC_GOOGLE_SIGN_IN:{

                GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                Status status = result.getStatus();
                if (result.isSuccess()) {
                    // Google Sign In was successful, authenticate with Firebase
                    GoogleSignInAccount account = result.getSignInAccount();
                    AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                    signInWithCredential(credential);
                } else {
                    // Google Sign In failed, update UI appropriately
                    // [START_EXCLUDE]
//                    updateUI(null);
                    // [END_EXCLUDE]
                    onFailure(null);
                }

                break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
