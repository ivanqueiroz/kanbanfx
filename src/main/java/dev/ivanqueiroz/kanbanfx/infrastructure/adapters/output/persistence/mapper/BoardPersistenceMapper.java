package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.mapper;

import dev.ivanqueiroz.kanbanfx.domain.model.Board;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.entity.BoardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ColumnPersistenceMapper.class)
public interface BoardPersistenceMapper {
  @Mapping(target = "id", ignore = true)
  BoardEntity toBoardEntity(Board board);

  Board toBoard(BoardEntity boardEntity);
}
