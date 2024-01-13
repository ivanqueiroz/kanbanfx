package dev.ivanqueiroz.kanbanfx.domain.application.ports.input;

import dev.ivanqueiroz.kanbanfx.domain.model.Board;
import dev.ivanqueiroz.kanbanfx.domain.model.Column;
import java.util.List;
import java.util.Optional;

public interface GetColumnsUseCase {
  List<Column> getAllColumnsForBoard(Board board);

  Optional<Column> getColumnByName(String name);
}
