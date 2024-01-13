package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "task")
public class TaskEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_id_tasks")
  @SequenceGenerator(name = "sequence_id_tasks", sequenceName = "task_seq", allocationSize = 1)
  private Long id;

  private String title;
  private String description;
  private TaskStatusEntity status;

  @ManyToOne
  @JoinColumn(name = "column_id", nullable = false)
  private ColumnEntity column;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  private Long position;
}
