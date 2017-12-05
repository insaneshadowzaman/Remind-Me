package com.example.akibtalcompany.remindme;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NoteView extends RecyclerView.ViewHolder {
    TextView name, hh, mm;
    Button delete;
    public NoteView(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.note_name);
        hh = itemView.findViewById(R.id.note_hour);
        mm = itemView.findViewById(R.id.note_minute);
        delete = itemView.findViewById(R.id.delete);
    }
}
