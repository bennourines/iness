//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.User;
import services.UserService;
import utils.MyDatabase;
import utils.SessionManager;
import javafx.scene.control.Alert;
//import javax.mail.*;
//import javax.mail.Message.RecipientType;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignIn implements Initializable {
    @FXML
    private Label TravelMe;
    @FXML
    private TextField email_signin;
    @FXML
    private PasswordField password_signin;
    @FXML
    private Button login_btn;
    @FXML
    private Hyperlink create_acc;
    @FXML
    private Label TravelMe2;
    @FXML
    private TextField cin;
    @FXML
    private TextField username;
    @FXML
    private Button signup_btn;
    @FXML
    private Hyperlink login_acc;
    @FXML
    private AnchorPane signup_form;
    @FXML
    private AnchorPane login_form;
    @FXML
    private PasswordField confirm_password;
    @FXML
    private TextField numero;
    @FXML
    private PasswordField password_signup;
    @FXML
    private TextField adresse;
    @FXML
    private TextField email_signup;
    @FXML
    private Hyperlink mdp_oub;
    private Connection cnx;
    private Statement statement;
    private PreparedStatement prepare;
    private ResultSet result;
    private UserService userService = new UserService();
    public SignIn() {
    }

    public void exit() {
        System.exit(0);
    }

    public void textfieldDesign() {
        if (this.email_signin.isFocused()) {
            this.email_signin.setStyle("-fx-background-color:#fff;-fx-border-width:2px");
            this.password_signin.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
        } else if (this.password_signin.isFocused()) {
            this.email_signin.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.password_signin.setStyle("-fx-background-color:#fff;-fx-border-width:2px");
        }

    }

    public void textfieldDesign1() {
        if (this.email_signup.isFocused()) {
            this.email_signup.setStyle("-fx-background-color:#fff;-fx-border-width:2px");
            this.password_signup.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.cin.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.username.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.adresse.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.numero.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.confirm_password.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
        } else if (this.password_signup.isFocused()) {
            this.email_signup.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.password_signup.setStyle("-fx-background-color:#fff;-fx-border-width:2px");
            this.cin.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.username.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.adresse.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.numero.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.confirm_password.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
        } else if (this.cin.isFocused()) {
            this.email_signup.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.password_signup.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.cin.setStyle("-fx-background-color:#fff;-fx-border-width:2px");
            this.username.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.adresse.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.numero.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.confirm_password.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
        } else if (this.username.isFocused()) {
            this.email_signup.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.password_signup.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.cin.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.username.setStyle("-fx-background-color:#fff;-fx-border-width:2px");
            this.adresse.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.numero.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.confirm_password.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
        } else if (this.adresse.isFocused()) {
            this.email_signup.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.password_signup.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.cin.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.username.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.adresse.setStyle("-fx-background-color:#fff;-fx-border-width:2px");
            this.numero.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.confirm_password.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
        } else if (this.numero.isFocused()) {
            this.email_signup.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.password_signup.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.cin.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.username.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.adresse.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.numero.setStyle("-fx-background-color:#fff;-fx-border-width:2px");
            this.confirm_password.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
        } else if (this.confirm_password.isFocused()) {
            this.email_signup.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.password_signup.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.cin.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.username.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.adresse.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.numero.setStyle("-fx-background-color:transparent;-fx-border-width:1px");
            this.confirm_password.setStyle("-fx-background-color:#fff;-fx-border-width:2px");
        }

    }

    public void dropShadowEffect() {
        DropShadow original = new DropShadow(20.0, Color.valueOf("#ae44a5"));
        original.setRadius(30.0);
        this.TravelMe.setEffect(original);
        this.TravelMe2.setEffect(original);
        this.TravelMe.setOnMouseEntered((event) -> {
            DropShadow shadow = new DropShadow(60.0, Color.valueOf("#ae44a5"));
            this.TravelMe.setCursor(Cursor.HAND);
            this.TravelMe.setStyle("-fx-text-fill:#517ab5");
            this.TravelMe.setEffect(shadow);
        });
        this.TravelMe.setOnMouseExited((event) -> {
            DropShadow shadow = new DropShadow(20.0, Color.valueOf("#ae44a5"));
            shadow.setRadius(30.0);
            this.TravelMe.setStyle("-fx-text-fill:#000");
            this.TravelMe.setEffect(shadow);
        });
        this.TravelMe2.setOnMouseEntered((event) -> {
            DropShadow shadow = new DropShadow(60.0, Color.valueOf("#ae44a5"));
            this.TravelMe2.setCursor(Cursor.HAND);
            this.TravelMe2.setStyle("-fx-text-fill:#517ab5");
            this.TravelMe2.setEffect(shadow);
        });
        this.TravelMe2.setOnMouseExited((event) -> {
            DropShadow shadow = new DropShadow(20.0, Color.valueOf("#ae44a5"));
            shadow.setRadius(30.0);
            this.TravelMe2.setStyle("-fx-text-fill:#000");
            this.TravelMe2.setEffect(shadow);
        });
    }

    public void changeForm(ActionEvent event) {
        if (event.getSource() == this.create_acc) {
            this.signup_form.setVisible(true);
            this.login_form.setVisible(false);
        } else if (event.getSource() == this.login_acc) {
            this.login_form.setVisible(true);
            this.signup_form.setVisible(false);
        }

    }

    public boolean ValidationEmail() {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9._]+([.][a-zA-Z0-9]+)+");
        Matcher match = pattern.matcher(this.email_signup.getText());
        if (match.find() && match.group().equals(this.email_signup.getText())) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Errore message");
            alert.setHeaderText((String)null);
            alert.setContentText("Invalid Email");
            alert.showAndWait();
            return false;
        }
    }

    public void login() {
        try {
            User user = userService.authentifier(email_signin.getText(), password_signin.getText());

            if (user != null) {
                System.out.println(user);

                String fxmlPath = null;
                switch(user.getRole()) {
                    case "admin":
                        fxmlPath = "/Admin.fxml";
                        break;
                    case "user":
                        fxmlPath = "/ShowReclamation.fxml";
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
                Stage currentStage = (Stage) email_signin.getScene().getWindow();
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




    private void showAlert(AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }


    @FXML
    public void signUp() {
        this.cnx = MyDatabase.getInstance().getConnection();
        String query = "INSERT INTO user (cin, user_name, phone, email, adress, password, role,image,points)VALUES (?, ?, ?, ?, ?, ?,\"user\",\"user\",0)";
        String defaultRole = "user"; // Set the default role here
        try {
            Alert alert;
            if (this.username.getText().isEmpty() | this.numero.getText().isEmpty() | this.email_signup.getText().isEmpty() | this.password_signup.getText().isEmpty() | this.adresse.getText().isEmpty() | this.cin.getText().isEmpty() | this.confirm_password.getText().isEmpty()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Travel Me :: Error Message");
                alert.setHeaderText((String)null);
                alert.setContentText("Entrer all blank fields !!");
                alert.showAndWait();
            } else if (this.confirm_password.getText().length() < 8 | this.confirm_password.getText() == this.password_signup.getText()) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Travel Me :: Error Message");
                alert.setHeaderText((String)null);
                alert.setContentText("Password doit etre sup 8 caractéres !!");
                alert.showAndWait();
            } else if (this.ValidationEmail()) {
                PreparedStatement smt = this.cnx.prepareStatement(query);
                smt.setString(1, this.cin.getText());
                smt.setString(2, this.username.getText());
                smt.setString(3, this.numero.getText());
                smt.setString(4, this.email_signup.getText());
                smt.setString(5, this.adresse.getText());
                smt.setString(6, this.password_signup.getText());
                smt.executeUpdate();
                System.out.println("ajout avec succee");
                Alert alertt = new Alert(AlertType.INFORMATION);
                alertt.setTitle("Travel Me :: BIENVENNUE");
                alertt.setHeaderText((String)null);
                alertt.setContentText("Vous Etes Inscrit !!");
                alertt.showAndWait();
                this.login_form.setVisible(true);
                this.signup_form.setVisible(false);
            }
        } catch (SQLException var4) {
            System.out.println(var4.getMessage());
        }

    }

    void sendPassword() {
        System.out.println("cxcccccccccccccccccc");
        String query2 = "select * from user where email=? ";
        String email1 = "empty";

        try {
            PreparedStatement smt = this.cnx.prepareStatement(query2);
            smt.setString(1, this.email_signin.getText());
            ResultSet rs = smt.executeQuery();
            if (rs.next()) {
                email1 = rs.getString("email");
                System.out.println(email1);
            }
        } catch (Exception var5) {
            System.out.println(var5.getMessage());
        }

        //this.sendMail(email1);
    }
/*
    public void sendMail(String recepient) {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        final String myAccountEmail = "travelme3a29@gmail.com";
        final String passwordd = "Aymen2000abid@";
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, passwordd);
            }
        });
        Message message = this.preparedMessage(session, myAccountEmail, recepient);

        try {
            Transport.send(message);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("TravelMe :: Boite Mail");
            alert.setHeaderText((String)null);
            alert.setContentText("consulter votre boite mail !!");
            alert.showAndWait();
        } catch (Exception var8) {
            var8.printStackTrace();
        }

    }

    private Message preparedMessage(Session session, String myAccountEmail, String recepient) {
        String query2 = "select * from user where email=?";
        String userEmail = "null";
        String pass = "empty";

        try {
            PreparedStatement smt = this.cnx.prepareStatement(query2);
            smt.setString(1, this.email_signin.getText());
            ResultSet rs = smt.executeQuery();
            System.out.println(rs);
            if (rs.next()) {
                pass = rs.getString("password");
                userEmail = rs.getString("email");
            }
        } catch (Exception var12) {
            System.out.println(var12.getMessage());
        }

        System.out.print("c est en cours");
        String text = "Votre mot de pass est :" + pass + "";
        String object = "Recupération de mot de passe";
        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(RecipientType.TO, new InternetAddress(userEmail));
            message.setSubject(object);
            String htmlcode = "<h1> " + text + " </h1> <h2> <b> </b2> </h2> ";
            message.setContent(htmlcode, "text/html");
            System.out.println("Message envoyeer");
            System.out.println(pass);
            return message;
        } catch (MessagingException var11) {
            var11.printStackTrace();
            return null;
        }
    }
*/
    @FXML
    void sendPaswword_btn(ActionEvent event) {
        this.sendPassword();
    }

    public void initialize(URL url, ResourceBundle rb) {
        this.dropShadowEffect();
    }
}

