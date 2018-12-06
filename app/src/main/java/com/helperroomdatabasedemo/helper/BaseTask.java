package com.helperroomdatabasedemo.helper;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public abstract class BaseTask<T> extends AsyncTask<Object, Void, T> {

    public Context context;
    public ProgressDialog dialog;
    public Exception exception;



    public BaseTask(Context context) {
        this.context = context;
        this.dialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute() {
        this.dialog.setMessage("please wait...");
        this.dialog.show();
    }

    @Override
    protected T doInBackground(Object... objects) {
        try {
            return doWork(objects);
        } catch (Exception e) {
            exception = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(T result) {
        if (dialog.isShowing()) dialog.dismiss();
        if (exception == null) {
            onResult(result);
        } else {
            onError();
        }
    }


    public abstract T doWork(Object... objects) throws Exception;

    public abstract void onResult(T result);

    public abstract void onError();


}