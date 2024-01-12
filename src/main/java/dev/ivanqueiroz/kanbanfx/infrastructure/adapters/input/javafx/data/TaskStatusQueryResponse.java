package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TaskStatusQueryResponse {
  TODO("todo"),
  IN_PROGRESS("in-progress"),
  DONE("done");
  private final String description;
}
