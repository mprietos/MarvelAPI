package es.miguelprietos.marvelheroes.control.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;

import java.util.List;

import es.miguelprietos.marvelheroes.R;
import es.miguelprietos.marvelheroes.control.callbacks.ComicListCallBack;
import es.miguelprietos.marvelheroes.domain.classes.Comic;

public class ComicsRecyclerViewAdapter extends RecyclerView.Adapter<CommonViewHolder<Comic>> {

    private final Context mContext;
    private final ComicListCallBack mListener;
    private  List<Comic> mValues;

    public ComicsRecyclerViewAdapter(Context context, List<Comic> items, ComicListCallBack listener) {
        mContext = context;
        mValues = items;
        mListener = listener;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_resource, parent, false);
        return new CommonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.name.setText(mValues.get(position).getTitle());

        holder.subname.setText(mValues.get(position).getDescription());

        String urlImage = mValues.get(position).getThumbnail().getPath() + "." + mValues.get(position).getThumbnail().getExtension();
        Glide.with(mContext).load(urlImage).into(holder.avatar);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickComic(mValues.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void fillData(List<Comic> list) {
        mValues = list;

    }
}
