package co.kogi.feedtabapp.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import co.kogi.feedtabapp.R;
import co.kogi.feedtabapp.model.Article;
import co.kogi.feedtabapp.util.AppContext;
import co.kogi.feedtabapp.util.UIHelper;

/**
 * Created by user on 5/07/2016.
 */
public class ArticleAdapter extends BaseAdapter {
    private List<Article> objects;

    private Context context;
    private LayoutInflater layoutInflater;

    public ArticleAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (objects == null){
            return 0;
        } else {
            return objects.size();
        }
    }

    @Override
    public Article getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.adapter_article, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag(), position);
        return convertView;
    }

    private void initializeViews(Article object, ViewHolder holder, int position) {
        if (AppContext.getPreference("interpColors").equals("true")){
            if (position % 2 != 0){
                holder.relativeLayout_background.setBackgroundColor(ContextCompat.getColor(AppContext.getContext()
                ,R.color.interpColor));
            }
        }
        UIHelper.displayImage(object.getArticle_imageurl(),holder.imageView_articleThumb);
        holder.textView_articleText.setText(object.getArticle_content());
    }

    protected class ViewHolder {
        private ImageView imageView_articleThumb;
        private TextView textView_articleText;
        private RelativeLayout relativeLayout_background;

        public ViewHolder(View view) {
            imageView_articleThumb = (ImageView) view.findViewById(R.id.imageView_articleThumb);
            textView_articleText = (TextView) view.findViewById(R.id.textView_articleText);
            relativeLayout_background = (RelativeLayout) view.findViewById(R.id.relativeLayout_background);
        }
    }

    public void setItems(List<Article> items){
        objects = items;
        notifyDataSetChanged();
    }

    public List<Article> getItems(){
        return objects;
    }
}
