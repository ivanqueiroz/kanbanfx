package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data;

import static dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.TaskStatusData.TODO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskData {
  private String title;
  private String description;
  @Builder.Default private TaskStatusData status = TODO;
  private Long position;
}
