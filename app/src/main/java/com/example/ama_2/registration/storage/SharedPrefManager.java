package com.example.ama_2.registration.storage;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.ama_2.registration.model.Student;

public class SharedPrefManager {

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private static final String SHARED_PREF_NAME = "mySharedPrefName";

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void saveStudent(Student student) {

        SharedPreferences sharedPrefManager = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefManager.edit();

        editor.putInt("id", student.getId());
        editor.putString("name", student.getName());
        editor.putString("email", student.getEmail());
        editor.putString("phone", student.getPhone());
        editor.putString("stdID", student.getStdID());

        editor.apply();

    }

    public boolean isLoggedIn() {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1) != -1;

    }

    public void logout() {

        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        //clear editor for making logout
        editor.clear();

        editor.apply();

    }

    public Student getStudent(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Student(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("stdID", null),
                sharedPreferences.getString("phone", null)
        );
    }
}