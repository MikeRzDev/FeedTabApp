package co.kogi.feedtabapp.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.http.callback.HttpConnectCallback;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import co.kogi.feedtabapp.device.HttpClient;


/**
 * Created by user on 5/07/2016.
 */
public class UIHelper {
    private static ProgressDialog progressDialog;

    public static final void showProgressDialog(Activity uiContext, String progressDialogMessage) {
        if (!uiContext.isFinishing()) {
            progressDialog = new ProgressDialog(uiContext);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage(progressDialogMessage);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }

    public static final void hideProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public static final void showInformationDialog(final Activity uiContext,
                                                   String title,
                                                   String message,
                                                   DialogInterface.OnClickListener okListener,
                                                   DialogInterface.OnClickListener cancelListener) {
        if (!uiContext.isFinishing()) {
            new AlertDialog.Builder(uiContext)
                    .setTitle(title)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setMessage(message)
                    .setCancelable(false)
                    .setPositiveButton("Aceptar",
                            okListener)
                    .setNegativeButton("Cancelar", cancelListener)
                    .show();
        }

    }


    public static final void showInformationDialog(final Activity uiContext,
                                                   String title,
                                                   String message,
                                                   DialogInterface.OnClickListener listener) {
        if (!uiContext.isFinishing()) {
            new AlertDialog.Builder(uiContext)
                    .setTitle(title)
                    .setCancelable(false)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setMessage(message)
                    .setPositiveButton("Aceptar",
                            listener).show();
        }

    }

    public static void displayImage(final String url, final ImageView imageView){
        ImageLoader.getInstance().displayImage(url, imageView,
                AppContext.getImageOptions(), new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        String newUrl = url.replace(".jpg",".png");
                       ImageLoader.getInstance().displayImage(newUrl,imageView,AppContext.getImageOptions());

                    }
                });
    }
}

