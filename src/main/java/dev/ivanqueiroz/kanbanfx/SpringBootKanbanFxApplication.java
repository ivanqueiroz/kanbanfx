package dev.ivanqueiroz.kanbanfx;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringBootKanbanFxApplication {

  public static void main(String[] args) {
    Application.launch(KanbanFxMainApplication.class, args);
  }
}
