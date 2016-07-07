package co.kogi.feedtabapp.ui.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import co.kogi.feedtabapp.R;
import co.kogi.feedtabapp.device.HttpClient;
import co.kogi.feedtabapp.model.Article;
import co.kogi.feedtabapp.model.Feed;
import co.kogi.feedtabapp.ui.activity.MainActivity;
import co.kogi.feedtabapp.ui.adapter.ArticleAdapter;
import co.kogi.feedtabapp.util.AppContext;
import co.kogi.feedtabapp.util.PermissionHelper;
import co.kogi.feedtabapp.util.UIHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {



    ArticleAdapter articleAdapter;
    ImageView imageView_articleImage;
    TextView textView_articleText;
    ListView listView_articleList;
    MainActivity mActivity;




    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView_articleList = (ListView) getActivity().findViewById(R.id.listView_articleList);
        textView_articleText = (TextView) getActivity().findViewById(R.id.textView_articleText);
        imageView_articleImage = (ImageView) getActivity().findViewById(R.id.imageView_articleImage);
        articleAdapter = new ArticleAdapter(getActivity());
        listView_articleList.setAdapter(articleAdapter);

        textView_articleText.setMovementMethod(new ScrollingMovementMethod());

        listView_articleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                displayArticle(articleAdapter.getItem(position));
                EventBus.getDefault().post(articleAdapter.getItem(position).getArticle_url());
            }
        });

        PermissionHelper.requestStoragePermission(getActivity(), new PermissionHelper.PermissionGrantedListener() {
            @Override
            public void onPermissionGranted() {
                HttpClient.startCache();
                loadFeed();
            }
        });


    }
    private void loadFeed(){
        UIHelper.showProgressDialog(getActivity(),"Loading feed");
        HttpClient.get("http://www.kogimobile.com/applicant-test/applicant%20test%20feed.json", new AsyncHttpClient.StringCallback() {

            @Override
            public void onCompleted(Exception e, AsyncHttpResponse source, String result) {
                UIHelper.hideProgressDialog();
                if (e != null){
                    return;
                }

                if (result != null){
                   final Feed feed = AppContext.getGson().fromJson(result,Feed.class);

                   getActivity().runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           articleAdapter.setItems(feed.getData());
                           displayArticle(feed.getData().get(0));


                       }
                   });
                }
            }
        });
    }

    private void displayArticle(Article article){
        if (article != null) {
            textView_articleText.setText(article.getArticle_content());
            UIHelper.displayImage(article.getArticle_imageurl(),imageView_articleImage);
        }
    }
}
