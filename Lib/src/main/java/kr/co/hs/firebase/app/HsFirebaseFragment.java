package kr.co.hs.firebase.app;


import kr.co.hs.app.HsFragment;
import kr.co.hs.firebase.IHsFirebaseApplication;
import kr.co.hs.firebase.IHsFirebaseFragment;

/**
 * Created by Bae on 2016-12-25.
 */

public abstract class HsFirebaseFragment extends HsFragment implements IHsFirebaseFragment {

    @Override
    public HsFirebaseApplication getHsFirebaseApplication() {
        HsFirebaseApplication application = (HsFirebaseApplication) getHsApplication();
        return application;
    }

    @Override
    public String getFirebaseToken() {
        IHsFirebaseApplication application = getHsFirebaseApplication();
        if(application != null){
            return application.getFirebaseToken();
        }
        return null;
    }
}
