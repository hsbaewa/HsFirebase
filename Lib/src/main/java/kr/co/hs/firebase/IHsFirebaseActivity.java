package kr.co.hs.firebase;


import kr.co.hs.app.IHsActivity;
import kr.co.hs.firebase.app.HsFirebaseApplication;

/**
 * Created by Bae on 2016-12-25.
 */

public interface IHsFirebaseActivity extends IHsActivity, IHsFirebaseComponent {
    HsFirebaseApplication getHsFirebaseApplication();
}
