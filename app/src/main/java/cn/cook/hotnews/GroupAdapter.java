package cn.cook.hotnews;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2016/11/3.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupHolder> implements View.OnClickListener {


    private Context mContext;
    private List<GroupBean> mResData;
    private OnItemClickListener mOnItemClickListener;
    private static SelectData mLastData;
    private int height;

    public GroupAdapter(Context mContext) {
        this.mContext = mContext;
        mResData = new ArrayList<>();
    }


    @Override
    public GroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_group, null);
        return new GroupHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GroupAdapter.GroupHolder holder, int position) {
        GroupBean bean = mResData.get(position);
        holder.tevDate.setText(bean.getDate());


        SelectData data = new SelectData(bean, position);
        holder.tevDate.setOnClickListener(this);
        holder.tevDate.setTag(data);

        if (bean.getItemStatus()) {
            mLastData = data;
            holder.frmDetails.setVisibility(View.VISIBLE);
            height=holder.itemView.getMeasuredHeight();
        } else {
            holder.frmDetails.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return mResData.size();
    }

    public void addData(ArrayList<GroupBean> list) {
        if (list != null && list.size() > 0) {
            mResData.addAll(list);
        }
    }

    public void changeStatus(int position) {
        if (position >= mResData.size()) {
            return;
        }
        if(mLastData!=null){
           if(mLastData.position==position&&mLastData.bean.getItemStatus())return;
        }
        changeItem(new SelectData(mResData.get(position), position));

    }

    @Override
    public void onClick(View v) {

        if (v instanceof TextView) {
            SelectData data = (SelectData) v.getTag();
            if (data != null) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(data);
                }
                changeItem(data);
            }
        }
    }

    private void changeItem(SelectData data) {
        if(mLastData!=null&&data.position==mLastData.position){
            mLastData.bean.setItemStatus(!mLastData.bean.getItemStatus());
            notifyItemChanged(mLastData.position);
            return;
        }
        if (mLastData != null) {
            mLastData.bean.setItemStatus(false);
            notifyItemChanged(mLastData.position);
        }
        GroupBean bean = data.bean;
        bean.setItemStatus(true);
        notifyItemChanged(data.position);





    }

    public interface OnItemClickListener {
        void onClick(SelectData bundle);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    class GroupHolder extends RecyclerView.ViewHolder {
        private TextView tevDate;
        private FrameLayout frmDetails;

        public GroupHolder(View itemView) {
            super(itemView);
            tevDate = (TextView) itemView.findViewById(R.id.tev_date);
            frmDetails = (FrameLayout) itemView.findViewById(R.id.frm_detail);
        }
    }

    class SelectData {
        public SelectData(GroupBean bean, int position) {
            this.bean = bean;
            this.position = position;
        }

        GroupBean bean;
        int position;
    }
}
