package com.example.todolist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class interface_connexion {

    @FXML
    private TextField textenom;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button submit;
    @FXML
    private Button signin;
    static String URL = "jdbc:mysql://localhost:3306/todolist_bd?useSSl=false";
    static  String USER = "root";
    static String password = "inconnu_X2027";


    public void creationCompte(ActionEvent actionEvent) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interface_creation.fxml"));

            Stage secondStage = new Stage();
            secondStage.setTitle("Creation de compte");
            secondStage.setScene(new Scene(loader.load()));
            secondStage.setWidth(800);
            secondStage.setHeight(500);
            secondStage.show();
        }catch (Exception e){
            System.out.println(e);
        }
        Stage stage = (Stage) signin.getScene().getWindow();
        stage.close();

    }

    public void connexionCompte(ActionEvent actionEvent) {
        String name = textenom.getText();
        String motdepasse = passwordField.getText();

        String sql = "SELECT nom, motdepasse FROM utilisateur";
        String nameV = null;
        String motdepasseV = null;
        try {
            Connection conn = DriverManager.getConnection(URL, USER, password);
            Statement stmt = conn.createStatement();
            //PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet res = stmt.executeQuery(sql);
            //statement.setInt(1, idV);
            int i = 0;
            int i1 = 0;
            while (res.next()) {
                i = 0;
                i1 = 0;
                i++;
                nameV = res.getString("nom");
                motdepasseV = res.getString("motdepasse");
                if (name == nameV && motdepasseV == motdepasse) {
                    break;
                }else
                    i1++;
            }
            if (i == i1){
                System.out.println("pas bon");
            }
            conn.close();
            System.out.println("requête réussi");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
