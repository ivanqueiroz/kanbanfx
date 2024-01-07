package dev.ivanqueiroz.kanbanfx.domain.application.ports.input;

import dev.ivanqueiroz.jkaban.domain.model.Board;

public interface CreateBoardUseCase {
    Board createBoard(Board board);
}
