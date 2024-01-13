package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.repository;

import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.entity.ColumnEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColumnRepository extends JpaRepository<ColumnEntity, Long> {
  Optional<List<ColumnEntity>> findColumnEntitiesByBoardId(Long boardId);

  Optional<ColumnEntity> findColumnEntityByNameEqualsIgnoreCase(String name);
}
