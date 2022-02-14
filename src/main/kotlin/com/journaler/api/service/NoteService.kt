package com.journaler.api.service

import com.journaler.api.data.Note
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
    fun getNotes(): Iterable<Note> = repository.findAll()

    /**
     * Saves a given entity. Use the returned instance for further operation as
     * the save operation might have changed the entity instance completely.
     *
     * @prama: entity must not be {@literal null}
     * @return the saved entity will never be {@literal null}
     */
    fun insertNote(note: Note): Note = repository.save(note)

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
    fun updateNote(note: Note): Note = repository.save(note)
}