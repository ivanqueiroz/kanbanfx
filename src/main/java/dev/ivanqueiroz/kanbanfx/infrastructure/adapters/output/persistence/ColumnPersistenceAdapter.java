package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence;

import dev.ivanqueiroz.kanbanfx.domain.application.ports.output.ColumnOutputPort;
import dev.ivanqueiroz.kanbanfx.domain.model.Column;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.entity.BoardEntity;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.entity.ColumnEntity;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.mapper.ColumnPersistenceMapper;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.repository.BoardRepository;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.repository.ColumnRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ColumnPersistenceAdapter implements ColumnOutputPort {

  private final ColumnRepository columnRepository;
  private final BoardRepository boardRepository;
  private final ColumnPersistenceMapper columnPersistenceMapper;

  @Override
  public Column save(Column column) {
    var columnEntityFromDb =
        columnRepository.findColumnEntityByNameEqualsIgnoreCase(column.getName()).orElseThrow();
    var columnEntity = updateOldTasks(column, columnEntityFromDb);
    columnEntity.setBoard(columnEntityFromDb.getBoard());
    columnEntity.setId(columnEntityFromDb.getId());
    var savedColumn = columnRepository.save(columnEntity);
    return columnPersistenceMapper.toColumn(savedColumn);
  }

  private ColumnEntity updateOldTasks(Column column, ColumnEntity columnEntityFromDb) {
    var columnEntityMapper = columnPersistenceMapper.toColumnEntity(column);

    columnEntityMapper
        .getTasks()
        .forEach(
            taskEntityMapper ->
                columnEntityFromDb.getTasks().stream()
                    .filter(
                        taskEntity ->
                            taskEntityMapper.getId() != null
                                && taskEntityMapper.getPosition().equals(taskEntity.getPosition()))
                    .toList()
                    .stream()
                    .findFirst()
                    .ifPresent(
                        taskEntity -> {
                          taskEntityMapper.setId(taskEntity.getId());
                          taskEntityMapper.setColumn(taskEntity.getColumn());
                          taskEntityMapper.setTitle(taskEntity.getTitle());
                          taskEntityMapper.setDescription(taskEntity.getDescription());
                          taskEntityMapper.setPosition(taskEntity.getPosition());
                        }));

    columnEntityMapper.getTasks().stream()
        .filter(taskEntityMapper -> taskEntityMapper.getId() == null)
        .forEach(taskEntityMapper -> taskEntityMapper.setColumn(columnEntityFromDb));
    return columnEntityMapper;
  }

  @Override
  public List<Column> findColumnsByBoardName(String boardName) {
    var boardEntity = boardRepository.findBoardEntityByNameEqualsIgnoreCase(boardName);
    Long boardId = boardEntity.map(BoardEntity::getId).orElseThrow();
    var columnEntities = columnRepository.findColumnEntitiesByBoardId(boardId);
    return columnPersistenceMapper.toColumns(columnEntities.orElse(Collections.emptyList()));
  }

  @Override
  public Optional<Column> findColumnByName(String name) {
    var columnEntity = columnRepository.findColumnEntityByNameEqualsIgnoreCase(name);
    return columnEntity.map(columnPersistenceMapper::toColumn);
  }
}
