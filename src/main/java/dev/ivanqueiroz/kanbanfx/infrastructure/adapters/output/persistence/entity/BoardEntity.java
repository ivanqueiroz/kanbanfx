package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "board")
public class BoardEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  private String description;

  @OneToMany(mappedBy = "board")
  private List<ColumnEntity> columns;
}
