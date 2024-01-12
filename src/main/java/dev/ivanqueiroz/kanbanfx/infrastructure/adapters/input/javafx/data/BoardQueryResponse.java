package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardQueryResponse {
  private String description;
  private String name;
  private List<ColumnQueryResponse> columns;
}
