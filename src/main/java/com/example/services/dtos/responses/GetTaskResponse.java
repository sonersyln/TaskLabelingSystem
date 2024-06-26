package com.example.services.dtos.responses;

import com.example.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTaskResponse {
    private int id;
    private String name;
    private List<GetLabelResponse> labels;
    private GetUserResponse user;
}
