package controllers;
import utils.MyDatabase;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.User;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import services.UserService;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
public class Admin implements Initializable {

    @FXML
    private TableView<User> tableviewUser;
    @FXML
    private TableColumn<?, ?> idUser;
    @FXML
    private TableColumn<?, ?> CinUser;
    @FXML
    private TableColumn<?, ?> Username;
    @FXML
    private TableColumn<?, ?> phoneUser;
    @FXML
    private TableColumn<?, ?> EmailUser;
    @FXML
    private TableColumn<?, ?> AdresseUser;
    @FXML
    private TextField Recherche_User;
    @FXML
    private TableColumn<User, Void> editCol;

    private UserService userService;
    User user = null;
    private Connection cnx;
    @FXML
    public void exit() {
        System.exit(0);
    }

    public void initialize(URL url, ResourceBundle rb) {
        this.userService = new UserService();
        this.showRec();
        this.setupEditColumn();
        this.searchRec();
    }

    public ObservableList<User> getUserList() {
        try {
            return FXCollections.observableArrayList(this.userService.getAllUsers());
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return FXCollections.observableArrayList(); // Return an empty list or handle the error
        }
    }
    public void showRec() {
        ObservableList<User> list = this.getUserList();
        tableviewUser.setItems(list);
        this.idUser.setCellValueFactory(new PropertyValueFactory("id"));
        this.CinUser.setCellValueFactory(new PropertyValueFactory<>("CIN"));
        this.Username.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        this.phoneUser.setCellValueFactory(new PropertyValueFactory<>("phone"));
        this.EmailUser.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.AdresseUser.setCellValueFactory(new PropertyValueFactory<>("Adress"));
    }

    private void refresh() {
        tableviewUser.setItems(getUserList());
    }

    @FXML
    private void SupprimerUser(ActionEvent event) {
        User user = tableviewUser.getSelectionModel().getSelectedItem();
        if (user != null) {
            try {
                userService.delete(user);
                refresh();
                showAlert(Alert.AlertType.INFORMATION, "Travel Me :: Information Message", "Utilisateur supprimé");
            } catch (SQLException e) {
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Travel Me :: Error Message", "Une erreur s'est produite lors de la suppression de l'utilisateur");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "Travel Me :: Warning", "Veuillez sélectionner un utilisateur à supprimer.");
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
        this.user = (User)this.tableviewUser.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/Modify.fxml"));

        try {
            loader.load();
        } catch (Exception var6) {
            System.err.println(var6.getMessage());
        }

        Modify muc = (Modify)loader.getController();
        muc.setTextFields(this.user);
        Parent parent = (Parent)loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
        this.showRec();
    }

    private void handleDeleteUser(User user) {
        // Implement delete user functionality
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete User");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this user?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                this.userService.delete(user);
                refresh(); // Refresh the table view after deletion
                // Show success message
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("User Deleted");
                successAlert.setHeaderText(null);
                successAlert.setContentText("The user has been deleted successfully.");
                successAlert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
                // Show error message
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Error");
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("An error occurred while deleting the user.");
                errorAlert.showAndWait();
            }
        }

    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



    @FXML
    private void pdf_user(ActionEvent event) {
        System.out.println("hello");

        try {
            JasperDesign jDesign = JRXmlLoader.load("C:\\Users\\user\\Desktop\\User\\src\\main\\resources\\report.jrxml");
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            JasperPrint jPrint = JasperFillManager.fillReport(jReport, (Map)null, MyDatabase.getInstance().getConnection());
            JasperViewer viewer = new JasperViewer(jPrint, false);
            viewer.setTitle("Liste des Utilistaeurs");
            viewer.show();
            System.out.println("hello");
        } catch (Exception var6) {
            System.out.println(var6.getMessage());
        }

    }

    @FXML
    private void ModifierUser(ActionEvent event) {
        this.user = (User)this.tableviewUser.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("/Modify.fxml"));

        try {
            loader.load();
        } catch (Exception var6) {
            System.err.println(var6.getMessage());
        }

        Modify muc = (Modify)loader.getController();
        muc.setTextFields(this.user);
        Parent parent = (Parent)loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
        this.showRec();
    }


    private void searchRec() {
        this.idUser.setCellValueFactory(new PropertyValueFactory("id"));
        this.CinUser.setCellValueFactory(new PropertyValueFactory("CIN"));
        this.Username.setCellValueFactory(new PropertyValueFactory("UserName"));
        this.phoneUser.setCellValueFactory(new PropertyValueFactory("numero"));
        this.EmailUser.setCellValueFactory(new PropertyValueFactory("email"));
        this.AdresseUser.setCellValueFactory(new PropertyValueFactory("adresse"));
        ObservableList<User> list = this.getUserList();
        this.tableviewUser.setItems(list);
        FilteredList<User> filteredData = new FilteredList(list, (b) -> {
            return true;
        });
        this.Recherche_User.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate((rec) -> {
                if (newValue != null && !newValue.isEmpty()) {
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (rec.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else {
                        return rec.getUserName().toLowerCase().indexOf(lowerCaseFilter) != -1;
                    }
                } else {
                    return true;
                }
            });
        });
        SortedList<User> sortedData = new SortedList(filteredData);
        sortedData.comparatorProperty().bind(this.tableviewUser.comparatorProperty());
        this.tableviewUser.setItems(sortedData);
    }

}
