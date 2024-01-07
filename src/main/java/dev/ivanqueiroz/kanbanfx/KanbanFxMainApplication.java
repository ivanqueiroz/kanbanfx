package dev.ivanqueiroz.kanbanfx;

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
    applicationContext =
        new SpringApplicationBuilder(SpringBootKanbanFxApplication.class)
            .run(getParameters().getRaw().toArray(new String[0]));
  }

  @Override
  public void stop() {
    applicationContext.close();
    Platform.exit();
  }
}
