package dev.ivanqueiroz.kanbanfx.view;

import dev.ivanqueiroz.kanbanfx.view.event.StageReadyEvent;
import dev.ivanqueiroz.kanbanfx.view.main.MainController;
import javafx.scene.Scene;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageReadyEventListener implements ApplicationListener<StageReadyEvent> {

  private final String applicationTitle;
  private final FxWeaver fxWeaver;

  public StageReadyEventListener(
      @Value("${spring.application.ui.title}") String applicationTitle, FxWeaver fxWeaver) {
    this.applicationTitle = applicationTitle;
    this.fxWeaver = fxWeaver;
  }

  @Override
  public void onApplicationEvent(StageReadyEvent event) {
    var stage = event.getStage();
    stage.setScene(new Scene(fxWeaver.loadView(MainController.class)));
    stage.setTitle(applicationTitle);
    stage.show();
  }
}
