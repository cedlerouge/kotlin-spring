package com.journaler.api.controller

import com.journaler.api.data.Note
import com.journaler.api.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/notes")
/*@EnableAutoConfiguration*/
class NoteController {

    @Autowired
    private lateinit var service: NoteService

    /**
     * Get notes.
     */
    @GetMapping(
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getNotes() : Iterable<Note> = service.getNotes()

    /**
     * Insert note.
     * It consumes JSON, that is: request body Note.
     */
    @PutMapping(
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
        consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertNote(
            @RequestBody note: Note
    ) = service.insertNote(note)

    /**
     * Remove note by Id.
     * We introduce path variable for Id to pass
     */
    @DeleteMapping(
        value = ["/{id}"],
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteNote(@PathVariable(name = "id") id: String) = service.deleteNote(id)

    /**
     * Update item.
     * It consumes JSON, that is: request body Note.
     * As result it returns updated Notes.
     */
    @PostMapping(
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateNote(@RequestBody note: Note) : Note = service.updateNote(note)
}