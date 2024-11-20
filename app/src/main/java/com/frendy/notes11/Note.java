package com.frendy.notes11;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Note {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "date")
    private String date;

    // Constructor dengan parameter
    public Note(String title, String description, String date) {
        this.title = title;
        this.description = description;
        this.date = date;
    }

    // Constructor tanpa parameter, jika diperlukan untuk Room Database
    public Note() {
        // Kosong, agar bisa digunakan oleh Room untuk pengisian data
    }

    // Getter dan Setter untuk id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter dan Setter untuk title
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Getter dan Setter untuk description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getter dan Setter untuk date
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Override toString untuk menampilkan title
    @NonNull
    @Override
    public String toString() {
        return this.title;
    }
}

