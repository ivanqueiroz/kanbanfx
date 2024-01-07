package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.repository;

import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.entity.ColumnEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ColumnRepository extends JpaRepository<ColumnEntity, Long> {
    Optional<List<ColumnEntity>> findColumnEntitiesByBoardId(Long boardId);
}
