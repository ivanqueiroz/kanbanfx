package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.mapper;

import dev.ivanqueiroz.kanbanfx.domain.model.Column;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.ColumnData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TaskJavaFxMapper.class)
public interface ColumnJavaFxMapper {
  ColumnData toColumnData(Column column);

  Column toColumn(ColumnData columnData);
}
