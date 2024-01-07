package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "column")
public class ColumnEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;
  private int workInProgressLimit;

  @ManyToOne
  @JoinColumn(name = "board_id", nullable = false)
  private BoardEntity board;

  @OneToMany(mappedBy = "column")
  List<TaskEntity> tasks;
}
