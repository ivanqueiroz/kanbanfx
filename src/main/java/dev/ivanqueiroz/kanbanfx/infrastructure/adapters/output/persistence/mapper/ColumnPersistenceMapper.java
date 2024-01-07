package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.mapper;

import dev.ivanqueiroz.kanbanfx.domain.model.Column;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.entity.ColumnEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = TaskPersistenceMapper.class)
public interface ColumnPersistenceMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "board", ignore = true)
    ColumnEntity toColumnEntity(Column column);

    Column toColumn(ColumnEntity columnEntity);

    List<ColumnEntity> toColumEntities(List<Column> columns);

    List<Column> toColumns(List<ColumnEntity> columns);
}
