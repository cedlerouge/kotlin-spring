package com.journaler.api.controller

import com.journaler.api.data.TodoDTO
import com.journaler.api.service.TodoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/todos")
class TodoController {

    @Autowired
    private lateinit var service: TodoService

    /**
     * Get todos.
     */
    @GetMapping(
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun getTodos(): Iterable<TodoDTO> = service.getTodos()

    /**
     * Insert todo.
     * It consumes JSON, that is: request body Todo.
     */
    @PutMapping(
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
        consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun insertTodo(
        @RequestBody todo: TodoDTO
    ): TodoDTO = service.insertTodo(todo)

    /**
     * Delete todo.
     * We introduced path variable for Id to pass.
     */
    @DeleteMapping(
        value = ["/{id}"],
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun deleteTodo(@PathVariable(name = "id") id: String) = service.deleteTodo(id)

    /**
     * Update item.
     * It consumes JSON, that is: request..body Todo.
     */
    @PostMapping(
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
        consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
    )
    fun updateTodo(@RequestBody todo: TodoDTO) : TodoDTO = service.updateTodo(todo)

    @PostMapping(
        value = ["later_than"],
        produces = arrayOf(MediaType.APPLICATION_JSON_VALUE),
        consumes = arrayOf(MediaType.APPLICATION_JSON_VALUE)
        )
    fun getTodosLaterThan(
        @RequestBody payload:  TodoLaterThanRequest
    ): Iterable<TodoDTO> = service.getScheduledLaterThan(payload.date)
}