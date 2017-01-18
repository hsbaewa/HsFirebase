package kr.co.hs.firebase.database;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Map;

/**
 * 생성된 시간 2017-01-18, Bae 에 의해 생성됨
 * 프로젝트 이름 : HsFirebase
 * 패키지명 : kr.co.hs.firebase.database
 */
@IgnoreExtraProperties
public abstract class FirebaseDatabaseObject {

    public FirebaseDatabaseObject(){
    }

    @Exclude
    public abstract Map<String, Object> toMap();
}
