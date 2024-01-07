package dev.ivanqueiroz.kanbanfx.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Task {
    private String title;
    private String description;
    @Builder.Default
    private TaskStatus status = TaskStatus.TODO;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
