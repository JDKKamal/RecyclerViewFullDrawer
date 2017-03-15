package com.jdkgroup.recyclerviewfulldrawer;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class AdapterDrawer extends RecyclerView.Adapter<AdapterDrawer.MyViewHolder> {
    List<ModelDrawerMenu> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    private OnItemSelecteListener mListener;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private int lastPosition = -1;

    public AdapterDrawer(Context context, List<ModelDrawerMenu> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;

        mPref = context.getSharedPreferences("person", Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_drawer_menu, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ModelDrawerMenu current = data.get(position);
        holder.title.setText(current.getTitle());

        if (data.get(position).isSelected()) {
            holder.list_row.setBackgroundColor(Color.parseColor("#D4D4D4"));
        } else {
            holder.list_row.setBackgroundColor(Color.TRANSPARENT);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private RelativeLayout list_row;

        public MyViewHolder(View itemView) {
            super(itemView);

            list_row = (RelativeLayout) itemView.findViewById(R.id.list_row);
            title = (TextView) itemView.findViewById(R.id.title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemSelected(view, getAdapterPosition());

                    try {
                        if (data.size() > 1) {
                            data.get(mPref.getInt("position", 0)).setSelected(false);
                            mEditor.putInt("position", getAdapterPosition());
                            mEditor.commit();
                        }
                        data.get(getAdapterPosition()).setSelected(true);
                        notifyDataSetChanged();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void setOnItemClickLister(OnItemSelecteListener mListener) {
        this.mListener = mListener;
    }

    public interface OnItemSelecteListener {
        public void onItemSelected(View v, int position);
    }
}