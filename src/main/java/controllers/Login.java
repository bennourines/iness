
package controllers;
import javafx.event.ActionEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.User;
import services.UserService;
import javafx.fxml.FXML;
import java.io.IOException;
import java.sql.SQLException;

public class Login extends Application {
    private double x = 0.0;
    private double y = 0.0;

    private UserService userService = new UserService();

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SignIn.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            // Allow the window to be dragged
            root.setOnMousePressed((event) -> {
                this.x = event.getSceneX();
                this.y = event.getSceneY();
            });
            root.setOnMouseDragged((event) -> {
                stage.setX(event.getScreenX() - this.x);
                stage.setY(event.getScreenY() - this.y);
            });

            // Set stage properties
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("Error loading SignIn.fxml: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @FXML
    private TextField username_login;

    @FXML
    private PasswordField password_login;

    @FXML
    void login(ActionEvent event) {
        try {
            User user = userService.authentifier(username_login.getText(), password_login.getText());

            if (user != null) {
                System.out.println(user);

                String fxmlPath = null;
                switch(user.getRole()) {
                    case "admin":
                        fxmlPath = "/Admin.fxml";
                        break;
                    case "user":
                        fxmlPath = "/Add.fxml";
                        break;
                    default:
                        // Handle other roles or errors
                        return;
                }

                // Load FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                Parent root = loader.load();

                // Create a new stage
                Stage stage = new Stage();
                stage.setTitle("Your Title"); // Set your title here

                // Set the scene
                Scene scene = new Scene(root);
                stage.setScene(scene);

                // Close the current stage
                Stage currentStage = (Stage) username_login.getScene().getWindow();
                currentStage.close();

                // Show the new stage
                stage.show();
            } else {
                // Handle authentication failure
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Login Error");
                alert.setHeaderText(null);
                alert.setContentText("Invalid username or password");
                alert.showAndWait();
            }
        } catch (SQLException | IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
