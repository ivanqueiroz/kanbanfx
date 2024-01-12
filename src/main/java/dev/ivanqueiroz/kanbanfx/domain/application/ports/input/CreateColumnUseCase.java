package dev.ivanqueiroz.kanbanfx.domain.application.ports.input;

import dev.ivanqueiroz.kanbanfx.domain.model.Column;

public interface CreateColumnUseCase {
  Column createColumn(Column column);
}
