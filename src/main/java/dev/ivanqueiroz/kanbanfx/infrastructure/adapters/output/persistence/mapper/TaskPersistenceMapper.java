package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.mapper;

import dev.ivanqueiroz.kanbanfx.domain.model.Task;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.entity.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskPersistenceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "column", ignore = true)
    TaskEntity toTaskEntity(Task task);

    Task toTask(TaskEntity taskEntity);
}
