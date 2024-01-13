package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TaskStatusData {
  TODO("todo"),
  IN_PROGRESS("in-progress"),
  DONE("done");
  private final String description;

  public static TaskStatusData getByDescription(String description) {
    return switch (description.toLowerCase()) {
      case "todo" -> TODO;
      case "in-progress" -> IN_PROGRESS;
      case "done" -> DONE;
      default -> throw new IllegalStateException("Unexpected value: " + description.toLowerCase());
    };
  }
}
