package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.mapper;

import dev.ivanqueiroz.kanbanfx.domain.model.Board;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.BoardQueryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ColumnJavaFxMapper.class)
public interface BoardJavaFxMapper {

  BoardQueryResponse toBoardQueryResponse(Board board);
}
