package dev.ivanqueiroz.kanbanfx.view.controller;

import dev.ivanqueiroz.kanbanfx.StageReadyEventListener;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.ColumnJavaFxAdapter;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.ColumnData;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.TaskData;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.TaskStatusData;
import dev.ivanqueiroz.kanbanfx.view.components.MultiColumnListView;
import java.util.Objects;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Slf4j
@FxmlView
@Component
@RequiredArgsConstructor
@Getter
@Setter
public class TaskForm {

  private final ColumnJavaFxAdapter columnJavaFxAdapter;

  private Stage stage;
  private TaskData taskData;
  @FXML private VBox dialog;
  @FXML private Button saveButton;
  @FXML private Button cancelButton;
  @FXML private Label nameLbl;
  @FXML private TextField nameTxtField;
  @FXML private TextArea descriptionTxtArea;
  @FXML private Label descriptionLbl;
  private TaskStatusData taskStatusData;
  private ColumnData columnData;
  private ObservableList<TaskData> items;

  @FXML
  public void initialize() {
    this.stage = new Stage();
    var scene = new Scene(dialog);
    scene
        .getStylesheets()
        .add(
            Objects.requireNonNull(
                    StageReadyEventListener.class.getResource("multi-column-app.css"))
                .toExternalForm());
    stage.initStyle(StageStyle.UNDECORATED);
    stage.setScene(scene);
  }

  public void show(
      ActionEvent event, ColumnData columnData, MultiColumnListView<TaskData> multiColumnListView) {

    initDialog(event, columnData, multiColumnListView);
  }

  private void initDialog(
      ActionEvent event, ColumnData columnData, MultiColumnListView<TaskData> multiColumnListView) {
    this.taskStatusData = TaskStatusData.getByDescription(columnData.getName().toLowerCase());
    this.items = multiColumnListView.getColumns().get(taskStatusData.ordinal()).getItems();
    this.columnData = columnData;
    defineStyles();
    defineDialogModal(event);
    initButtons();
  }

  private void defineStyles() {
    dialog.getStyleClass().add("form-task");
    dialog.getStyleClass().removeAll("todo", "in-progress", "done");
    dialog.getStyleClass().add(taskStatusData.getDescription());
    nameLbl.getStyleClass().add("label-form");
    descriptionLbl.getStyleClass().add("label-form");
  }

  private void initButtons() {
    cancelButton.setOnAction(evt -> stage.close());
    saveButton.setOnAction(
        evt -> {
          taskData = getFormData();
          addTask();
          stage.close();
        });
  }

  private void addTask() {
    addTaskToDatabase(addTaskToView());
  }

  private void addTaskToDatabase(ObservableList<TaskData> items) {
    columnData.setTasks(items);
    this.columnData = columnJavaFxAdapter.save(columnData);
  }

  private ObservableList<TaskData> addTaskToView() {
    taskData.setPosition((long) items.size());
    items.add(taskData);
    return items;
  }

  private TaskData getFormData() {
    return TaskData.builder()
        .title(nameTxtField.getText())
        .description(descriptionTxtArea.getText())
        .build();
  }

  private void defineDialogModal(ActionEvent event) {
    var window = ((Node) event.getTarget()).getScene().getWindow();
    stage.initOwner(window);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.show();
  }
}
