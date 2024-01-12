package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx;

import dev.ivanqueiroz.kanbanfx.domain.application.ports.input.GetBoardUseCase;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.BoardQueryResponse;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.mapper.BoardJavaFxMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoardJavaFxAdapter {
  private final GetBoardUseCase getBoardUseCase;
  private final BoardJavaFxMapper boardJavaFxMapper;

  public BoardQueryResponse getBoardByName(String name) {
    var board = getBoardUseCase.getBoardByName(name);
    return boardJavaFxMapper.toBoardQueryResponse(board);
  }
}
