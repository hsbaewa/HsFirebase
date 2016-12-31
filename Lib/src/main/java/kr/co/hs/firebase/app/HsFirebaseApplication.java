package kr.co.hs.firebase.app;

import com.google.firebase.iid.FirebaseInstanceId;

import kr.co.hs.app.HsApplication;
import kr.co.hs.firebase.IHsFirebaseApplication;

/**
 * Created by Bae on 2016-12-25.
 */

public class HsFirebaseApplication extends HsApplication implements IHsFirebaseApplication {

    private String mFirebaseToken = null;

    @Override
    public void init() {
        super.init();
        mFirebaseToken = FirebaseInstanceId.getInstance().getToken();
    }

    @Override
    public String getFirebaseToken() {
        return this.mFirebaseToken;
    }
    void setFirebaseToken(String token){
        this.mFirebaseToken = token;
    }
}
