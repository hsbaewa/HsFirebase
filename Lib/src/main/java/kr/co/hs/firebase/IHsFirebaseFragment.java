package kr.co.hs.firebase;


import kr.co.hs.app.IHsFragment;

/**
 * Created by Bae on 2016-12-25.
 */

public interface IHsFirebaseFragment extends IHsFragment, IHsFirebase {
    IHsFirebaseApplication getHsFirebaseApplication();
}
