package kr.co.hs.firebase.sample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

import kr.co.hs.firebase.app.HsFirebaseAuthActivity;
import kr.co.hs.util.Logger;

/**
 * Created by Bae on 2016-12-31.
 */

public class AuthSampleActivity extends HsFirebaseAuthActivity implements View.OnClickListener{

    Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_authsample);
        super.onCreate(savedInstanceState);

        btnLogin = (Button) findViewById(R.id.ButtonLogin);
        btnLogin.setOnClickListener(this);

        String email = "hsbaewa@naver.com";
        String pw = "qwe123";

        signInWithEmailAndPassword(email, pw);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ButtonLogin:{

                break;
            }
        }
    }

    @Override
    public void onSuccessful(AuthResult authResult) {
        String email = authResult.getUser().getEmail();
        Logger.d(email);
    }

    @Override
    public void onFailure(Exception exception) {
        String errorMsg = exception.getMessage();
        Logger.d(errorMsg);
    }
}
