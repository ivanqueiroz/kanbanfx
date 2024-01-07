package dev.ivanqueiroz.kanbanfx;

import dev.ivanqueiroz.kanbanfx.view.BoardApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KanbanFxApplication {

    public static void main(String[] args) {
    Application.launch(BoardApplication.class, args);
    }

}
