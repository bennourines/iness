package controllers;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//




import models.Reclamation;
import services.ReclamationService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import utils.SessionManager;
//import javax.mail.Session;
import utils.SessionManager;
public class ShowReclamation implements Initializable {
    @FXML
    private Button Reclamer;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private TextArea description;
    @FXML
    private TextField objet;
    @FXML
    private VBox tfrec;
    @FXML
    private Button Modifier;
   // static Session sesh;
    static Properties prop = new Properties();

    public ShowReclamation() {
    }

    public void initData(Reclamation r) {
        this.type.getSelectionModel().select(r.getTypeR());
        this.description.setText(r.getDescriptionR());
        this.objet.setText(r.getObjet());
        System.out.println(r.getDescriptionR());
        this.Modifier.setOnMouseClicked((m) -> {
            ReclamationService serv = new ReclamationService();
            if (!r.getDescriptionR().equals("")) {
                r.setDescriptionR(this.description.getText());
                r.setObjet(this.objet.getText());
                r.setTypeR((String)this.type.getValue());
                serv.updateR(r);

                try {
                    Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("ShowReclamation.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage)((Node)m.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException var7) {
                    //Logger.getLogger(ShowPostController.class.getName()).log(Level.SEVERE, (String)null, var7);
                }
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("");
                alert.setContentText("Veuiller Selectionner La reclamation a modifier ");
                alert.showAndWait();
            }

        });
    }

    public void initialize(URL url, ResourceBundle rb) {
        this.type.getItems().addAll(new String[]{"Technic", "non technic"});
        this.type.getSelectionModel().selectFirst();
        this.Modifier.setOnMouseClicked((m) -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("");
            alert.setHeaderText("");
            alert.setContentText(" Selectionner La reclamation a modifier ");
            alert.showAndWait();
        });
        ReclamationService service = new ReclamationService();
        new ArrayList();
        List<Reclamation> list = service.recupererUser(SessionManager.getId());
        Iterator var5 = list.iterator();

        while(var5.hasNext()) {
            Reclamation e = (Reclamation)var5.next();
            HBox lc = new HBox();
            FontAwesomeIconView modif = new FontAwesomeIconView(FontAwesomeIcon.PENCIL);
            modif.setFill(Color.GREEN);
            modif.setGlyphSize(25);
            modif.setCursor(Cursor.HAND);
            lc.getChildren().add(modif);
            lc.setTranslateX(300.0);
            lc.setTranslateY(5.0);
            FontAwesomeIconView supp = new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
            supp.setFill(Color.RED);
            supp.setGlyphSize(25);
            supp.setCursor(Cursor.HAND);
            supp.setTranslateX(5.0);
            lc.getChildren().add(supp);
            HBox obj = new HBox();
            Label t = new Label("    Type        : " + e.getTypeR());
            Label desc = new Label("    Description : " + e.getDescriptionR());
            Label d = new Label("    Date        : " + e.getDateR());
            Label objet1 = new Label("    Objet       : " + e.getObjet());
            obj.getChildren().add(objet1);
            obj.getChildren().add(lc);
            supp.setOnMouseClicked((a) -> {
                service.deleteR(e);

                try {
                    Parent root = (Parent)FXMLLoader.load(this.getClass().getResource("ShowReclamation.fxml"));
                    Scene scene = new Scene(root);
                    Stage stage = (Stage)((Node)a.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException var7) {
                    //Logger.getLogger(ShowPostController.class.getName()).log(Level.SEVERE, (String)null, var7);
                }

            });
            modif.setOnMouseClicked((m) -> {
                try {
                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ShowReclamation.fxml"));
                    Parent root = (Parent)loader.load();
                    ShowReclamation controller = (ShowReclamation)loader.getController();
                    controller.initData(e);
                    System.out.println(loader.getController().toString());
                    Stage stage = (Stage)((Node)m.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException var7) {
                    //Logger.getLogger(ShowPostController.class.getName()).log(Level.SEVERE, (String)null, var7);
                }

            });
            Label type = new Label();
            Label objet = new Label();
            VBox rec = new VBox();
            rec.setBackground(new Background(new BackgroundFill[]{new BackgroundFill(Color.rgb(190, 210, 255), new CornerRadii(10.0), new Insets(5.0))}));
            rec.getChildren().add(objet);
            rec.getChildren().add(obj);
            rec.getChildren().add(d);
            rec.getChildren().add(t);
            rec.getChildren().add(desc);
            rec.getChildren().add(type);
            this.tfrec.getChildren().add(rec);
        }

        this.Reclamer.setOnMouseClicked((event) -> {
            ReclamationService serv = new ReclamationService();
            Reclamation p = new Reclamation();
            if (!this.description.getText().equals("") && !this.objet.getText().equals("")) {
                String UN = "bennourines@gmail.com";
                String PW = "bennourines@gmail.com";
                String mto = "bennourines@gmail.com";
                String msub = "Nouvelle Reclamation";
                String cTEXT = "Le Client , " + SessionManager.getUserName() + " add new reclamation with type ' " + (String)this.type.getValue() + "'";
                //MailerAPI.Mail(UN, PW, mto, msub, cTEXT);
                p.setIdc(SessionManager.getId());
                System.out.println(SessionManager.getId());
                p.setDescriptionR(this.description.getText());
                p.setObjet(this.objet.getText());
                p.setTypeR((String)this.type.getValue());
                serv.create(p);
                // Refresh the data
                refreshData();
            } else {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("");
                alert.setContentText("Verifier vos données ");
                alert.showAndWait();
            }
            Parent root = null;

            try {
                root = (Parent)FXMLLoader.load(this.getClass().getResource("ShowReclamation.fxml"));
            } catch (IOException var9) {
                Logger.getLogger(ShowReclamation.class.getName()).log(Level.SEVERE, (String)null, var9);
            }

            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        });
    }

    @FXML
    private void Redirect(ActionEvent event) {
    }

    @FXML
    private void refreshData() {
        // Clear the existing items in tfrec
        tfrec.getChildren().clear();

        // Reload the data by calling initialize method
        initialize(null, null);
    }

    // Your existing code...

}
