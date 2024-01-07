package dev.ivanqueiroz.kanbanfx.infrastructure.adapters.output.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "task")
public class TaskEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String title;
  private String description;
  private TaskStatusEntity status;
  @ManyToOne
  @JoinColumn(name = "column_id", nullable = false)
  private ColumnEntity column;
  @Column(name = "created_at")
  private LocalDateTime createdAt;

}
