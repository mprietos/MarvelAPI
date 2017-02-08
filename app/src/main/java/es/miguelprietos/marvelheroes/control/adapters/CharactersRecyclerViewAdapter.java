package es.miguelprietos.marvelheroes.control.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import es.miguelprietos.marvelheroes.R;
import es.miguelprietos.marvelheroes.control.callbacks.CharacterListCallBack;
import es.miguelprietos.marvelheroes.database.DataBaseActions;
import es.miguelprietos.marvelheroes.domain.classes.Characters;
import es.miguelprietos.marvelheroes.utils.Utils;


public class CharactersRecyclerViewAdapter extends RecyclerView.Adapter<CommonViewHolder<Characters>>{
    private final CharacterListCallBack mListener;
    private final DataBaseActions mActions;
    private List<Characters> mValues;

    private final Context mContext;

    public CharactersRecyclerViewAdapter(Context context, List<Characters> items, CharacterListCallBack listener, DataBaseActions actions) {
        mValues = items;
        mContext = context;
        mListener = listener;
        mActions = actions;
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
        if (mValues.get(position).getDescription().length() > 40){
            description = description.substring(0,40) + "...";
        }
        holder.subname.setText(description);
        final String urlImage = mValues.get(position).getThumbnail().getPath() + "." + mValues.get(position).getThumbnail().getExtension();

        if (mValues.get(position).isSelected()){
            holder.avatar.setImageResource(R.drawable.ic_success);
        }else {
            Utils.showRoundedImage(mContext, urlImage, holder.avatar);
        }
        holder.allData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClickCharacter(mValues.get(position));
            }
        });

        holder.isFavorite.setVisibility(View.GONE);
        if (mActions.isFavorite(mValues.get(position).getId())){
            holder.isFavorite.setVisibility(View.VISIBLE);
            holder.isFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mActions.removeFavorite(mValues.get(position).getId());
                    holder.isFavorite.setVisibility(View.GONE);
                }
            });
        }

        holder.avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mValues.get(position).isSelected()){
                    holder.avatar.setImageResource(R.drawable.ic_success);
                    changeToImage(urlImage, holder.avatar);
                    mValues.get(position).setSelected(false);
                    mListener.selectedCharacter(position, false);
                }else {
                    Utils.showRoundedImage(mContext, urlImage, holder.avatar);
                    changeTo(R.drawable.ic_success, holder.avatar);
                    mValues.get(position).setSelected(true);
                    mListener.selectedCharacter(position, true);
                }

            }
        });

    }

    private  void changeToImage(final String url, final ImageView avatar) {
        Animation flipAnim = AnimationUtils.loadAnimation(mContext, R.anim.flip);
        avatar.startAnimation(flipAnim);
        flipAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Utils.showRoundedImage(mContext, url, avatar);
                notifyDataSetChanged();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void changeTo(final int resId, final ImageView avatar) {
        Animation flipAnim = AnimationUtils.loadAnimation(mContext, R.anim.flip);
        avatar.startAnimation(flipAnim);
        flipAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                avatar.setImageResource(resId);
                notifyDataSetChanged();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void fillData(List<Characters> characters) {
        mValues = (characters);
    }


}
