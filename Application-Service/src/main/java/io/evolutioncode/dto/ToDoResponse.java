package io.evolutioncode.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ToDoResponse {
    private Integer id;
    private String description;
    private boolean checked;
}
