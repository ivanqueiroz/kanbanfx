package dev.ivanqueiroz.kanbanfx.view.controller;

import com.dlsc.gemsfx.MultiColumnListView;
import com.dlsc.gemsfx.MultiColumnListView.ColumnListCell;
import com.dlsc.gemsfx.MultiColumnListView.ListViewColumn;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.BoardJavaFxAdapter;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.BoardQueryResponse;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.TaskQueryResponse;
import dev.ivanqueiroz.kanbanfx.infrastructure.adapters.input.javafx.data.TaskStatusQueryResponse;
import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.rgielen.fxweaver.core.FxmlView;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@FxmlView
@RequiredArgsConstructor
@Slf4j
public class MainWindow {

  @FXML private MenuItem quitMenuItem;
  @FXML private BorderPane borderPane;
  private final BoardJavaFxAdapter boardJavaFxAdapter;

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
        TaskQueryResponse.builder().title("").description("").build());
    multiColumnListView.setPlaceholderTo(
        TaskQueryResponse.builder().title("").description("").build());
    VBox.setVgrow(multiColumnListView, Priority.ALWAYS);

    borderPane.setCenter(createBoardColumnsVBox(multiColumnListView));
  }

  @NotNull
  private static VBox createBoardColumnsVBox(MultiColumnListView<TaskQueryResponse> multiColumnListView) {
    var optionsBox = createBoardColumnsOptionBoxActions(multiColumnListView);
    var vbox = new VBox(10, multiColumnListView, optionsBox);
    vbox.setAlignment(Pos.TOP_RIGHT);
    vbox.setPadding(new Insets(20));
    return vbox;
  }

  @NotNull
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

  @NotNull
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
              result.setHeader(new Label(columnQueryResponse.getName()));
              result.getItems().setAll(columnQueryResponse.getTasks());
              return result;
            })
        .toList();
  }

  private void initializeMenu() {
    quitMenuItem.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
    quitMenuItem.setOnAction(e -> Platform.exit());
  }

  private static class TaskListCell extends ColumnListCell<TaskQueryResponse> {
    private final BooleanProperty placeholder =
        new SimpleBooleanProperty(this, "placeholder", false);

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

      var label = new Label();
      label.textProperty().bind(textProperty());

      var wrapper = new StackPane(content, contentPlaceholder, label);
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
          getStyleClass().add(item.getStatus().getDescription());
          getTaskColumnIndex()
              .ifPresent(columnIndex -> updateTaskStatus(item, columnIndex, getStyleClass()));
        }
      } else {
        setText("");
      }
    }

    private static void updateTaskStatus(
        TaskQueryResponse item, Integer columnIndex, ObservableList<String> styleClass) {
      item.setStatus(TaskStatusQueryResponse.values()[columnIndex]);
      styleClass.add(item.getStatus().getDescription());
    }

    private Optional<Integer> getTaskColumnIndex() {
      for (int i = 0; i < getMultiColumnListView().getColumns().size(); i++) {
        for (int j = 0; j < getMultiColumnListView().getColumns().get(i).getItems().size(); j++) {
          if (getMultiColumnListView()
              .getColumns()
              .get(i)
              .getItems()
              .get(j)
              .equals(
                  getMultiColumnListView().getDraggedItems().stream()
                      .findFirst()
                      .orElse(new TaskQueryResponse()))) {
            return Optional.of(i);
          }
        }
      }
      return Optional.empty();
    }
  }
}
