package com.example.services.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddTaskRequest {
    @NotNull(message = "The task name cannot be null.")
    @NotBlank(message = "The task name can't be empty.")
    private String name;
}