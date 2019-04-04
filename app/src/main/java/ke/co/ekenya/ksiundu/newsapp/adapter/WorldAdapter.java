package ke.co.ekenya.ksiundu.newsapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ke.co.ekenya.ksiundu.newsapp.R;
import ke.co.ekenya.ksiundu.newsapp.model.WorldModel;
import ke.co.ekenya.ksiundu.newsapp.ui.HeadLineDetailActivity;

public class WorldAdapter extends RecyclerView.Adapter<WorldAdapter.WorldHolder> {
    private Context mCtx;
    private ArrayList<WorldModel> mHeadLineModel;
    public WorldAdapter(ArrayList<WorldModel> mWorldList) {
        this.mHeadLineModel = mWorldList;
    }

    @NonNull
    @Override
    public WorldAdapter.WorldHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.single_world_news,
                viewGroup,
                false
        );
        return new WorldHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final WorldAdapter.WorldHolder worldHolder, int i) {
        worldHolder.bindWorld(mHeadLineModel.get(i));
        final String title = mHeadLineModel.get(i).getTitle();
        final String image = mHeadLineModel.get(i).getImage();
        final String content = mHeadLineModel.get(i).getContent();
        worldHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mHeadLineDetail =  new Intent(mCtx, HeadLineDetailActivity.class);
                mHeadLineDetail.putExtra("title", title);
                mHeadLineDetail.putExtra("image", image);
                mHeadLineDetail.putExtra("content", content);
                worldHolder.itemView.getContext().startActivity(mHeadLineDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHeadLineModel.size();
    }

    class WorldHolder extends RecyclerView.ViewHolder {
        TextView mTitle, mSource;
        ImageView mImageBg;
        WorldHolder(@NonNull View itemView) {
            super(itemView);
            mCtx = itemView.getContext();
            mTitle = itemView.findViewById(R.id.textViewWorldTitle);
            mSource = itemView.findViewById(R.id.textViewWorldSource);
            mImageBg = itemView.findViewById(R.id.imageViewWorldNews);
        }

        void bindWorld(WorldModel worldModel) {
            Picasso.get().load(worldModel.getImage()).fit().centerCrop().into(mImageBg);
            mTitle.setText(worldModel.getName());
            mSource.setText(worldModel.getName());
        }
    }
}
