package com.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.allnytnews.R;

import java.util.List;

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.MyViewHolder> {

    private List<PojoResults> pojoResultsList;
    private ImageLoader mImageLoader;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView text_content, text_provider, text_providerby, text_publishedate;
        private NetworkImageView mNetworkImageView;

        public MyViewHolder(View view) {
            super(view);
            text_content = view.findViewById(R.id.text_content);
            text_provider = view.findViewById(R.id.text_provider);
            text_providerby = view.findViewById(R.id.text_providerby);
            text_publishedate = view.findViewById(R.id.text_publishedate);
            mNetworkImageView = view.findViewById(R.id.img_course);
        }
    }


    public ResultsAdapter(List<PojoResults> moviesList, Context context) {
        this.pojoResultsList = moviesList;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listadapter_homepage, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        mImageLoader = VolleyImageRequestQueue.getInstance(context).getImageLoader();

        PojoResults pojoResults = pojoResultsList.get(position);
        holder.text_content.setText(Html.fromHtml(pojoResults.getTitle()));

        if (pojoResults.getByline().equals("")){
            holder.text_provider.setText("No Author");
        }else {
            holder.text_provider.setText(pojoResults.getByline());
        }
        holder.text_providerby.setText(pojoResults.getSource());
        holder.text_publishedate.setText(pojoResults.getPublished_date());
        mImageLoader.get(pojoResults.getBrandLogoURL(), ImageLoader.getImageListener(holder.mNetworkImageView,
                R.mipmap.bg_brand, R.drawable.ic_launcher_foreground));
        holder.mNetworkImageView.setImageUrl(pojoResults.getBrandLogoURL(), mImageLoader);
    }

    @Override
    public int getItemCount() {
        return pojoResultsList.size();
    }
}
