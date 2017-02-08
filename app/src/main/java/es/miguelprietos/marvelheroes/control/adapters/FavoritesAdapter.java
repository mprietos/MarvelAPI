package es.miguelprietos.marvelheroes.control.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.List;

import es.miguelprietos.marvelheroes.R;
import es.miguelprietos.marvelheroes.control.callbacks.CharacterListCallBack;
import es.miguelprietos.marvelheroes.database.CharacterItem;
import es.miguelprietos.marvelheroes.database.DataBaseActions;
import es.miguelprietos.marvelheroes.domain.classes.Characters;
import es.miguelprietos.marvelheroes.utils.Utils;


public class FavoritesAdapter extends RecyclerView.Adapter<CommonViewHolder<Characters>>{
    private List<CharacterItem> mValues;
    private final Context mContext;

    public FavoritesAdapter(Context context, List<CharacterItem> items) {
        mValues = items;
        mContext = context;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_character, parent, false);
        return new CommonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CommonViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);


        holder.name.setText(mValues.get(position).getName());
        String description = mValues.get(position).getDescription();

        holder.subname.setText(description);
        final String urlImage = mValues.get(position).getThumbnail();

            Utils.showRoundedImage(mContext, urlImage, holder.avatar);


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void fillData(List<CharacterItem> characters) {
        mValues = (characters);
    }


}
