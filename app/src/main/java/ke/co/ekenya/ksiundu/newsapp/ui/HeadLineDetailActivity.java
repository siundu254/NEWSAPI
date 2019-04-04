package ke.co.ekenya.ksiundu.newsapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ke.co.ekenya.ksiundu.newsapp.R;

public class HeadLineDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_line_detail);

        final String title = getIntent().getStringExtra("title");
        final String image = getIntent().getStringExtra("image");
        final String content = getIntent().getStringExtra("content");

        TextView mDetailTitle = findViewById(R.id.textHeadLineTitleDetail);
        mDetailTitle.setText(title);
        TextView mDetailContent = findViewById(R.id.textHeadLineDetailContent);
        mDetailContent.setText(content);
        ImageView mDetailImage = findViewById(R.id.imageHeadLineDetail);
        Picasso.get().load(image).fit().centerCrop().into(mDetailImage);
        ImageView mImageBack = findViewById(R.id.imageIconBack);
        mImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mBackIntent = new Intent(HeadLineDetailActivity.this, MainActivity.class);
                startActivity(mBackIntent);
            }
        });

    }
}
