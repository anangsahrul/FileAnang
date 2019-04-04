package com.example.anangdae.fileanang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText namafile, isifile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        namafile = findViewById(R.id.et_NamaFile);
        isifile = findViewById(R.id.et_IsiFile);

    }

    public void onCreateButtonClick(View view) {
        String nama = namafile.getText().toString();
        String isi = isifile.getText().toString();

        if (TextUtils.isEmpty(nama)) {
            Toast.makeText(this, "Mohon Isi Nama File", Toast.LENGTH_SHORT).show();
            return;
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = openFileOutput(nama, MODE_PRIVATE);
            fileOutputStream.write(isi.getBytes());
            Toast.makeText(this, "File : " + nama + " Berhasil dibikin", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Exeption : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onDeleteButtonClick(View view) {
        String nama = namafile.getText().toString();
        File file = new File(getFilesDir(), nama);
        if (TextUtils.isEmpty(nama)) {
            Toast.makeText(this, "Mohon Isi Nama File", Toast.LENGTH_SHORT).show();
            return;
        }

        if (file.exists()) {
            deleteFile(nama);
            Toast.makeText(this, "File : " + nama + " di hapus", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "File : " + nama + " tidak ditemukan", Toast.LENGTH_SHORT).show();
        }
    }

    public void onReadButtonClick(View view) {
        String nama = namafile.getText().toString();
        StringBuilder text = new StringBuilder();

        if (TextUtils.isEmpty(nama)) {
            Toast.makeText(this, "Mohon Isi Nama File", Toast.LENGTH_SHORT).show();
            return;
        }
        File file = new File(getFilesDir(), nama);

        try {
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) {
                    text.append(line);
                    text.append("\n");
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        isifile.setText(text.toString());
    }

}
