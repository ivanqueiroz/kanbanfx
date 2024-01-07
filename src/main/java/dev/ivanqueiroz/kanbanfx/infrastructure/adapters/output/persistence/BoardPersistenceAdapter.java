package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence;

import dev.ivanqueiroz.kanbanfx.domain.application.ports.output.BoardOutputPort;
import dev.ivanqueiroz.kanbanfx.domain.model.Board;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.mapper.BoardPersistenceMapper;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.repository.BoardRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class BoardPersistenceAdapter implements BoardOutputPort {
  private final BoardRepository boardRepository;
  private final BoardPersistenceMapper boardPersistenceMapper;

  @Override
  public Board save(Board board) {
    var boardEntity = boardPersistenceMapper.toBoardEntity(board);
    var savedBoard = boardRepository.save(boardEntity);
    return boardPersistenceMapper.toBoard(savedBoard);
  }

  @Transactional
  @Override
  public Optional<Board> findBoardByName(String name) {
    var boardEntity = boardRepository.findBoardEntityByNameEqualsIgnoreCase(name);
    return boardEntity.map(boardPersistenceMapper::toBoard);
  }
}
