package com.balaji.countrydetaildemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balaji.countrydetaildemo.R;
import com.balaji.countrydetaildemo.model.Country;
import com.balaji.countrydetaildemo.model.Row;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountryDetailAdapter extends RecyclerView.Adapter<CountryDetailAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private Country data;
    private Context context;

    public CountryDetailAdapter(Context context, Country data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public CountryDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryDetailAdapter.ViewHolder holder, int position) {
        Row row = data.getRows().get(position);
        holder.click(row, listener);

        holder.tvTitle.setText(row.getTitle());
        holder.tvDesc.setText(row.getDescription());

        Glide.with(context)
                .load(row.getImageHref())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return data.getRows().size();
    }

    public interface OnItemClickListener {
        void onClick(Row row);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView tvTitle;

        @BindView(R.id.desc)
        TextView tvDesc;

        @BindView(R.id.img)
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void click(final Row row, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(row);
                }
            });
        }
    }
}
