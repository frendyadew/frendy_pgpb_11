package com.frendy.notes11;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.frendy.notes11.databinding.ActivityAddEditNoteBinding;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import java.util.Calendar;
import java.util.Locale;
import android.widget.Toast;
import android.widget.TextView;


public class AddEditNoteActivity extends AppCompatActivity {

    private ActivityAddEditNoteBinding binding;
    private String mode; // Menyimpan mode (ADD atau EDIT)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddEditNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Mengambil referensi ke TextView untuk judul
        TextView tvAddNote = binding.tvAddNote;

        // Ambil data dari Intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("EXTRA_TITLE");
        String description = intent.getStringExtra("EXTRA_DESC");
        String date = intent.getStringExtra("EXTRA_DATE");
        mode = intent.getStringExtra("EXTRA_MODE"); // Menyimpan mode (ADD/EDIT)

        // Jika mode adalah EDIT, set data yang ada dan ubah tampilan
        if ("EDIT".equals(mode)) {
            setTitle("Edit Note"); // Mengubah judul layar menjadi "Edit Data"
            tvAddNote.setText(getString(R.string.edit_note));
            binding.btnAdd.setVisibility(View.GONE); // Sembunyikan tombol Add
            binding.btnUpdate.setVisibility(View.VISIBLE); // Tampilkan tombol Update

            // Menampilkan data untuk diubah
            binding.edtTitle.setText(title);
            binding.edtDesc.setText(description);
            binding.edtDate.setText(date);
        } else {
            setTitle("Add Note"); // Mengubah judul layar menjadi "Add Data"
            tvAddNote.setText(getString(R.string.add_note));
            binding.btnAdd.setVisibility(View.VISIBLE); // Tampilkan tombol Add
            binding.btnUpdate.setVisibility(View.GONE); // Sembunyikan tombol Update
        }

        // Listener untuk membuka DatePickerDialog
        binding.edtDate.setOnClickListener(view -> {
            // Mendapatkan tanggal saat ini sebagai default
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Membuka dialog DatePicker
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (DatePicker view1, int selectedYear, int selectedMonth, int selectedDay) -> {
                        // Format tanggal menjadi dd/MM/yyyy
                        String formattedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear);
                        binding.edtDate.setText(formattedDate);
                    }, year, month, day);

            datePickerDialog.show();
        });

        // Tombol untuk menambah catatan baru
        binding.btnAdd.setOnClickListener(view -> {
            String titleNew = binding.edtTitle.getText() != null ? binding.edtTitle.getText().toString().trim() : "";
            String descriptionNew = binding.edtDesc.getText() != null ? binding.edtDesc.getText().toString().trim() : "";
            String dateNew = binding.edtDate.getText() != null ? binding.edtDate.getText().toString().trim() : "";

            if (titleNew.isEmpty() || descriptionNew.isEmpty() || dateNew.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Menyiapkan hasil yang akan dikirim kembali
            Intent resultIntent = new Intent();
            resultIntent.putExtra("EXTRA_TITLE", titleNew);
            resultIntent.putExtra("EXTRA_DESC", descriptionNew);
            resultIntent.putExtra("EXTRA_DATE", dateNew);
            setResult(RESULT_OK, resultIntent);
            finish();
        });

        // Tombol untuk memperbarui catatan
        binding.btnUpdate.setOnClickListener(view -> {
            String updatedTitle = binding.edtTitle.getText() != null ? binding.edtTitle.getText().toString().trim() : "";
            String updatedDescription = binding.edtDesc.getText() != null ? binding.edtDesc.getText().toString().trim() : "";
            String updatedDate = binding.edtDate.getText() != null ? binding.edtDate.getText().toString().trim() : "";

            if (updatedTitle.isEmpty() || updatedDescription.isEmpty() || updatedDate.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Menyiapkan hasil yang akan dikirim kembali
            Intent resultIntent = new Intent();
            resultIntent.putExtra("EXTRA_TITLE", updatedTitle);
            resultIntent.putExtra("EXTRA_DESC", updatedDescription);
            resultIntent.putExtra("EXTRA_DATE", updatedDate);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
