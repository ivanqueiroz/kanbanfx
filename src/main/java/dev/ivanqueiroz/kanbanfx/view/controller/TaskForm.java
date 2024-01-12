package dev.ivanqueiroz.kanbanfx.view.controller;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Slf4j
@FxmlView
@Component
@RequiredArgsConstructor
public class TaskForm {

  private Stage stage;
  @FXML private VBox dialog;
  @FXML public Button saveButton;
  @FXML public Button cancelButton;

  @FXML
  public void initialize() {
    this.stage = new Stage();
    stage.setScene(new Scene(dialog));
  }

  public void show(Window window) {
    stage.initOwner(window);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.show();
    cancelButton.setOnAction(a -> stage.close());
  }
}
