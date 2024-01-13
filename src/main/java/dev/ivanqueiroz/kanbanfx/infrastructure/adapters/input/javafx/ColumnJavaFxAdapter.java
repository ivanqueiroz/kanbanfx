package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx;

import dev.ivanqueiroz.kanbanfx.domain.application.ports.input.CreateColumnUseCase;
import dev.ivanqueiroz.kanbanfx.domain.application.ports.input.GetColumnsUseCase;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.TaskData;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.mapper.ColumnJavaFxMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ColumnJavaFxAdapter {
  private final GetColumnsUseCase getColumnsUseCase;
  private final CreateColumnUseCase createColumnUseCase;
  private final ColumnJavaFxMapper columnJavaFxMapper;

  @Transactional
  public void saveTasksToColumn(String columnName, List<TaskData> tasks) {
    var columnData =
        getColumnsUseCase
            .getColumnByName(columnName)
            .map(columnJavaFxMapper::toColumnQueryResponse)
            .map(
                column -> {
                  column.setTasks(tasks);
                  return column;
                });
    columnData.map(columnJavaFxMapper::toColumn).ifPresent(createColumnUseCase::updateColumn);
  }
}
