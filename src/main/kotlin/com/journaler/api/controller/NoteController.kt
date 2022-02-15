package com.journaler.api.controller

import com.journaler.api.data.NoteDTO
import com.journaler.api.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

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
    fun getNotes() = service.getNotes()

    /**
     * Insert note.
     * It consumes JSON, that is: request body Note.
     */
    @PutMapping(
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
        consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertNote(
            @RequestBody note: NoteDTO
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
    fun updateNote(@RequestBody note: NoteDTO) : NoteDTO = service.updateNote(note)

    /**
     * Find note by title
     */
    @PostMapping(
        value = ["/by_title"],
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
        consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getNoteByTitle(
        @RequestBody payload: NoteFindByTitleRequest
    ): Iterable<NoteDTO> = service.findByTitle(payload.title)
}