package kr.co.hs.firebase;


import kr.co.hs.app.IHsActivity;

/**
 * Created by Bae on 2016-12-25.
 */

public interface IHsFirebaseActivity extends IHsActivity, IHsFirebaseComponent {
    IHsFirebaseApplication getHsFirebaseApplication();
}
