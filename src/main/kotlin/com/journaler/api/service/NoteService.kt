package com.journaler.api.service

import com.journaler.api.data.Note
import com.journaler.api.data.NoteDTO
import com.journaler.api.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service("Note Service")
class NoteService {

    @Autowired
    lateinit var repository: NoteRepository

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    fun getNotes(): Iterable<NoteDTO> = repository.findAll().map{ it -> NoteDTO(it) }

    /**
     * Saves a given entity. Use the returned instance for further operation as
     * the save operation might have changed the entity instance completely.
     *
     * @prama: entity must not be {@literal null}
     * @return the saved entity will never be {@literal null}
     */
    fun insertNote(note: NoteDTO) = NoteDTO(
        repository.save(
            Note(
                title = note.title,
                message = note.message,
                location = note.location
            )
        )
    )

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@leteral null}
     * @return IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    fun deleteNote(id: String) = repository.deleteById(id)

    /**
     * Saves a given entity. Use the returned instance for further operation as the save
     * operation might have changed the entity instance completely.
     */
    fun updateNote(noteDTO: NoteDTO): NoteDTO{
        var note = repository.findById(noteDTO.id).get()
        note.title = noteDTO.title
        note.message = noteDTO.message
        note.location = noteDTO.location
        note.modified = Date()
        note = repository.save(note)
        return NoteDTO(note)
    }

    fun findByTitle(title: String): Iterable<NoteDTO> {
        return repository.findByTitle(title).map { it -> NoteDTO(it) }
    }
}