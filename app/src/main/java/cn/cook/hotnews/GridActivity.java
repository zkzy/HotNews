package cn.cook.hotnews;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hasee on 2016/11/2.
 */

public class GridActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private GroupAdapter groupAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        mRecyclerView= (RecyclerView) findViewById(R.id.recycleview);
        mRecyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        groupAdapter = new GroupAdapter(this);
        mRecyclerView.setAdapter(groupAdapter);
        ArrayList<GroupBean> beanArrayList = new ArrayList<>();
        for (int i = 0; i <30 ; i++) {
            GroupBean bean =new GroupBean();
            bean.setDate(2016+""+i);
            beanArrayList.add(bean);
        }
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
              if(newState==RecyclerView.SCROLL_STATE_IDLE){
                  int first=  linearLayoutManager.findFirstVisibleItemPosition();
                  int count=linearLayoutManager.getChildCount();
                  groupAdapter.changeStatus(first+count-1);
              }
            }
        });
        groupAdapter.addData(beanArrayList);
        mRecyclerView.smoothScrollToPosition(linearLayoutManager.getItemCount()-1);
        groupAdapter.setOnItemClickListener(new GroupAdapter.OnItemClickListener() {
            @Override
            public void onClick(GroupAdapter.SelectData bundle) {

                Toast.makeText(GridActivity.this, ""+bundle.position, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
