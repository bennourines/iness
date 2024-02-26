package controllers;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import models.User;
import services.UserService;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class Modify implements Initializable {
    @FXML
    private TextField cinM;
    @FXML
    private TextField idUser;
    @FXML
    private TextField usernameM;
    @FXML
    private Button modifier_btn;
    @FXML
    private TextField numeroM;
    @FXML
    private TextField adresseM;
    @FXML
    private TextField passwordM;
    @FXML
    private TextField email_signupM;

    public Modify() {
    }

    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void textfieldDesign1(MouseEvent event) {
    }

    @FXML
    private void textfieldDesign1(KeyEvent event) {
    }

    @FXML
    void ModifierU(ActionEvent event) {
        UserService userService = new UserService(); // Assuming UserService is your service class
        try {
            Integer id = Integer.parseInt(this.idUser.getText());
            Integer cin = Integer.parseInt(this.cinM.getText());
            Integer num = Integer.parseInt(this.numeroM.getText());
            String username = this.usernameM.getText();
            String adresse = this.adresseM.getText();

            String email = this.email_signupM.getText();

            User user = new User(id, cin, username, num, adresse, email);

            userService.update(user);

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Travel Me :: Success Message");
            alert.setHeaderText(null);
            alert.setContentText("Utilisateur modifié");
            alert.showAndWait();
        } catch (NumberFormatException e) {
            // Handle parsing errors
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Travel Me :: Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir des valeurs numériques valides pour l'ID, le CIN et le numéro.");
            alert.showAndWait();
        } catch (SQLException e) {
            // Handle SQL errors
            e.printStackTrace(); // For debugging purposes
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Travel Me :: Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Une erreur s'est produite lors de la modification de l'utilisateur.");
            alert.showAndWait();
        }
    }

    public void setText(User user) {
        String id = String.valueOf(user.getId());
        this.cinM.setText(id);
        String cin = String.valueOf(user.getCIN());
        this.cinM.setText(cin);
        this.usernameM.setText(user.getUserName());
        this.adresseM.setText(user.getAdresse());
        this.passwordM.setText(user.getPassword());
        String num = String.valueOf(user.getPhone());
        this.numeroM.setText(num);
        this.email_signupM.setText(user.getEmail());
    }

    public void setTextFields(User R) {
        this.idUser.setText(String.valueOf(R.getId()));
        this.cinM.setText(String.valueOf(R.getCIN()));
        this.usernameM.setText(R.getUserName());
        this.numeroM.setText(String.valueOf(R.getPhone()));
        this.adresseM.setText(R.getAdresse());
        this.email_signupM.setText(R.getEmail());
    }
}
