package dev.ivanqueiroz.kanbanfx.domain.application.ports.output;

import dev.ivanqueiroz.kanbanfx.domain.model.Column;
import java.util.List;
import java.util.Optional;

public interface ColumnOutputPort {
  Column save(Column column);

  List<Column> findColumnsByBoardName(String boardName);

  Optional<Column> findColumnByName(String name);
}
