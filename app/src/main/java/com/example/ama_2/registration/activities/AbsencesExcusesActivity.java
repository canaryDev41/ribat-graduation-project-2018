package com.example.ama_2.registration.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.ama_2.registration.R;
import java.io.IOException;

public class AbsencesExcusesActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int IMAGE_REQUEST = 777;
    RadioButton rbMedial, rbRenew, rbOthers;
    RadioGroup rgTypes;
    Button btnConfirm, btnAttach;
    Bitmap bitmap;
    ImageView ivAttach;
    EditText etNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absences_excuses);

        rbMedial = (RadioButton) findViewById(R.id.rbMedical);
        rbRenew = (RadioButton) findViewById(R.id.rbRenew);
        rbOthers = (RadioButton) findViewById(R.id.rbOthers);
        rgTypes = (RadioGroup) findViewById(R.id.rgTypes);

        etNote = (EditText) findViewById(R.id.etNote);

        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnAttach = (Button) findViewById(R.id.btnAttach);

        ivAttach = (ImageView) findViewById(R.id.ivAttach);

        btnAttach.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        btnConfirm.setEnabled(false);

    }

    public void selectImage(){

        Intent intent = new Intent();

        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK && data != null){

            Uri path = data.getData();

            try{
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                ivAttach.setImageBitmap(bitmap);
                ivAttach.setVisibility(View.VISIBLE);

                btnConfirm.setEnabled(true);

            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void upload(){
        String note = etNote.getText().toString().trim();

        if (note.isEmpty()){
            etNote.setError("this field is required");
            etNote.requestFocus();
        }

        Toast.makeText(this, "you click me!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAttach:
                selectImage();
                break;

            case R.id.btnConfirm:
                    upload();
                break;
        }
    }
}