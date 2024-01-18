package dev.ivanqueiroz.kanbanfx.domain.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Board {
  private Long id;
  private String name;
  private String description;
  private List<Column> columns;
}
