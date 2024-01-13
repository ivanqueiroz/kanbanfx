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
@Table(name = "column")
public class ColumnEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_column")
  @SequenceGenerator(name = "sequence_id_column", sequenceName = "column_seq", allocationSize = 1)
  private Long id;

  private String name;
  private int workInProgressLimit;

  @ManyToOne
  @JoinColumn(name = "board_id", nullable = false)
  private BoardEntity board;

  @OneToMany(mappedBy = "column", cascade = CascadeType.ALL)
  List<TaskEntity> tasks;
}
