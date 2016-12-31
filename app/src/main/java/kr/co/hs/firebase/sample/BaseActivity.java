package kr.co.hs.firebase.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import kr.co.hs.firebase.app.HsFirebaseActivity;

/**
 * Created by Bae on 2016-12-31.
 */

public class BaseActivity extends HsFirebaseActivity {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mToolbar = (Toolbar) findViewById(R.id.Toolbar);
        if(mToolbar != null)
            setSupportActionBar(mToolbar);
    }
}
