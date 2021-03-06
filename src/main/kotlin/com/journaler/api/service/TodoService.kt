package com.journaler.api.service

import com.journaler.api.data.Todo
import com.journaler.api.data.TodoDTO
import com.journaler.api.repository.TodoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service("Todo Service")
class TodoService {

    @Autowired
    lateinit var repository: TodoRepository

    fun getTodos(): Iterable<TodoDTO> = repository.findAll().map{ it -> TodoDTO(it) }

    fun insertTodo(todo: TodoDTO): TodoDTO =  TodoDTO(
        repository.save(
            Todo(
                title = todo.title,
                message = todo.message,
                location = todo.location,
                schedule = todo.schedule
            )
        )
    )

    fun deleteTodo(id: String) = repository.deleteById(id)

    fun updateTodo(todoDTO: TodoDTO): TodoDTO {
        var todo = repository.findById(todoDTO.id).get()
        todo.title = todoDTO.title
        todo.message = todoDTO.message
        todo.location = todoDTO.location
        todo.modified = Date()
        todo = repository.save(todo)
        return TodoDTO(todo)

    }

    fun getScheduledLaterThan(date: Date): Iterable<TodoDTO> {
        return repository.findScheduledLaterThan(date.time).map { it -> TodoDTO(it) }
    }
}