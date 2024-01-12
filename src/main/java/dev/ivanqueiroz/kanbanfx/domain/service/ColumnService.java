package dev.ivanqueiroz.kanbanfx.domain.service;

import dev.ivanqueiroz.kanbanfx.domain.application.ports.input.CreateColumnUseCase;
import dev.ivanqueiroz.kanbanfx.domain.application.ports.input.GetColumnsUseCase;
import dev.ivanqueiroz.kanbanfx.domain.application.ports.output.ColumnOutputPort;
import dev.ivanqueiroz.kanbanfx.domain.model.Board;
import dev.ivanqueiroz.kanbanfx.domain.model.Column;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColumnService implements CreateColumnUseCase, GetColumnsUseCase {

  private final ColumnOutputPort columnOutputPort;

  @Override
  public Column createColumn(Column column) {
    return columnOutputPort.save(column);
  }

  @Override
  public List<Column> getAllColumnsForBoard(Board board) {
    return columnOutputPort.findColumnsByBoardName(board.getName());
  }
}
