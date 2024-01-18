package dev.ivanqueiroz.kanbanfx.domain.service;

import dev.ivanqueiroz.kanbanfx.domain.application.ports.input.DeleteTaskUseCase;
import dev.ivanqueiroz.kanbanfx.domain.application.ports.output.TaskOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService implements DeleteTaskUseCase {

  private final TaskOutputPort taskOutputPort;

  @Override
  public void deleteTaskById(Long id) {
    taskOutputPort.deleteTaskById(id);
  }
}
