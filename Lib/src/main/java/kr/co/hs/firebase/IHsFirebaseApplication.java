package kr.co.hs.firebase;

import kr.co.hs.app.IHsApplication;

/**
 * Created by Bae on 2016-12-25.
 */

public interface IHsFirebaseApplication extends IHsApplication, IHsFirebaseComponent {
    void onFirebaseTokenRefresh(String token);
}
