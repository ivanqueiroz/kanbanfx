package dev.ivanqueiroz.kanbanfx.domain.application.ports.input;

import dev.ivanqueiroz.kanbanfx.domain.model.Board;

public interface CreateBoardUseCase {
  Board createBoard(Board board);
}
