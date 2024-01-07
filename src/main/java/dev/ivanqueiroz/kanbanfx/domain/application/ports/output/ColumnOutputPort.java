package dev.ivanqueiroz.kanbanfx.domain.application.ports.output;

import dev.ivanqueiroz.kanbanfx.domain.model.Column;
import java.util.List;

public interface ColumnOutputPort {
  Column save(Column column);
  List<Column> findColumnsByBoardName(String boardName);
}
