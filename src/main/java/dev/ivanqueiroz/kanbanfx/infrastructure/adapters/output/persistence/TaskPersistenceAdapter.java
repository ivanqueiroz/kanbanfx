package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence;

import dev.ivanqueiroz.kanbanfx.domain.application.ports.output.TaskOutputPort;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TaskPersistenceAdapter implements TaskOutputPort {

  private final TaskRepository taskRepository;

  @Override
  public void deleteTaskById(Long id) {
    taskRepository.deleteById(id);
  }
}
