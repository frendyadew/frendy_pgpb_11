package com.frendy.notes11;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.frendy.notes11.databinding.ActivityMainBinding;
import java.util.ArrayList;
import androidx.annotation.NonNull;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD_NOTE = 1;   // Kode untuk tambah catatan
    private static final int REQUEST_CODE_EDIT_NOTE = 2;  // Kode untuk edit catatan

    private ArrayList<Note> notesList;    // Daftar catatan
    private NotesAdapter notesAdapter;   // Adapter untuk ListView
    private ActivityMainBinding binding; // View Binding untuk akses layout
    private int editNotePosition = -1;   // Menyimpan posisi catatan yang diedit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Menggunakan View Binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Inisialisasi daftar catatan dan adapter
        notesList = new ArrayList<>();
        // Menambahkan listener untuk aksi Edit dan Hapus
        notesAdapter = new NotesAdapter(this, notesList, new NotesAdapter.OnNoteActionListener() {
            @Override
            public void onEdit(@NonNull Note note) {
                // Logika untuk edit catatan
                Intent intent = new Intent(MainActivity.this, AddEditNoteActivity.class);
                intent.putExtra("EXTRA_TITLE", note.getTitle());
                intent.putExtra("EXTRA_DESC", note.getDescription());
                intent.putExtra("EXTRA_DATE", note.getDate());
                intent.putExtra("EXTRA_MODE", "EDIT"); // Menambahkan mode EDIT

                editNotePosition = notesList.indexOf(note); // Menyimpan posisi catatan yang diedit
                startActivityForResult(intent, REQUEST_CODE_EDIT_NOTE);
            }

            @Override
            public void onDelete(@NonNull Note note) {
                // Logika untuk menghapus catatan
                notesList.remove(note);
                notesAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Catatan dihapus", Toast.LENGTH_SHORT).show();
            }
        });

        // Menghubungkan adapter ke ListView
        binding.listView.setAdapter(notesAdapter);

        // Listener untuk tombol tambah catatan
        binding.btnAddNote.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddEditNoteActivity.class);
            startActivityForResult(intent, REQUEST_CODE_ADD_NOTE);
        });

        // Listener untuk item catatan (edit dan hapus) sudah ditangani dalam adapter
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            String title = data.getStringExtra("EXTRA_TITLE");
            String description = data.getStringExtra("EXTRA_DESC");
            String date = data.getStringExtra("EXTRA_DATE");

            if (requestCode == REQUEST_CODE_ADD_NOTE) {
                // Tambah catatan baru
                Note newNote = new Note(title, description, date);
                notesList.add(newNote);
                notesAdapter.notifyDataSetChanged();
            } else if (requestCode == REQUEST_CODE_EDIT_NOTE && editNotePosition != -1) {
                // Update catatan yang diedit
                Note editedNote = notesList.get(editNotePosition);
                editedNote.setTitle(title);
                editedNote.setDescription(description);
                editedNote.setDate(date);
                notesAdapter.notifyDataSetChanged();
                editNotePosition = -1; // Reset posisi edit
            }
        }
    }
}
