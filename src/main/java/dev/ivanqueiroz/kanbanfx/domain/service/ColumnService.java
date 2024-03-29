package dev.ivanqueiroz.kanbanfx.domain.service;

import dev.ivanqueiroz.kanbanfx.domain.application.ports.input.GetColumnsUseCase;
import dev.ivanqueiroz.kanbanfx.domain.application.ports.input.UpdateColumnUseCase;
import dev.ivanqueiroz.kanbanfx.domain.application.ports.output.ColumnOutputPort;
import dev.ivanqueiroz.kanbanfx.domain.model.Board;
import dev.ivanqueiroz.kanbanfx.domain.model.Column;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColumnService implements UpdateColumnUseCase, GetColumnsUseCase {

  private final ColumnOutputPort columnOutputPort;

  @Override
  public Column updateColumn(Column column) {
    return columnOutputPort.save(column);
  }

  @Override
  public List<Column> getAllColumnsForBoard(Board board) {
    return columnOutputPort.findColumnsByBoardName(board.getName());
  }

  @Override
  public Optional<Column> getColumnByName(String name) {
    return columnOutputPort.findColumnByName(name);
  }
}
