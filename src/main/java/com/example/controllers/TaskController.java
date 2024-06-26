package com.example.controllers;

import com.example.core.utilities.results.DataResult;
import com.example.core.utilities.results.Result;
import com.example.services.abstracts.TaskService;
import com.example.services.dtos.requests.taskRequests.AddTaskRequest;
import com.example.services.dtos.requests.taskRequests.UpdateTaskRequest;
import com.example.services.dtos.responses.GetTaskListResponse;
import com.example.services.dtos.responses.GetTaskResponse;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
@CrossOrigin
public class TaskController {

    private final TaskService taskService;

    @GetMapping()
    public DataResult<List<GetTaskListResponse>> getAllTasks() {
        return this.taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public DataResult<GetTaskResponse> getById(@PathVariable int id) {
        return this.taskService.getById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public Result addTask(@RequestBody @Valid AddTaskRequest addTaskRequest) throws MessagingException, UnsupportedEncodingException {
        return this.taskService.addTask(addTaskRequest);
    }

    @PutMapping()
    public Result updateTask(@RequestBody @Valid UpdateTaskRequest updateTaskRequest) {
        return this.taskService.updateTask(updateTaskRequest);
    }

    @DeleteMapping("/{id}")
    public Result deleteTask(@PathVariable int id) {
        return this.taskService.deleteTask(id);
    }

    @PostMapping("/{taskId}/labels/{labelId}")
    public Result addLabelToTask(@PathVariable int taskId, @PathVariable int labelId) {
        return taskService.addLabelToTask(taskId, labelId);
    }

    @GetMapping("/labels/{labelId}")
    public DataResult<List<GetTaskResponse>> getAllTasksByLabelId(@PathVariable int labelId) {
        return taskService.getAllTasksByLabelId(labelId);
    }
}
