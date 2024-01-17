package io.evolutioncode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Table(name = "todo")
public class ToDoData {
    @Id
    @GeneratedValue
    private Integer id;
    private String description;
    private boolean checked;
}
