package dev.ivanqueiroz.kanbanfx.domain.application.ports.input;

import dev.ivanqueiroz.kanbanfx.domain.model.Board;
import dev.ivanqueiroz.kanbanfx.domain.model.Column;
import java.util.List;

public interface GetColumnsUseCase {
  List<Column> getAllColumnsForBoard(Board board);
}
