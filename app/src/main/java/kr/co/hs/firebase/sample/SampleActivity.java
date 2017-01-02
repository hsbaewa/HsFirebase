package kr.co.hs.firebase.sample;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kr.co.hs.widget.recyclerview.HsRecyclerView;


/**
 * Created by Bae on 2016-12-29.
 */
public class SampleActivity extends BaseActivity implements HsRecyclerView.OnItemClickListener{

    HsRecyclerView mRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_sample);
        super.onCreate(savedInstanceState);

        mRecyclerView = (HsRecyclerView) findViewById(R.id.RecyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        mRecyclerView.setAdapter(new SampleAdapter());

        mRecyclerView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setFirebaseAnalyticsCurrentScreen(this, "SampleActivity", null);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onItemClick(HsRecyclerView hsRecyclerView, View view, int i) {
        switch (i){
            case 0:{
                Intent intent = new Intent(getContext(), RemoteConfigSampleActivity.class);
                startActivity(intent);

                Bundle param = new Bundle();
                param.putString("name", "RemoteConfigSampleActivity");
                sendFirebaseAnalyticsLogEvent("Activity", param);
                break;
            }
            case 1:{
                Intent intent = new Intent(getContext(), AnalyticsSampleActivity.class);
                startActivity(intent);

                Bundle param = new Bundle();
                param.putString("name", "AnalyticsSampleActivity");
                sendFirebaseAnalyticsLogEvent("Activity", param);
                break;
            }
            case 2:{
                Intent intent = new Intent(getContext(), AuthSampleActivity.class);
                startActivity(intent);

                Bundle param = new Bundle();
                param.putString("name", "AuthSampleActivity");
                sendFirebaseAnalyticsLogEvent("Activity", param);
                break;
            }
            case 3:{
                Intent intent = new Intent(getContext(), GoogleAuthSampleActivity.class);
                startActivity(intent);

                Bundle param = new Bundle();
                param.putString("name", "GoogleAuthSampleActivity");
                sendFirebaseAnalyticsLogEvent("Activity", param);
                break;
            }
            case 4:{
                Intent intent = new Intent(getContext(), FCMSampleActivity.class);
                startActivity(intent);

                Bundle param = new Bundle();
                param.putString("name", "FCMSampleActivity");
                sendFirebaseAnalyticsLogEvent("Activity", param);
                break;
            }
        }
    }

    class SampleAdapter extends HsRecyclerView.HsAdapter<SampleAdapter.SampleViewHolder>{
        @Override
        public SampleViewHolder onCreateHsViewHolder(ViewGroup viewGroup, int i) {
            return new SampleViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.viewholder_sample, viewGroup, false));
        }

        @Override
        public void onBindHsViewHolder(SampleViewHolder holder, int i, boolean b) {
            switch (i){
                case 0:{
                    holder.textView.setText("RemoteConfigSample");
                    break;
                }
                case 1:{
                    holder.textView.setText("Analytics");
                    break;
                }
                case 2:{
                    holder.textView.setText("Auth");
                    break;
                }
                case 3:{
                    holder.textView.setText("GoogleAuth");
                    break;
                }
                case 4:{
                    holder.textView.setText("FCM");
                    break;
                }
            }
        }

        @Override
        public int getItemCount() {
            return 5;
        }

        class SampleViewHolder extends HsRecyclerView.HsViewHolder{
            TextView textView;
            public SampleViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) findViewById(R.id.TextView);
            }
        }
    }
}
