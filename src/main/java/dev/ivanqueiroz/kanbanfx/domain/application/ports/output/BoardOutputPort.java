package dev.ivanqueiroz.kanbanfx.domain.application.ports.output;

import dev.ivanqueiroz.kanbanfx.domain.model.Board;
import java.util.Optional;

public interface BoardOutputPort {
  Optional<Board> findBoardByName(String name);

  Optional<Board> findBoardById(Long id);
}
