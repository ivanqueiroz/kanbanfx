package dev.ivanqueiroz.kanbanfx.domain.model;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Column {

  private String name;
  private int workInProgressLimit;
  private List<Task> tasks;
}
