package com.balaji.countrydetaildemo.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.balaji.countrydetaildemo.R;
import com.balaji.countrydetaildemo.model.Country;
import com.balaji.countrydetaildemo.model.Row;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

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
    public void onBindViewHolder(final CountryDetailAdapter.ViewHolder holder, int position) {
        Row row = data.getRows().get(position);
        holder.click(row, listener);

        holder.contentTitle.setText(row.getTitle());
        holder.contentDesc.setText(row.getDescription());

        if(TextUtils.isEmpty(row.getImageHref())){
            holder.contentImage.setVisibility(View.GONE);
        }else {
            holder.contentImage.setVisibility(View.GONE);
            Glide.with(context)
                    .load(row.getImageHref())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            holder.contentImage.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            holder.contentImage.setVisibility(View.VISIBLE);
                            return false;
                        }
                    })
                    .into(holder.contentImage);
        }
    }

    @Override
    public int getItemCount() {
        return data.getRows().size();
    }

    public interface OnItemClickListener {
        void onClick(Row row);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.content_title)
        TextView contentTitle;

        @BindView(R.id.content_desc)
        TextView contentDesc;

        @BindView(R.id.content_image)
        ImageView contentImage;

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
