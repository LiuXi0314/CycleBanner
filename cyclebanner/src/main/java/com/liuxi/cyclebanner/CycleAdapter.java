package com.liuxi.cyclebanner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @Author liuxi
 * @Email xiaoxixizhizhi@gmail.com
 */
public abstract class CycleAdapter<M, T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {

    private CycleAdapterHelper mHelper = new CycleAdapterHelper();

    protected abstract View createView(@NonNull ViewGroup viewGroup);

    protected abstract T createViewHolder(@NonNull View view);

    protected abstract void onBind(T holder, M model, int position);

    private List<M> mData;

    @NonNull
    @Override
    public T onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = createView(viewGroup);
        mHelper.onCreateViewHolder(viewGroup, view);
        return createViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull T holder, int position) {
        mHelper.onBindViewHolder(holder.itemView, position, getItemCount());
        M m = getItemData(position);
        if (m == null) {
            return;
        }
        onBind(holder, m, position);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public void setData(List<M> list) {
        mData = list;
        notifyDataSetChanged();
    }

    public int getItemRealCount() {
        return mData == null ? 0 : mData.size();
    }

    protected M getItemData(int position) {
        int size = mData.size();
        if (size <= 0) return null;
        int i = position % size;
        if (size <= i) {
            return null;
        }
        return mData.get(i);
    }

}
