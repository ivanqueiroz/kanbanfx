package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.mapper;

import dev.ivanqueiroz.kanbanfx.domain.model.Column;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.ColumnQueryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TaskJavaFxMapper.class)
public interface ColumnJavaFxMapper {
  ColumnQueryResponse toColumnQueryResponse(Column column);
}
