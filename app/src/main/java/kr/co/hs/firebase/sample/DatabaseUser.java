package kr.co.hs.firebase.sample;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.Map;

import kr.co.hs.firebase.database.FirebaseDatabaseObject;

/**
 * 생성된 시간 2017-01-18, Bae 에 의해 생성됨
 * 프로젝트 이름 : HsFirebase
 * 패키지명 : kr.co.hs.firebase.sample
 */

@IgnoreExtraProperties
public class DatabaseUser extends FirebaseDatabaseObject{

    public String hsUserName;
    public String hsPassword;

    public DatabaseUser(String hsUserName, String hsPassword) {
        super();
        this.hsUserName = hsUserName;
        this.hsPassword = hsPassword;
    }

    @Override
    public Map<String, Object> toMap() {
        return null;
    }

}
