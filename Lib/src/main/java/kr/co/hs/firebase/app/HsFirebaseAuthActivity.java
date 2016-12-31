package kr.co.hs.firebase.app;

import android.os.Bundle;
import android.support.annotation.Nullable;

import kr.co.hs.firebase.auth.HsFirebaseAuth;

/**
 * Created by Bae on 2016-12-31.
 */

public abstract class HsFirebaseAuthActivity extends HsFirebaseActivity implements HsFirebaseAuth.OnFirebaseAuthResultListener{

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
}
