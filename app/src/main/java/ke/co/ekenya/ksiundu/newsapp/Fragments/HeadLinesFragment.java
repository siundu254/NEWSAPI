package ke.co.ekenya.ksiundu.newsapp.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;
import ke.co.ekenya.ksiundu.newsapp.R;
import ke.co.ekenya.ksiundu.newsapp.adapter.HeadLinesAdapter;
import ke.co.ekenya.ksiundu.newsapp.model.HeadLines;
import ke.co.ekenya.ksiundu.newsapp.services.ApiService;


public class HeadLinesFragment extends Fragment  {
    private SweetAlertDialog mHeadLinesDialog;
    RecyclerView mHeadLinesView;
    ArrayList<HeadLines> mHeadLinesList = new ArrayList<>();
    public HeadLinesFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView = inflater.inflate(R.layout.fragment_head_lines, container, false);
        mHeadLinesView = mView.findViewById(R.id.recyclerHeadLines);
        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getHeadLines();
            }
        });
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void getHeadLines() {
        new HeadLinesAsyncTask(getActivity()).execute();
    }

    @SuppressLint("StaticFieldLeak")
    class HeadLinesAsyncTask extends AsyncTask<Void, Void, Void> {
        Activity mCtx;
        HeadLinesAsyncTask(FragmentActivity activity) {
            this.mCtx = activity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mHeadLinesDialog = new SweetAlertDialog(getActivity(), SweetAlertDialog.PROGRESS_TYPE);
            mHeadLinesDialog.setTitleText("Fetching News")
                    .setContentText("Dear Client, Kindly wait as we fetch the latest News from around the World")
                    .setCancelable(false);
            mHeadLinesDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            String url = "https://newsapi.org/v2/top-headlines?language=en&apiKey=2000bed637964320887999f67adb1242";
            ApiService mService = new ApiService();
            String response = mService.getHeadLines(url);

            try {
                JSONObject mResponseObject = new JSONObject(response);
                JSONArray mArticlesArray = mResponseObject.getJSONArray("articles");

                for (int i = 0; i < mArticlesArray.length(); i++) {

                    JSONObject mResultsObject = mArticlesArray.getJSONObject(i);

                    String title = mResultsObject.getString("title");
                    String image = mResultsObject.getString("urlToImage");
                    String date = String.valueOf(mResultsObject.get("publishedAt"));
                    String content = mResultsObject.getString("content");

                    if (image == null || title == null || content == null) {
                        Toast.makeText(mCtx, "", Toast.LENGTH_SHORT).show();
                    } else {
                        HeadLines mHeadLineModel = new HeadLines();
                        mHeadLineModel.setTitle(title);
                        mHeadLineModel.setImage(image);
                        mHeadLineModel.setDate(date);
                        mHeadLineModel.setContent(content);

                        mHeadLinesList.add(mHeadLineModel);
                    }

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            HeadLinesAdapter mAdapter = new HeadLinesAdapter(mHeadLinesList);
            // specify an adapter (see also next example)
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            mHeadLinesView.setHasFixedSize(true);

            // use a linear layout manager
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mHeadLinesView.setLayoutManager(layoutManager);
            mHeadLinesView.setItemAnimator(new DefaultItemAnimator());
            mHeadLinesView.setAdapter(mAdapter);
            mHeadLinesDialog.dismiss();
        }
    }

}
