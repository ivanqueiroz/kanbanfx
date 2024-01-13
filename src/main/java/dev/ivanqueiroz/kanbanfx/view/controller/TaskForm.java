package dev.ivanqueiroz.kanbanfx.view.controller;

import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.ColumnJavaFxAdapter;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.TaskData;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.TaskStatusData;
import dev.ivanqueiroz.kanbanfx.view.components.MultiColumnListView;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
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
  @FXML private TextField nameTxtField;
  @FXML private TextArea descriptionTxtArea;

  @FXML
  public void initialize() {
    this.stage = new Stage();
    stage.setScene(new Scene(dialog));
  }

  public void show(
      ActionEvent event,
      TaskStatusData taskStatusData,
      MultiColumnListView<TaskData> multiColumnListView) {

    initDialog(event, taskStatusData, multiColumnListView);
  }

  private void initDialog(
      ActionEvent event,
      TaskStatusData taskStatusData,
      MultiColumnListView<TaskData> multiColumnListView) {

    defineDialogModal(event);
    initButtons(taskStatusData, multiColumnListView);
  }

  private void initButtons(
      TaskStatusData taskStatusData, MultiColumnListView<TaskData> multiColumnListView) {
    cancelButton.setOnAction(evt -> stage.close());
    saveButton.setOnAction(
        evt -> {
          taskData = getFormData();
          addTask(taskStatusData, multiColumnListView);
          stage.close();
        });
  }

  private void addTask(
      TaskStatusData taskStatusData, MultiColumnListView<TaskData> multiColumnListView) {
    var items = multiColumnListView.getColumns().get(taskStatusData.ordinal()).getItems();
    addTaskToView(items);
    saveInDataBase(taskStatusData, items);
  }

  private void saveInDataBase(TaskStatusData taskStatusData, ObservableList<TaskData> items) {
    columnJavaFxAdapter.saveTasksToColumn(taskStatusData.getDescription(), items);
  }

  private void addTaskToView(ObservableList<TaskData> items) {
    taskData.setPosition((long) items.size());
    items.add(taskData);
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
