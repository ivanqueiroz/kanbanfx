package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.mapper;

import dev.ivanqueiroz.kanbanfx.domain.model.Column;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.entity.ColumnEntity;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TaskPersistenceMapper.class)
public interface ColumnPersistenceMapper {

  @Mapping(target = "board", ignore = true)
  ColumnEntity toColumnEntity(Column column);

  Column toColumn(ColumnEntity columnEntity);

  List<ColumnEntity> toColumEntities(List<Column> columns);

  List<Column> toColumns(List<ColumnEntity> columns);
}
