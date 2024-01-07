package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.mapper;


import dev.ivanqueiroz.kanbanfx.domain.model.Task;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.TaskQueryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskJavaFxMapper {

    TaskQueryResponse toTaskQueryResponse(Task task);

}
