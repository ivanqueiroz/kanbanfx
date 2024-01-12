package dev.ivanqueiroz.kanbanfx.view.controller;

import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.BoardJavaFxAdapter;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.BoardQueryResponse;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.ColumnQueryResponse;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.TaskQueryResponse;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.TaskStatusQueryResponse;
import dev.ivanqueiroz.kanbanfx.view.components.MultiColumnListView;
import dev.ivanqueiroz.kanbanfx.view.components.MultiColumnListView.ColumnListCell;
import dev.ivanqueiroz.kanbanfx.view.components.MultiColumnListView.ListViewColumn;
import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxControllerAndView;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Slf4j
@FxmlView
@Component
@RequiredArgsConstructor
public class MainWindow {

  @FXML private MenuItem quitMenuItem;
  @FXML private BorderPane borderPane;
  private final BoardJavaFxAdapter boardJavaFxAdapter;
  private final FxWeaver fxWeaver;

  @FXML
  public void initialize() {
    initializeMenu();
    initializeBoard();
  }

  private void initializeBoard() {
    var multiColumnListView = new MultiColumnListView<TaskQueryResponse>();
    multiColumnListView.setCellFactory(listView -> new TaskListCell(multiColumnListView));

    var columns = createBoardColumns(boardJavaFxAdapter.getBoardByName("Task Board"));
    multiColumnListView.getColumns().setAll(columns);

    multiColumnListView.setPlaceholderFrom(
        TaskQueryResponse.builder().title("from").description("from_description").build());
    multiColumnListView.setPlaceholderTo(
        TaskQueryResponse.builder().title("to").description("to_description").build());
    VBox.setVgrow(multiColumnListView, Priority.ALWAYS);
    var boardColumnsVBox = createBoardColumnsVBox(multiColumnListView);
    borderPane.setCenter(boardColumnsVBox);
  }

  private static VBox createBoardColumnsVBox(
      MultiColumnListView<TaskQueryResponse> multiColumnListView) {
    var optionsBox = createBoardColumnsOptionBoxActions(multiColumnListView);
    var vbox = new VBox(10, multiColumnListView, optionsBox);
    vbox.setAlignment(Pos.TOP_RIGHT);
    vbox.setPadding(new Insets(20));
    return vbox;
  }

  private static HBox createBoardColumnsOptionBoxActions(
      MultiColumnListView<TaskQueryResponse> multiColumnListView) {
    var optionBoxConfig = getBoardColumnsOptionBoxConfig(multiColumnListView);

    var optionsBox =
        new HBox(
            10,
            optionBoxConfig.separators(),
            optionBoxConfig.showHeaders(),
            optionBoxConfig.disableDragAndDrop());
    optionsBox.setAlignment(Pos.CENTER_RIGHT);
    return optionsBox;
  }

  private static OptionBoxConfig getBoardColumnsOptionBoxConfig(
      MultiColumnListView<TaskQueryResponse> multiColumnListView) {
    var showHeaders = new CheckBox("Show Headers");
    showHeaders.selectedProperty().bindBidirectional(multiColumnListView.showHeadersProperty());

    var disableDragAndDrop = new CheckBox("Disable Editing");
    disableDragAndDrop
        .selectedProperty()
        .bindBidirectional(multiColumnListView.disableDragAndDropProperty());

    var separatorFactory = multiColumnListView.getSeparatorFactory();

    var separators = new CheckBox("Use Separators");
    separators.setSelected(true);
    separators
        .selectedProperty()
        .addListener(
            it -> {
              if (separators.isSelected()) {
                multiColumnListView.setSeparatorFactory(separatorFactory);
              } else {
                multiColumnListView.setSeparatorFactory(null);
              }
            });
    return new OptionBoxConfig(showHeaders, disableDragAndDrop, separators);
  }

  private record OptionBoxConfig(
      CheckBox showHeaders, CheckBox disableDragAndDrop, CheckBox separators) {}

  private List<ListViewColumn<TaskQueryResponse>> createBoardColumns(BoardQueryResponse taskBoard) {
    return taskBoard.getColumns().stream()
        .map(
            columnQueryResponse -> {
              var result = new ListViewColumn<TaskQueryResponse>();
              var header = createColumnHeader(columnQueryResponse);
              result.setHeader(header);
              result.getItems().setAll(columnQueryResponse.getTasks());
              return result;
            })
        .toList();
  }

