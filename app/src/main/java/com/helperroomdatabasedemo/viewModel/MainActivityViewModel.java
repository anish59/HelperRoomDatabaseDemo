package com.helperroomdatabasedemo.viewModel;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.helperroomdatabasedemo.dbHelper.CollegeDatabase;
import com.helperroomdatabasedemo.helper.BaseTask;
import com.helperroomdatabasedemo.model.Student;

import java.util.List;


public class MainActivityViewModel {
    private Context context;
    private MainActivityCallBack callBack;
    private CollegeDatabase database;

    public MainActivityViewModel(Context context, MainActivityCallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        database = CollegeDatabase.getInstance(context);
    }

    @SuppressLint("StaticFieldLeak")
    public void insertStudent(final String name, final int rollNo) {

        new BaseTask<Object>(context) {
            @Override
            public Object doWork(Object... objects) throws Exception {
                database.getCollegeDao().insertStudent(new Student(name, rollNo));
                return null;
            }

            @Override
            public void onResult(Object result) {
                callBack.onInsertedSuccessFully("Successful");
                getStudents();
            }

            @Override
            public void onError() {
                callBack.onInsertError("error inserting");
            }
        }.execute();


    }


    @SuppressLint("StaticFieldLeak")
    public void getStudents() {

        new BaseTask<List<Student>>(context) {
            @Override
            public List<Student> doWork(Object... objects) throws Exception {
                return database.getCollegeDao().getAllStudents();
            }

            @Override
            public void onResult(List<Student> studentList) {

                callBack.onGettingStudentList(studentList);
            }

            @Override
            public void onError() {
                callBack.onFetchingStudentListError("error fetching");
            }
        }.execute();
    }


    public interface MainActivityCallBack {
        void onInsertedSuccessFully(String msg);

        void onInsertError(String errMsg);

        void onGettingStudentList(List<Student> studentList);

        void onFetchingStudentListError(String errMsg);
    }


}
