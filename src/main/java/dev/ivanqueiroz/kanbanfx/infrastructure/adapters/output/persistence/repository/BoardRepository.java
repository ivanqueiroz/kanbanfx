package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.repository;

import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.entity.BoardEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
  Optional<BoardEntity> findBoardEntityByNameEqualsIgnoreCase(String name);
}
