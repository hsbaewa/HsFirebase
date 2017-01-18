package kr.co.hs.firebase.app;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import kr.co.hs.util.Logger;

/**
 * 생성된 시간 2017-01-18, Bae 에 의해 생성됨
 * 프로젝트 이름 : HsFirebase
 * 패키지명 : kr.co.hs.firebase.app
 */

public abstract class HsFirebaseDatabaseActivity extends HsFirebaseActivity implements DatabaseReference.CompletionListener, ChildEventListener{

    @Override
    public Task<Void> updateChildren(Map<String, Object> map) {
        super.updateChildren(map, this);
        return null;
    }

    public void addChildEventListener(DatabaseReference reference){
        reference.addChildEventListener(this);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    @Override
    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
    }

    @Override
    public void onChildRemoved(DataSnapshot dataSnapshot) {
    }

    @Override
    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
    }

    @Override
    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
    }
}
