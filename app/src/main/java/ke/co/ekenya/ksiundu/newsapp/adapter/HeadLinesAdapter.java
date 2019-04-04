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
import ke.co.ekenya.ksiundu.newsapp.model.HeadLines;
import ke.co.ekenya.ksiundu.newsapp.ui.HeadLineDetailActivity;

public class HeadLinesAdapter extends RecyclerView.Adapter<HeadLinesAdapter.HeadLineHolder> {
    private Context mCtx;
    private ArrayList<HeadLines> mHeadLineModel;
    public HeadLinesAdapter(ArrayList<HeadLines> mHeadLinesList) {
        this.mHeadLineModel = mHeadLinesList;
    }

    @NonNull
    @Override
    public HeadLinesAdapter.HeadLineHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.single_head_lines,
                viewGroup,
                false
        );
        return new HeadLineHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HeadLinesAdapter.HeadLineHolder headLineHolder, int i) {
        headLineHolder.binHeadLines(mHeadLineModel.get(i));
        final String title = mHeadLineModel.get(i).getTitle();
        final String image = mHeadLineModel.get(i).getImage();
        final String content = mHeadLineModel.get(i).getContent();
        headLineHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mHeadLineDetail =  new Intent(mCtx, HeadLineDetailActivity.class);
                mHeadLineDetail.putExtra("title", title);
                mHeadLineDetail.putExtra("image", image);
                mHeadLineDetail.putExtra("content", content);
                headLineHolder.itemView.getContext().startActivity(mHeadLineDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mHeadLineModel.size();
    }

    class HeadLineHolder extends RecyclerView.ViewHolder {
        TextView mHeadLineTitle, mHeadLineDate;
        ImageView mHeadLineImage;
        HeadLineHolder(@NonNull View itemView) {
            super(itemView);
            mCtx = itemView.getContext();
            mHeadLineTitle = itemView.findViewById(R.id.textHeadLineTitle);
            mHeadLineDate = itemView.findViewById(R.id.textHeadLinePublishedAt);
            mHeadLineImage = itemView.findViewById(R.id.imageViewHeadLine);
        }

        void binHeadLines(HeadLines headLines) {
            Picasso.get().load(headLines.getImage()).fit().centerCrop().into(mHeadLineImage);
            mHeadLineTitle.setText(headLines.getTitle());
            mHeadLineDate.setText(headLines.getDate());
        }
    }
}
