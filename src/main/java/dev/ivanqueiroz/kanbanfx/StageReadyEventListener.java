package dev.ivanqueiroz.kanbanfx;

import dev.ivanqueiroz.kanbanfx.view.controller.MainWindow;
import dev.ivanqueiroz.kanbanfx.view.event.StageReadyEvent;
import fr.brouillard.oss.cssfx.CSSFX;
import javafx.scene.Parent;
import javafx.scene.Scene;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class StageReadyEventListener implements ApplicationListener<StageReadyEvent> {

  @Value("${spring.application.ui.title}")
  private final String applicationTitle;

  private final FxWeaver fxWeaver;

  @Override
  public void onApplicationEvent(StageReadyEvent event) {
    var stage = event.getStage();
    Parent parent = fxWeaver.loadView(MainWindow.class);
    var scene = new Scene(parent);
    scene
        .getStylesheets()
        .add(
            Objects.requireNonNull(
                    StageReadyEventListener.class.getResource("multi-column-app.css"))
                .toExternalForm());
    CSSFX.start();
    stage.setScene(scene);
    stage.setTitle(applicationTitle);
    stage.centerOnScreen();
    stage.show();
  }
}
