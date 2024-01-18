package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx;

import dev.ivanqueiroz.kanbanfx.domain.application.ports.input.GetColumnsUseCase;
import dev.ivanqueiroz.kanbanfx.domain.application.ports.input.UpdateColumnUseCase;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.ColumnData;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.mapper.ColumnJavaFxMapper;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ColumnJavaFxAdapter {
  private final GetColumnsUseCase getColumnsUseCase;
  private final UpdateColumnUseCase updateColumnUseCase;
  private final ColumnJavaFxMapper columnJavaFxMapper;

  @Transactional
  public ColumnData save(ColumnData columnData) {
    var column = columnJavaFxMapper.toColumn(columnData);
    var columnSaved = updateColumnUseCase.updateColumn(column);
    return columnJavaFxMapper.toColumnData(columnSaved);
  }

  @Transactional
  public Optional<ColumnData> getColumnByName(String columnName) {
    return getColumnsUseCase.getColumnByName(columnName).map(columnJavaFxMapper::toColumnData);
  }
}
