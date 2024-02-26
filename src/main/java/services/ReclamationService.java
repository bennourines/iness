package services;

import java.util.ArrayList;
import java.util.List;
import models.Reclamation;
import  models.User;
import utils.MyDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.SessionManager;

public class ReclamationService {
        Connection cnx = MyDatabase.getInstance().getConnection();

        public ReclamationService() {
        }

        /*public void create(Reclamation t) {
            try {
                String req = "insert into reclamation(id_user,object,type,description,etat,date) values( " + t.getIdc() + ",' " + t.getObjet() + "','" + t.getTypeR() + "','" + t.getDescriptionR() + "','Non traité', NOW());";
                Statement st = this.cnx.createStatement();
                st.executeUpdate(req);
                System.out.println("Reclamation ajoutée");
                System.out.println(t.getDateR());
            } catch (SQLException var4) {
                System.out.println(var4.getMessage());
            }

        }
*/
        public void create(Reclamation t) {
            try {
                String req = "INSERT INTO reclamation (id_user, object, type, description, etat, date) VALUES (?, ?, ?, ?, ?, NOW())";
                PreparedStatement ps = this.cnx.prepareStatement(req);
                ps.setInt(1, 5); // Setting id_user to 5
                ps.setString(2, t.getObjet());
                ps.setString(3, t.getTypeR());
                ps.setString(4, t.getDescriptionR());
                ps.setString(5, "Non traité");

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Reclamation ajoutée");
                } else {
                    System.out.println("Failed to add Reclamation");
                }
            } catch (SQLException e) {
                System.out.println("Error adding Reclamation: " + e.getMessage());
            }
        }
        public void updateR(Reclamation t) {
            try {
                String req = "update reclamation set object=?,type=?,description=? where id_rec= ?";
                PreparedStatement ps = this.cnx.prepareStatement(req);
                ps.setString(1, t.getObjet());
                ps.setString(2, t.getTypeR());
                ps.setString(3, t.getDescriptionR());
                ps.setInt(4, (int)t.getId_rec());
                ps.executeUpdate();
                System.out.println("Reclamation modifiée");
            } catch (SQLException var4) {
                System.out.println(var4.getMessage());
            }

        }

        public void deleteR(Reclamation reclamation) {
            try {
                Statement st = this.cnx.createStatement();
                int id = reclamation.getId_rec(); // Assuming getId() returns the ID of the reclamation
                String req = "DELETE FROM reclamation WHERE id_rec = " + id;
                st.executeUpdate(req);
                System.out.println("Reclamation supprimer avec succès...");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public List<Reclamation> recuperer() {
            List<Reclamation> reclamations = new ArrayList();

            try {
                String req = "select * from reclamation order by date DESC";
                Statement st = this.cnx.createStatement();
                ResultSet rs = st.executeQuery(req);

                while(rs.next()) {
                    Reclamation p = new Reclamation();
                    p.setId_rec(rs.getInt(1));
                    p.setObjet(rs.getString("object"));
                    p.setTypeR(rs.getString("type"));
                    p.setDescriptionR(rs.getString("description"));
                    p.setDateR(rs.getString("date"));
                    reclamations.add(p);
                }
            } catch (SQLException var6) {
                System.out.println(var6.getMessage());
            }

            return reclamations;
        }

        public List<Reclamation> recupererUser(int idu) {
            List<Reclamation> reclamations = new ArrayList();
             idu=5;
            try {
                String req = "select * from reclamation where id_user='" + idu + "' order by date DESC";
                Statement st = this.cnx.createStatement();
                ResultSet rs = st.executeQuery(req);

                while(rs.next()) {
                    Reclamation p = new Reclamation();
                    p.setId_rec(rs.getInt(1));
                    p.setObjet(rs.getString("object"));
                    p.setTypeR(rs.getString("type"));
                    p.setDescriptionR(rs.getString("description"));
                    p.setDateR(rs.getString("date"));
                    reclamations.add(p);
                }
            } catch (SQLException var7) {
                System.out.println(var7.getMessage());
            }

            return reclamations;
        }

        public ObservableList<Reclamation> getall() {
            ObservableList<Reclamation> reclamations = FXCollections.observableArrayList();

            try {
                String req = "select * from reclamation";
                Statement st = this.cnx.createStatement();
                ResultSet rs = st.executeQuery(req);

                while(rs.next()) {
                    Reclamation p = new Reclamation();
                    p.setId_rec(rs.getInt(1));
                    p.setObjet(rs.getString("object"));
                    p.setTypeR(rs.getString("type"));
                    p.setDescriptionR(rs.getString("description"));
                    p.setDateR(rs.getString("date"));
                    p.setEtat(rs.getString("etat"));
                    reclamations.add(p);
                }
            } catch (SQLException var6) {
                System.out.println(var6.getMessage());
            }

            return reclamations;
        }

        public void traiter(Reclamation t) {
            try {
                String req = "update reclamation set etat=? where id_rec= ?";
                PreparedStatement ps = this.cnx.prepareStatement(req);
                ps.setString(1, "traité");
                ps.setInt(2, (int)t.getId_rec());
                ps.executeUpdate();
                System.out.println("Reclamation modifiée");
            } catch (SQLException var4) {
                System.out.println(var4.getMessage());
            }

        }

        public User OneUser(int idu) {
            User u = null;
            String req = "SELECT * FROM user WHERE id_user=?";

            try (PreparedStatement st = this.cnx.prepareStatement(req)) {
                // Set the parameter safely
                st.setInt(1, idu);

                try (ResultSet rs = st.executeQuery()) {
                    if (rs.next()) {
                        u = new User();
                        u.setId(idu);
                        u.setCIN(rs.getInt("cin"));
                        u.setAdresse(rs.getString("adress"));
                        u.setEmail(rs.getString("email"));
                        u.setPhone(rs.getInt("phone"));
                        u.setRole(rs.getString("role"));
                        u.setUserName(rs.getString("user_name"));
                        System.out.println(u);
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error fetching user: " + e.getMessage());
            }

            return u;
        }
    }


