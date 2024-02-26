package controllers;
import services.UserService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import models.User;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class View implements Initializable {

    @FXML
    private TableView<User> usersTable;
    @FXML
    private TableColumn<User, String> lastnameCol;
    @FXML
    private TableColumn<User, String> firstnameCol;
    @FXML
    private TableColumn<User, String> roleCol;
    @FXML
    private TableColumn<User, String> addressCol;
    @FXML
    private TableColumn<User, String> emailCol;
    @FXML
    private TableColumn<User, Void> editCol;

    private final UserService userService = new UserService();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTableDataFromEvent(null);
        setupEditColumn();
    }

    private void loadTableDataFromEvent(ActionEvent event) {
        loadTableData(event);
    }

    @FXML
    private void loadTableData(ActionEvent event) {
        try {
            ObservableList<User> userList = FXCollections.observableArrayList(userService.read());
            usersTable.setItems(userList);

            lastnameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            firstnameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        } catch (SQLException e) {
            showErrorAlert("Error loading users", e.getMessage());
        }
    }

    private void setupEditColumn() {
        Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<User, Void> call(final TableColumn<User, Void> param) {
                final TableCell<User, Void> cell = new TableCell<>() {
                    private final HBox actionsBox = new HBox();

                    {
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
                        editIcon.setStyle("-fx-cursor: hand; -glyph-size: 18px; -fx-fill: #00E676;");
                        editIcon.setOnMouseClicked(event -> {
                            User user = getTableView().getItems().get(getIndex());
                            handleEditUser(user);
                        });

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        deleteIcon.setStyle("-fx-cursor: hand; -glyph-size: 18px; -fx-fill: #ff1744;");
                        deleteIcon.setOnMouseClicked(event -> {
                            User user = getTableView().getItems().get(getIndex());
                            handleDeleteUser(user);
                        });
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                        + "-glyph-size:28px;"
                                        + "-fx-fill:#00E676;"
                        );
                        actionsBox.getChildren().addAll(editIcon, deleteIcon);
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(actionsBox);
                        }
                    }
                };
                return cell;
            }
        };

        editCol.setCellFactory(cellFactory);
    }

    private void handleEditUser(User user) {
        // Implement edit user functionality
        // For example, open a new window with user details for editing
    }

    private void handleDeleteUser(User user) {
      /*  try {
            userService.delete(user.getId());
            loadTableData(null);
        } catch (SQLException e) {
            showErrorAlert("Error deleting user", e.getMessage());
        }*/
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


    @FXML
    private void refresh() {
        try {
            ObservableList<User> userList = FXCollections.observableArrayList(userService.read());
            usersTable.setItems(userList);

            lastnameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            firstnameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            roleCol.setCellValueFactory(new PropertyValueFactory<>("role"));
            addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
            emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        } catch (SQLException e) {
            showErrorAlert("Error refreshing users", e.getMessage());
        }
    }

    @FXML
    private void openAddView(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Add.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded FXML file
            Scene scene = new Scene(root);

            // Create a new stage to show the scene
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Add New User"); // Set the title of the stage
            stage.show(); // Show the stage
        } catch (IOException e) {
            e.printStackTrace();
            showErrorAlert("Error", "Failed to open the add view");
        }
    }


}
