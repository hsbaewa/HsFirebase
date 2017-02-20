package kr.co.hs.firebase;


import kr.co.hs.app.IHsService;

/**
 * Created by Bae on 2016-12-25.
 */

public interface IHsFirebaseService extends IHsService, IHsFirebaseComponent {
    IHsFirebaseApplication getHsFirebaseApplication();
}
