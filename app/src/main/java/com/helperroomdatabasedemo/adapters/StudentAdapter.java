package com.helperroomdatabasedemo.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.helperroomdatabasedemo.R;
import com.helperroomdatabasedemo.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    private Context context;
    private List<Student> studentList;

    public StudentAdapter(Context context) {
        this.context = context;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = new ArrayList<>();
        this.studentList = studentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student, viewGroup, false);

        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder studentViewHolder, int i) {
        studentViewHolder.setData(studentList.get(i));
    }

    @Override
    public int getItemCount() {
        return studentList != null ? studentList.size() : 0;
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        TextView txtRollNo;
        TextView txtName;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtRollNo = (TextView) itemView.findViewById(R.id.txtRollNo);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
        }

        void setData(Student data) {
            txtName.setText(data.getName());
            txtRollNo.setText(data.getRollNo()+"");
        }
    }
}
