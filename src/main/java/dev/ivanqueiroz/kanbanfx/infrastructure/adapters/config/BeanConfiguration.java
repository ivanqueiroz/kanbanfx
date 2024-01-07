package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.config;

import dev.ivanqueiroz.kanbanfx.domain.application.ports.output.BoardOutputPort;
import dev.ivanqueiroz.kanbanfx.domain.application.ports.output.ColumnOutputPort;
import dev.ivanqueiroz.kanbanfx.domain.service.BoardService;
import dev.ivanqueiroz.kanbanfx.domain.service.ColumnService;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.BoardPersistenceAdapter;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.ColumnPersistenceAdapter;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.mapper.BoardPersistenceMapper;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.mapper.ColumnPersistenceMapper;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.repository.BoardRepository;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.repository.ColumnRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

  @Bean
  public BoardPersistenceAdapter boardPersistenceAdapter(
      BoardRepository boardRepository, BoardPersistenceMapper boardPersistenceMapper) {
    return new BoardPersistenceAdapter(boardRepository, boardPersistenceMapper);
  }

  @Bean
  public BoardService boardService(BoardOutputPort boardOutputPort) {
    return new BoardService(boardOutputPort);
  }

  @Bean
  public ColumnPersistenceAdapter columnPersistenceAdapter(
      ColumnRepository columnRepository,
      BoardRepository boardRepository,
      ColumnPersistenceMapper columnPersistenceMapper) {
    return new ColumnPersistenceAdapter(columnRepository, boardRepository, columnPersistenceMapper);
  }

  @Bean
  public ColumnService columnService(ColumnOutputPort columnOutputPort) {
    return new ColumnService(columnOutputPort);
  }
}
