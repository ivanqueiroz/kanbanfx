package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence;

import dev.ivanqueiroz.kanbanfx.domain.application.ports.output.ColumnOutputPort;
import dev.ivanqueiroz.kanbanfx.domain.model.Column;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.entity.BoardEntity;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.mapper.ColumnPersistenceMapper;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.repository.BoardRepository;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.repository.ColumnRepository;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ColumnPersistenceAdapter implements ColumnOutputPort {

    private final ColumnRepository columnRepository;
    private final BoardRepository boardRepository;
    private final ColumnPersistenceMapper columnPersistenceMapper;

    @Override
    public Column save(Column column) {
        var columnEntity = columnPersistenceMapper.toColumnEntity(column);
        var savedColumn = columnRepository.save(columnEntity);
        return columnPersistenceMapper.toColumn(savedColumn);
    }

    @Override
    public List<Column> findColumnsByBoardName(String boardName) {
        var boardEntity = boardRepository.findBoardEntityByNameEqualsIgnoreCase(boardName);
        Long boardId = boardEntity.map(BoardEntity::getId).orElseThrow();
        var columnEntities = columnRepository.findColumnEntitiesByBoardId(boardId);
        return columnPersistenceMapper.toColumns(columnEntities.orElse(Collections.emptyList()));
    }
}
