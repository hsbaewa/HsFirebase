package kr.co.hs.firebase.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * 생성된 시간 2017-01-18, Bae 에 의해 생성됨
 * 프로젝트 이름 : HsFirebase
 * 패키지명 : kr.co.hs.firebase.sample
 */

public class StorageSampleActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl("gs://causal-sunspot-91903.appspot.com");

        storageReference.child("test/제목 없음.png");

    }
}
