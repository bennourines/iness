package controllers;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//



import  models.Reclamation;
import services.ReclamationService;
;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Backr implements Initializable {
    @FXML
    private TableView<Reclamation> tftableview;
    @FXML
    private TableColumn<?, ?> tfobjet;
    @FXML
    private TableColumn<?, ?> tfdescription;
    @FXML
    private TableColumn<?, ?> tftype;
    @FXML
    private Button tfback;
    @FXML
    private TableColumn<?, ?> tfdate;
    @FXML
    private TableColumn<?, ?> tfetat;
    @FXML
    private Button tftraite;
    public static final String ACCOUNT_SID = "TWILIO_ACCOUNT_SID";
    public static final String AUTH_TOKEN = "TWILIO_AUTH_TOKEN";

    public Backr() {
    }

    public void initialize(URL url, ResourceBundle rb) {
        ReclamationService service = new ReclamationService();
        ObservableList<Reclamation> list = service.getall();
        this.tfobjet.setCellValueFactory(new PropertyValueFactory("Objet"));
        this.tfdescription.setCellValueFactory(new PropertyValueFactory("DescriptionR"));
        this.tftype.setCellValueFactory(new PropertyValueFactory("TypeR"));
        this.tfdate.setCellValueFactory(new PropertyValueFactory("DateR"));
        this.tfetat.setCellValueFactory(new PropertyValueFactory("etat"));
        this.tftableview.setItems(list);
    }

    @FXML
    private void back(ActionEvent event) {
    }

    @FXML
    private void treat(ActionEvent event) {
        ReclamationService service = new ReclamationService();
        Reclamation r = (Reclamation)this.tftableview.getSelectionModel().getSelectedItem();
        service.traiter(r);
        String UN = ".com";
        String PW = "@";
        String mto = service.OneUser(r.getIdc()).getEmail();
        String msub = "Nouvelle Reclamation";
        String cTEXT = "Cher Client , Votre Reclamation a ete bien traiter ";
        this.refresh();
    }

    public void refresh() {
        ReclamationService service = new ReclamationService();
        ObservableList<Reclamation> list = service.getall();
        this.tfobjet.setCellValueFactory(new PropertyValueFactory("Objet"));
        this.tfdescription.setCellValueFactory(new PropertyValueFactory("DescriptionR"));
        this.tftype.setCellValueFactory(new PropertyValueFactory("TypeR"));
        this.tfdate.setCellValueFactory(new PropertyValueFactory("DateR"));
        this.tfetat.setCellValueFactory(new PropertyValueFactory("etat"));
        this.tftableview.setItems(list);
    }
}
