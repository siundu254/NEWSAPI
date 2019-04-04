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

public class TechAdapter extends RecyclerView.Adapter<TechAdapter.TechHolder> {
    private Context mCtx;
    private ArrayList<WorldModel> mHeadLineModel;
    public TechAdapter(ArrayList<WorldModel> mWorldList) {
        this.mHeadLineModel = mWorldList;
    }

    @NonNull
    @Override
    public TechAdapter.TechHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.single_tech_new,
                viewGroup,
                false
        );
        return new TechHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final TechAdapter.TechHolder techHolder, int i) {
        techHolder.bindViews(mHeadLineModel.get(i));
        final String title = mHeadLineModel.get(i).getTitle();
        final String image = mHeadLineModel.get(i).getImage();
        final String content = mHeadLineModel.get(i).getContent();
        techHolder.mReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mHeadLineDetail =  new Intent(mCtx, HeadLineDetailActivity.class);
                mHeadLineDetail.putExtra("title", title);
                mHeadLineDetail.putExtra("image", image);
                mHeadLineDetail.putExtra("content", content);
                techHolder.itemView.getContext().startActivity(mHeadLineDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHeadLineModel.size();
    }

    class TechHolder extends RecyclerView.ViewHolder {
        TextView mTitle, mSource, mReadMore;
        ImageView mBgImage;
        TechHolder(@NonNull View itemView) {
            super(itemView);
            mCtx = itemView.getContext();
            mTitle = itemView.findViewById(R.id.textViewTitle);
            mSource = itemView.findViewById(R.id.textViewSource);
            mReadMore = itemView.findViewById(R.id.textReadMore);
            mBgImage = itemView.findViewById(R.id.imageViewTechNews);
        }

        void bindViews(WorldModel worldModel) {
            Picasso.get().load(worldModel.getImage()).fit().centerCrop().into(mBgImage);
            mTitle.setText(worldModel.getTitle());
            mSource.setText(worldModel.getName());
        }
    }
}
