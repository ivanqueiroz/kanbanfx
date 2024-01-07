package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardQueryResponse {
    private String description;
    private String name;
    private List<ColumnQueryResponse> columns;
}
