package co.kogi.feedtabapp.model;

/**
 * Created by user on 5/07/2016.
 */
public class Article {
    String article_id;
    String article_content;
    String article_url;
    String article_imageurl;

    public Article(){

    }

    public String getArticle_id() {
        return article_id;
    }

    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getArticle_content() {
        return article_content;
    }

    public void setArticle_content(String article_content) {
        this.article_content = article_content;
    }

    public String getArticle_url() {
        return article_url;
    }

    public void setArticle_url(String article_url) {
        this.article_url = article_url;
    }

    public String getArticle_imageurl() {
        return  "http://www.kogimobile.com/applicant-test"+article_imageurl;
    }

    public void setArticle_imageurl(String article_imageurl) {

        this.article_imageurl = article_imageurl;
    }
}
