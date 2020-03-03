package com.assosiatedicoding.movieyogiefix.clik;

import android.view.View;

import com.assosiatedicoding.movieyogiefix.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemClickSupport {
    private final RecyclerView mRecylerview;
    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    private ItemClickSupport(RecyclerView recyclerView){
        mRecylerview = recyclerView;
        mRecylerview.setTag(R.id.item_click_support,this);
        mRecylerview.addOnChildAttachStateChangeListener(mAttachListener);
    }
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mOnItemClickListener !=null){
                RecyclerView.ViewHolder holder = mRecylerview.getChildViewHolder(v);
                mOnItemClickListener.onItemClicked(mRecylerview,holder.getAdapterPosition(),v);
            }
        }
    };
    private View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener(){
        @Override
        public boolean onLongClick(View v){
            if (mOnItemClickListener != null){
                RecyclerView.ViewHolder holder = mRecylerview.getChildViewHolder(v);
                return mOnItemLongClickListener.onItemLongClicked(mRecylerview,holder.getAdapterPosition(),v);
            }
            return false;
        }
    };
    private RecyclerView.OnChildAttachStateChangeListener mAttachListener = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(@NonNull View view) {
            if (mOnItemClickListener != null){
                view.setOnClickListener(mOnClickListener);
            }
            if (mOnItemLongClickListener !=null){
                view.setOnClickListener((View.OnClickListener) mOnLongClickListener);
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(@NonNull View view) {

        }
    };
    public static ItemClickSupport addTo(RecyclerView view){
        ItemClickSupport support = (ItemClickSupport)view.getTag(R.id.item_click_support);
        if (support == null){
            support = new ItemClickSupport(view);
        }
        return support;
    }
    public static ItemClickSupport removeFrom(RecyclerView view){
        ItemClickSupport support = (ItemClickSupport)view.getTag(R.id.item_click_support);
        if (support !=null){
            support.detach(view);
        }
        return support;
    }
    public ItemClickSupport setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
        return this;
    }
    public ItemClickSupport setOnItemLongClickListener(OnItemLongClickListener listener){
        mOnItemLongClickListener = listener;
        return this;
    }
    private void detach(RecyclerView view){
        view.removeOnChildAttachStateChangeListener(mAttachListener);
        view.setTag(R.id.item_click_support);
    }
    public interface OnItemClickListener{
        void onItemClicked(RecyclerView recyclerView,int position,View v);
    }
    public interface OnItemLongClickListener{
        boolean onItemLongClicked(RecyclerView recyclerView,int position,View v);
    }
}


