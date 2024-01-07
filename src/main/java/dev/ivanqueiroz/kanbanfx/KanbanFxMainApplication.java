package dev.ivanqueiroz.kanbanfx.view;

import dev.ivanqueiroz.kanbanfx.SpringBootKanbanFxApplication;
import dev.ivanqueiroz.kanbanfx.view.event.StageReadyEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class KanbanFxMainApplication extends Application {
  private ConfigurableApplicationContext applicationContext;

  @Override
  public void start(Stage stage) {
    applicationContext.publishEvent(new StageReadyEvent(stage));
  }

  @Override
  public void init() {
    applicationContext = new SpringApplicationBuilder(SpringBootKanbanFxApplication.class).run();
  }

  @Override
  public void stop() {
    applicationContext.close();
    Platform.exit();
  }

}
