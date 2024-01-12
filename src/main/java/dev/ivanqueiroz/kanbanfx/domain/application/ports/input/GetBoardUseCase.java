package dev.ivanqueiroz.kanbanfx.domain.application.ports.input;

import dev.ivanqueiroz.kanbanfx.domain.model.Board;

public interface GetBoardUseCase {
  Board getBoardByName(String name);
}
