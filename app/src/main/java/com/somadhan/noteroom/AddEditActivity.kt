package com.somadhan.noteroom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.somadhan.noteroom.databinding.ActivityAddEditBinding
import java.text.SimpleDateFormat
import java.util.Date

class AddEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddEditBinding
    private val viewModel: NoteViewModel by viewModels()
    var noteID = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val noteType = intent.getStringExtra("noteType")
        if (noteType == "Edit") {
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDesc = intent.getStringExtra("noteDescription")
            noteID = intent.getIntExtra("noteID", -1)
            binding.idBtnAddUpdate.setText("Update note")
            binding.idEdtNoteTitle.setText(noteTitle)
            binding.idEdtNoteDescription.setText(noteDesc)
        } else {
            binding.idBtnAddUpdate.setText("Save Note")
        }

        binding.idBtnAddUpdate.setOnClickListener {
            val noteTitle = binding.idEdtNoteTitle.text.toString()
            val noteDescription = binding.idEdtNoteDescription.text.toString()

            if (noteType == "Edit") {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    val updateNote = Note(noteTitle, noteDescription, currentDate)
                    updateNote.id = noteID
                    viewModel.updateNote(updateNote)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if (noteTitle.isNotEmpty() && noteDescription.isNotEmpty()) {
                    val sdf = SimpleDateFormat("dd MM, yyyy - HH:mm")
                    val currentDate: String = sdf.format(Date())
                    viewModel.addNote(Note(noteTitle, noteDescription, currentDate))
                    Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show()
                }
            }

            // Move these lines inside the onClickListener block
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}
