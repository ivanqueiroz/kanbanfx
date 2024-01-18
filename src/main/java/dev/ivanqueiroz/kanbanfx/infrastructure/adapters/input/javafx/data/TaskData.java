package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data;

import static dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.TaskStatusData.TODO;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskData {
  private Long id;
  private String title;
  private String description;
  @Builder.Default private TaskStatusData status = TODO;
  private Long position;
  @Builder.Default private LocalDateTime createdAt = LocalDateTime.now();
}
