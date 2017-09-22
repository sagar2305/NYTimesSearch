package com.nytsearch.nytimessearch.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nytsearch.nytimessearch.R;
import com.nytsearch.nytimessearch.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by SagarMutha on 9/21/17.
 */

public class ArticleArrayAdapter extends ArrayAdapter<Article> {

    public ArticleArrayAdapter(Context context, List<Article> articles) {
        super(context, android.R.layout.simple_list_item_1, articles);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // get the data item for the current position
        Article article = this.getItem(position);

        // check to see if the existing view is being reused or recycled
        // not using a recycled view -> inflate the layout
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_article_result, parent, false);
        }

        //find the image view
        ImageView imageView = convertView.findViewById(R.id.ivImage);

        // clear out the recycled image from convertview from last time
        imageView.setImageResource(0);

        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        tvTitle.setText(article.getHeadline());

        // populate the thumbnail image
        // remote download image in the background
        String thumbnail = article.getThumbnail();
        if (!TextUtils.isEmpty(thumbnail)) {
            Picasso.with(getContext()).load(thumbnail).into(imageView);
        }

        return convertView;
    }
}
