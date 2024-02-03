package com.example.taskkotlinapp.controller

import com.example.taskkotlinapp.data.model.TaskCreateRequest
import com.example.taskkotlinapp.data.model.TaskDto
import com.example.taskkotlinapp.data.model.TaskUpdateRequest
import com.example.taskkotlinapp.service.TaskService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class TaskController(private val service: TaskService) {

    @GetMapping("all-tasks")
    fun getAllTasks(): ResponseEntity<List<TaskDto>> = ResponseEntity(service.getAllTasks(), HttpStatus.OK)

    @GetMapping("open-tasks")
    fun getAllOpenTasks(): ResponseEntity<List<TaskDto>> = ResponseEntity(service.getAllOpenTasks(), HttpStatus.OK)

    @GetMapping("closed-tasks")
    fun getAllClosedTasks(): ResponseEntity<List<TaskDto>> = ResponseEntity(service.getAllClosedTasks(), HttpStatus.OK)

    @GetMapping("task/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<TaskDto> =
        ResponseEntity(service.getTaskById(id), HttpStatus.OK)

    @PostMapping("create")
    fun createTask(
        @Valid @RequestBody request: TaskCreateRequest
    ): ResponseEntity<TaskDto> = ResponseEntity(service.createTask(request), HttpStatus.OK)

    @PutMapping("update/{id}")
    fun updateTask(
        @PathVariable id: Long,
        @Valid @RequestBody request: TaskUpdateRequest
    ): ResponseEntity<TaskDto> = ResponseEntity(service.updateTask(id, request), HttpStatus.OK)

    @DeleteMapping("delete/{id}")
    fun deleteTask(@PathVariable id: Long): ResponseEntity<String> =
        ResponseEntity(service.deleteTask(id), HttpStatus.OK)

}