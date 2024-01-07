package dev.ivanqueiroz.kanbanfx;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootKanbanFxApplication {

    public static void main(String[] args) {
    Application.launch(KanbanFxMainApplication.class, args);
    }

}
