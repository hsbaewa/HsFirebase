package kr.co.hs.firebase.app;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.firebase.auth.AuthCredential;

import kr.co.hs.firebase.auth.HsFirebaseAuth;

/**
 * Created by Bae on 2016-12-31.
 */

public abstract class HsFirebaseAuthActivity extends HsFirebaseActivity implements HsFirebaseAuth.OnFirebaseAuthResultListener, HsFirebaseAuth.OnFirebaseSignOutResultListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void createUserWithEmailAndPassword(String email, String password) {
        super.createUserWithEmailAndPassword(email, password, this);
    }

    protected void signInWithEmailAndPassword(String email, String password) {
        super.signInWithEmailAndPassword(email, password, this);
    }

    public void signInWithCredential(AuthCredential credential) {
        super.signInWithCredential(credential, this);
    }

    @Override
    public void signOut() {
        super.signOut(this);
    }
}