  private HBox createColumnHeader(ColumnQueryResponse columnQueryResponse) {
    var header = new HBox();
    header.setAlignment(Pos.CENTER_LEFT);

    var headerRight = new HBox();
    headerRight.setAlignment(Pos.CENTER_RIGHT);
    var createTaskActionButton = getButton();
    headerRight.getChildren().add(createTaskActionButton);

    HBox.setHgrow(headerRight, Priority.ALWAYS);
    header.getChildren().addAll(new Label(columnQueryResponse.getName()), headerRight);
    return header;
  }

  private Button getButton() {
    var createTaskActionButton = new Button("+");

    createTaskActionButton.setOnAction(
        event -> {
          FxControllerAndView<TaskForm, VBox> taskForm = fxWeaver.load(TaskForm.class);
          var window = ((Node) event.getTarget()).getScene().getWindow();
          taskForm.getController().show(window);
        });

    return createTaskActionButton;
  }

  private void initializeMenu() {
    quitMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
    quitMenuItem.setOnAction(e -> Platform.exit());
  }

  @Slf4j
  private static class TaskListCell extends ColumnListCell<TaskQueryResponse> {
    private final BooleanProperty placeholder =
        new SimpleBooleanProperty(this, "placeholder", false);
    private StringProperty taskDescription;

    public TaskListCell(MultiColumnListView<TaskQueryResponse> multiColumnListView) {
      super(multiColumnListView);
      getStyleClass().add("issue-list-cell");
      var content = new VBox();
      content.getStyleClass().add("content");

      content.visibleProperty().bind(placeholder.not().and(emptyProperty().not()));
      content.managedProperty().bind(placeholder.not().and(emptyProperty().not()));

      var contentPlaceholder = new VBox();
      contentPlaceholder.getStyleClass().add("placeholder");
      contentPlaceholder.visibleProperty().bind(placeholder);
      contentPlaceholder.managedProperty().bind(placeholder);

      var taskTitleLbl = new Label();
      taskTitleLbl.setStyle("-fx-font-size: 12");
      taskTitleLbl.textProperty().bind(textProperty());
      taskTitleLbl.setPadding(new Insets(10, 10, 0, 10));

      var separator = new Separator();
      separator.setPadding(new Insets(10, 10, 0, 10));

      var taskDescriptionLbl = new Label();
      taskDescriptionLbl.setStyle("-fx-font-size: 10");
      taskDescriptionLbl.textProperty().bind(taskDescriptionProperty());
      taskDescriptionLbl.setPadding(new Insets(10, 10, 0, 10));

      content.getChildren().add(taskTitleLbl);
      content.getChildren().add(separator);
      content.getChildren().add(taskDescriptionLbl);

      var wrapper = new StackPane(content, contentPlaceholder);
      setGraphic(wrapper);
      setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
    }

    @Override
    protected void updateItem(TaskQueryResponse item, boolean empty) {
      super.updateItem(item, empty);

      placeholder.set(false);

      getStyleClass().removeAll("todo", "in-progress", "done");

      if (item != null && !empty) {
        if (item == getMultiColumnListView().getPlaceholderFrom()) {
          placeholder.set(true);
          setText("From");
        } else if (item == getMultiColumnListView().getPlaceholderTo()) {
          placeholder.set(true);
          setText("To");
        } else {
          setText(item.getTitle());
          setTaskDescription(item.getDescription());
          getStyleClass().add(item.getStatus().getDescription());
          getTaskColumnIndex(item)
              .ifPresent(columnIndex -> updateTaskStatus(item, columnIndex, getStyleClass()));
        }
      } else {
        setText("");
      }
    }

    public final void setTaskDescription(String value) {
      taskDescriptionProperty().setValue(value);
    }

    public final StringProperty taskDescriptionProperty() {
      if (taskDescription == null) {
        taskDescription = new SimpleStringProperty(this, "taskDescription", "");
      }
      return taskDescription;
    }

    private static void updateTaskStatus(
        TaskQueryResponse item, Integer columnIndex, ObservableList<String> styleClass) {
      log.debug("Task updated {}", item);
      item.setStatus(TaskStatusQueryResponse.values()[columnIndex]);
      styleClass.add(item.getStatus().getDescription());
    }

    private Optional<Integer> getTaskColumnIndex(TaskQueryResponse item) {
      log.debug("Dragged {}", getMultiColumnListView().getDraggedItems());
      for (int i = 0; i < getMultiColumnListView().getColumns().size(); i++) {
        for (int j = 0; j < getMultiColumnListView().getColumns().get(i).getItems().size(); j++) {
          if (getMultiColumnListView().getColumns().get(i).getItems().get(j).equals(item)) {
            log.debug("Item found at index {}", i);
            return Optional.of(i);
          }
        }
      }
      return Optional.empty();
    }
  }
}
