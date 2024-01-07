package dev.ivanqueiroz.kanbanfx.domain.application.ports.output;

import dev.ivanqueiroz.kanbanfx.domain.model.Board;
import java.util.Optional;

public interface BoardOutputPort {

    Board save(Board board);
    Optional<Board> findBoardByName(String name);
}
