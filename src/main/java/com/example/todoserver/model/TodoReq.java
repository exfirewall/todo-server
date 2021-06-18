package com.example.todoserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoReq {
    private String title;
    private Long Order;
    private Boolean completed;
}
