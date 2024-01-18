package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx;

import dev.ivanqueiroz.kanbanfx.domain.application.ports.input.DeleteTaskUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TaskJavaFxAdapter {

  private final DeleteTaskUseCase deleteTaskUseCase;

  public void deleteTaskById(Long id) {
    deleteTaskUseCase.deleteTaskById(id);
  }
}
