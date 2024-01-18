package dev.ivanqueiroz.kanbanfx.domain.application.ports.input;

import dev.ivanqueiroz.kanbanfx.domain.model.Column;

public interface UpdateColumnUseCase {

  Column updateColumn(Column column);
}
