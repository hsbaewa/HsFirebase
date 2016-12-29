package kr.co.hs.firebase;


import kr.co.hs.firebase.app.HsFirebaseApplication;

/**
 * Created by Bae on 2016-12-25.
 */

public interface IHsFirebaseService extends IHsFirebase {
    HsFirebaseApplication getHsFirebaseApplication();
}
