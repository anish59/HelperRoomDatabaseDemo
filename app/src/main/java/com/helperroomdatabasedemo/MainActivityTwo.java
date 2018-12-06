package com.helperroomdatabasedemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.helperroomdatabasedemo.adapters.StudentAdapter;
import com.helperroomdatabasedemo.model.Student;
import com.helperroomdatabasedemo.viewModel.MainActivityViewModel;

import java.util.List;

public class MainActivityTwo extends AppCompatActivity implements MainActivityViewModel.MainActivityCallBack {
    private android.widget.EditText txtName;
    private android.widget.EditText txtRollNo;
    private android.widget.Button btnInsert;
    private android.widget.Button btnUpdate;
    private android.widget.Button btnDelete;
    private android.support.v7.widget.RecyclerView rvStudents;
    private Context context;
    private MainActivityViewModel viewModel;
    private EditText edtName;
    private EditText edtRollNo;
    private Button btnShowData;
    private StudentAdapter studentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.btnShowData = (Button) findViewById(R.id.btnShowData);
        this.edtRollNo = (EditText) findViewById(R.id.edtRollNo);
        this.edtName = (EditText) findViewById(R.id.edtName);
        this.rvStudents = (RecyclerView) findViewById(R.id.rvStudents);
        this.btnDelete = (Button) findViewById(R.id.btnDelete);
        this.btnUpdate = (Button) findViewById(R.id.btnUpdate);
        this.btnInsert = (Button) findViewById(R.id.btnInsert);
        this.txtRollNo = (EditText) findViewById(R.id.edtRollNo);
        this.txtName = (EditText) findViewById(R.id.edtName);
        context = this;
        viewModel = new MainActivityViewModel(this, this);
        setAdapters();


        setActionListener();
    }

    private void setAdapters() {
        studentAdapter = new StudentAdapter(context);
        rvStudents.setLayoutManager(new LinearLayoutManager(context));
        rvStudents.setAdapter(studentAdapter);
    }

    private void setActionListener() {
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.insertStudent(txtName.getText().toString().trim(), Integer.parseInt(txtRollNo.getText().toString().trim()));
            }
        });
        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.getStudents();
            }
        });
    }

    @Override
    public void onInsertedSuccessFully(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInsertError(String errMsg) {
        Toast.makeText(context, errMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGettingStudentList(List<Student> studentList) {
        studentAdapter.setStudentList(studentList);
    }

    @Override
    public void onFetchingStudentListError(String errMsg) {
        Toast.makeText(context, errMsg, Toast.LENGTH_SHORT).show();
    }
}
