package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ColumnQueryResponse {
    private Long id;
    private String description;
    private String name;
}
