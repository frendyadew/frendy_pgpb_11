package com.frendy.notes11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

// NotesAdapter: Adapter untuk menghubungkan data Note ke ListView
public class NotesAdapter extends BaseAdapter {

    private final Context context;       // Konteks aplikasi
    private final List<Note> notesList;  // Daftar catatan
    private final OnNoteActionListener listener; // Callback untuk aksi Edit dan Hapus

    // Konstruktor NotesAdapter
    public NotesAdapter(Context context, List<Note> notesList, OnNoteActionListener listener) {
        this.context = context;
        this.notesList = notesList;
        this.listener = listener;
    }

    // Mengembalikan jumlah item di notesList
    @Override
    public int getCount() {
        return notesList.size();
    }

    // Mengembalikan item Note berdasarkan posisi
    @Override
    public Object getItem(int position) {
        return notesList.get(position);
    }

    // Mengembalikan ID item berdasarkan posisi
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Menghubungkan data Note ke tampilan item di ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gunakan kembali view yang sudah ada untuk efisiensi
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false);
        }

        // Referensi komponen di layout item_note.xml
        TextView titleTextView = convertView.findViewById(R.id.tvTitle);
        TextView dateTextView = convertView.findViewById(R.id.tvDate);
        Button btnEdit = convertView.findViewById(R.id.btnEdit);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        // Ambil data Note berdasarkan posisi
        Note currentNote = notesList.get(position);

        // Atur data ke dalam TextView
        titleTextView.setText(currentNote.getTitle());
        dateTextView.setText(currentNote.getDate());

        // Tombol Edit: Kirim data untuk diedit
        btnEdit.setOnClickListener(v -> listener.onEdit(currentNote));

        // Tombol Hapus: Panggil callback untuk menghapus
        btnDelete.setOnClickListener(v -> listener.onDelete(currentNote));

        return convertView;
    }

    // Antarmuka untuk mendefinisikan aksi Edit dan Hapus
    public interface OnNoteActionListener {
        void onEdit(@NonNull Note note);
        void onDelete(@NonNull Note note);
    }
}
