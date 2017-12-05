package com.example.akibtalcompany.remindme;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.LauncherActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.zip.Inflater;

public class UpdateTaskActivity extends Activity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        final Cursor cursor = AppHelper.getCursor(this);

        recyclerView.setAdapter(new RecyclerView.Adapter<NoteView>() {
            @Override
            public NoteView onCreateViewHolder(ViewGroup parent, int viewType) {
                return new NoteView(LayoutInflater.from(parent.getContext()).inflate(R.layout.noteview, parent, false));
            }

            @Override
            public void onBindViewHolder(NoteView holder, int position) {
                cursor.moveToPosition(position);
                final String name = cursor.getString(0);
                holder.name.setText(cursor.getString(0));
                holder.hh.setText(cursor.getString(1));
                holder.mm.setText(cursor.getString(2));

                holder.delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new AlertDialog.Builder(UpdateTaskActivity.this)
                                .setTitle("Delete")
                                .setMessage("Do you want to Delete")
                                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        AppHelper.deleteNote(UpdateTaskActivity.this, name);
                                        finish();
                                        dialog.dismiss();
                                    }

                                })
                                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .create().show();
                    }
                });
            }

            @Override
            public int getItemCount() {
                return cursor.getCount();
            }
        });

    }
}
