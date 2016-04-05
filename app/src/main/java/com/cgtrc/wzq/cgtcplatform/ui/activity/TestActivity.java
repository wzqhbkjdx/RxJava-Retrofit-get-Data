package com.cgtrc.wzq.cgtcplatform.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cgtrc.wzq.cgtcplatform.R;
import com.cgtrc.wzq.cgtcplatform.model.RealmNewsItem;
import com.cgtrc.wzq.cgtcplatform.utils.DB;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bym on 16/4/5.
 */
public class TestActivity extends Activity {

    @Bind(R.id.bt_get_db)
    protected Button bt_get_db;
    @Bind(R.id.tv_result)
    protected TextView tv_result;

    private List<RealmNewsItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_layout);
        ButterKnife.bind(this);


        bt_get_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer sb = new StringBuffer();
                list = DB.findAll(RealmNewsItem.class);
                if(list.isEmpty()) {
                    Toast.makeText(TestActivity.this,"数据库为空",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TestActivity.this,"获取数据库数据成功",Toast.LENGTH_SHORT).show();
                }
                for(RealmNewsItem item : list) {
                    sb.append(item.getTitle());
                    Log.i("TestActivity",item.getTitle());
                }
                tv_result.setText(sb.toString());
            }
        });
    }
}
