package cn.cook.hotnews;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;

import static android.R.attr.host;

/**
 * Created by hasee on 2016/11/2.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ImageHoder> {
    private Context mContext;

    public GridAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ImageHoder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageHoder(View.inflate(mContext,R.layout.item_grid,null));
    }

    @Override
    public void onBindViewHolder(GridAdapter.ImageHoder holder, int position) {
        holder.imageView.setImageResource(R.drawable.a);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mContext, GridDetails.class);

                ActivityOptions options =
                        null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    options = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext,
                            Pair.create(view, mContext.getString(R.string.image)),
                            Pair.create(view, mContext.getString(R.string
                    .layout)));
                }
                ((Activity)mContext).startActivityForResult(intent, 200, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 50;
    }


    public class ImageHoder extends RecyclerView.ViewHolder {
        FourThreeImageView imageView;
        public ImageHoder(View itemView) {
            super(itemView);
            imageView= (FourThreeImageView) itemView.findViewById(R.id.image);
        }
    }
}
