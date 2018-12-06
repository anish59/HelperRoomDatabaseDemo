package com.helperroomdatabasedemo.dbHelper.daos;

import android.arch.persistence.room.*;
import android.database.Cursor;
import com.helperroomdatabasedemo.model.Student;

import java.util.List;

@Dao
public interface StudentDao {//Data Access Object

    //-------------Query---------------------
    @Query("select * from Student")
    List<Student> getAllStudents();


    @Query("select * from Student where id=:studentId ")
    List<Student> getAllStudents(int studentId);


    @Query("select * from Student")
    Cursor getStudentCursor();// need to know the perfect use of the cursor


    //-------------update-------------------
    @Update
    void updateStudents(Student... students);

    @Update
    void updateStudent(Student student);

    @Update
    void updateStudents(List<Student> studentList);


    //-------------update-------------------
    @Insert
    void insertStudents(Student... students);

    @Insert
    void insertStudent(Student student);

    @Insert
    void insertStudents(List<Student> studentList);

    //-------------delete-------------------
    @Delete
    void deleteStudents(Student... students);

    @Delete
    void deleteStudent(Student students);


    //--------------mixed query-----------

    @Query("Delete from Student where id=:studentId")
    void deleteStudent(int studentId);

    @Query("Update  Student set name=:name where id=:studentId")
    void updateStudent(int studentId, String name);

}
