package co.kogi.feedtabapp.model;

import java.util.List;

/**
 * Created by user on 5/07/2016.
 */
public class Feed {
    String responseTxt;
    String responseErrorCode;
    List<Article> data;

    public Feed() {
    }

    public String getResponseTxt() {
        return responseTxt;
    }

    public void setResponseTxt(String responseTxt) {
        this.responseTxt = responseTxt;
    }

    public String getResponseErrorCode() {
        return responseErrorCode;
    }

    public void setResponseErrorCode(String responseErrorCode) {
        this.responseErrorCode = responseErrorCode;
    }

    public List<Article> getData() {
        return data;
    }

    public void setData(List<Article> data) {
        this.data = data;
    }
}
