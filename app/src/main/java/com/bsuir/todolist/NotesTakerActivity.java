package com.bsuir.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bsuir.todolist.models.Note;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class NotesTakerActivity extends AppCompatActivity {

    private EditText editText_title;
    private EditText editText_notes;
    private ImageView imageView_save;
    private Note note;

    private boolean isOldNote = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);

        imageView_save = findViewById(R.id.imageView_save);
        editText_title = findViewById(R.id.editText_title);
        editText_notes = findViewById(R.id.editText_notes);

        note = new Note();
        try {
            note = (Note) getIntent().getSerializableExtra("old_note");
            editText_title.setText(note.getTitle());
            editText_notes.setText(note.getNotes());
            isOldNote = true;
        }catch (Exception e) {
            e.printStackTrace();
        }

        imageView_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editText_title.getText().toString();
                String text = editText_notes.getText().toString();

                if(text.isEmpty()) {
                    Toast.makeText(NotesTakerActivity.this, "Please add text",Toast.LENGTH_LONG).show();
                    return;
                }

                SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyy HH:mm:ss");
                Date date = new Date();

                if (!isOldNote) {
                    note = new Note();
                }

                note.setTitle(title);
                note.setNotes(text);
                note.setDate(formatter.format(date));

                Intent intent = new Intent();

                intent.putExtra("note", note);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

}