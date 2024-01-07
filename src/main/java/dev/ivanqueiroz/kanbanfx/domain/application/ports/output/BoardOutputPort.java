package dev.ivanqueiroz.kanbanfx.domain.application.ports;

import dev.ivanqueiroz.kanbanfx.domain.model.Board;

import java.util.List;
import java.util.Optional;

public interface BoardOutputPort {

    Board save(Board board);
    Optional<Board> findAllBoardById(Long boardId);
}
