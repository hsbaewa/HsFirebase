package kr.co.hs.firebase.app;


import kr.co.hs.app.HsActivity;
import kr.co.hs.firebase.IHsFirebaseActivity;
import kr.co.hs.firebase.IHsFirebaseApplication;

/**
 * Created by Bae on 2016-12-25.
 */

public class HsFirebaseActivity extends HsActivity implements IHsFirebaseActivity {

    @Override
    public String getFirebaseToken() {
        HsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null)
            return application.getFirebaseToken();

        return null;
    }

    @Override
    public HsFirebaseApplication getHsFirebaseApplication() {
        HsFirebaseApplication application = (HsFirebaseApplication) getHsApplication();
        return application;
    }
}
