package com.tencent.qcloud.timchat.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.tencent.TIMGroupDetailInfo;
import com.tencent.qcloud.presentation.presenter.GroupManagerPresenter;
import com.tencent.qcloud.presentation.viewfeatures.GroupInfoView;
import com.tencent.qcloud.timchat.R;
import com.tencent.qcloud.timchat.adapters.ProfileSummaryAdapter;
import com.tencent.qcloud.timchat.model.GroupProfile;
import com.tencent.qcloud.timchat.model.ProfileSummary;

import java.util.ArrayList;
import java.util.List;

public class SearchGroupActivity extends Activity implements GroupInfoView, KeyEvent.Callback{

    private final String TAG = "SearchGroupActivity";

    private GroupManagerPresenter groupManagerPresenter;
    private List<ProfileSummary> list= new ArrayList<>();
    private ProfileSummaryAdapter adapter;
    private EditText searchInput;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_group);
        searchInput = (EditText) findViewById(R.id.inputSearch);
        listView =(ListView) findViewById(R.id.list);
        adapter = new ProfileSummaryAdapter(this, R.layout.item_profile_summary, list);
        listView.setAdapter(adapter);
        groupManagerPresenter = new GroupManagerPresenter(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                list.get(position).onClick(SearchGroupActivity.this);
            }
        });
        TextView tvCancel = (TextView) findViewById(R.id.cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }




    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_ENTER:
//                groupManagerPresenter.searchGroupByID(searchInput.getText().toString());
                groupManagerPresenter.searchGroupByName(searchInput.getText().toString());
                return true;
            default:
                return super.onKeyUp(keyCode, event);
        }
    }

    /**
     * 显示群资料
     *
     * @param groupInfos 群资料信息列表
     */
    @Override
    public void showGroupInfo(List<TIMGroupDetailInfo> groupInfos) {
        list.clear();
        for (TIMGroupDetailInfo item : groupInfos){
            list.add(new GroupProfile(item));
        }
        adapter.notifyDataSetChanged();
    }
}
