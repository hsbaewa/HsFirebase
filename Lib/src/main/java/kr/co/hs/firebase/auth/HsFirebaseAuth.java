package kr.co.hs.firebase.auth;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Bae on 2016-12-31.
 */

public class HsFirebaseAuth extends FirebaseAuth{

    private static HsFirebaseAuth instance = null;
    public static HsFirebaseAuth getInstance(){
        if(instance == null)
            instance = new HsFirebaseAuth(FirebaseApp.getInstance());
        return instance;
    }

    public HsFirebaseAuth(FirebaseApp firebaseApp) {
        super(firebaseApp);
    }

    public void createUserWithEmailAndPassword(@NonNull String email, @NonNull String password, final OnFirebaseAuthResultListener listener) {
        super.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(listener != null)
                        listener.onSuccessful(task.getResult());
                }else{
                    if(listener != null)
                        listener.onFailure(task.getException());
                }
            }
        });
    }

    public void signInWithEmailAndPassword(@NonNull String email, @NonNull String password, final OnFirebaseAuthResultListener listener) {
        super.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(listener != null)
                        listener.onSuccessful(task.getResult());
                }else{
                    if(listener != null)
                        listener.onFailure(task.getException());
                }
            }
        });
    }

    public interface OnFirebaseAuthResultListener{
        void onSuccessful(AuthResult authResult);
        void onFailure(Exception exception);
    }
}
