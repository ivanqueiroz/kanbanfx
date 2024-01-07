package dev.ivanqueiroz.kanbanfx.domain.service;

import dev.ivanqueiroz.kanbanfx.domain.application.ports.input.GetBoardUseCase;
import dev.ivanqueiroz.kanbanfx.domain.application.ports.output.BoardOutputPort;
import dev.ivanqueiroz.kanbanfx.domain.application.ports.input.CreateBoardUseCase;
import dev.ivanqueiroz.kanbanfx.domain.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService implements CreateBoardUseCase, GetBoardUseCase {

  private final BoardOutputPort boardOutputPort;

  @Override
  public Board createBoard(Board board) {
    return boardOutputPort.save(board);
  }

  @Override
  public Board getBoardByName(String name) {
    return boardOutputPort.findBoardByName(name).orElseThrow();
  }
}
