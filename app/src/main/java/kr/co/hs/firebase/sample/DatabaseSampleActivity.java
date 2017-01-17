package kr.co.hs.firebase.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import kr.co.hs.widget.recyclerview.HsRecyclerView;

/**
 * 생성된 시간 2017-01-17, Bae 에 의해 생성됨
 * 프로젝트 이름 : HsFirebase
 * 패키지명 : kr.co.hs.firebase.sample
 */

public class DatabaseSampleActivity extends BaseActivity {

    private HsRecyclerView mHsRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHsRecyclerView = (HsRecyclerView) findViewById(R.id.RecyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mHsRecyclerView.setLayoutManager(llm);


        Adapter adapter = new Adapter();
        mHsRecyclerView.setAdapter(adapter);

        DatabaseReference mDatabaseReference;

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

    }




    class Adapter extends HsRecyclerView.HsAdapter<Holder>{

        @Override
        public Holder onCreateHsViewHolder(ViewGroup viewGroup, int i) {
            return new Holder(LayoutInflater.from(getContext()).inflate(R.layout.viewholder_database, viewGroup, false));
        }

        @Override
        public void onBindHsViewHolder(Holder holder, int i, boolean b) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }

    class Holder extends HsRecyclerView.HsViewHolder{
        TextView mTextView;
        public Holder(View itemView) {
            super(itemView);
            mTextView = (TextView) findViewById(R.id.TextView);
        }
    }
}
