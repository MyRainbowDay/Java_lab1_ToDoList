package com.bsuir.todolist;

import androidx.cardview.widget.CardView;

import com.bsuir.todolist.models.Note;

public interface NoteClickListener {
    void onClick(Note notes);

    void onLongClick(Note notes, CardView cardView);
}
