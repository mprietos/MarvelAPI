package es.miguelprietos.marvelheroes.control.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import es.miguelprietos.marvelheroes.R;


public class CommonViewHolder<T> extends RecyclerView.ViewHolder  {
    public final View mView;
    public final TextView name;
    public final TextView subname;
    public final ImageView avatar ;
    public final ImageView isFavorite;
    public final LinearLayout allData;
    public T mItem;

    public CommonViewHolder(View view) {
        super(view);
        mView = view;
        subname = (TextView) view.findViewById(R.id.subname);
        name = (TextView) view.findViewById(R.id.name);
        avatar = (ImageView) view.findViewById(R.id.avatar );
        isFavorite = (ImageView) view.findViewById(R.id.isFavorite);
        allData = (LinearLayout) view.findViewById(R.id.allData);
    }

    @Override
    public String toString() {
        return super.toString() + " '" + name.getText() + "'";
    }
}