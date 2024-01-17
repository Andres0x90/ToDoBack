package io.evolutioncode.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ToDo {
    private Integer id;
    private String description;
    private boolean checked;
}
