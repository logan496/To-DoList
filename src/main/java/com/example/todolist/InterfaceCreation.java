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


public class InterfaceCreation {

    @FXML
    private Button Save;
    @FXML
    private Button Cancel;
    @FXML
    private Button showpassword;
    public PasswordField passwordField1;
    public PasswordField passwordField2;
    @FXML
    public TextField textname;
    static String URL = "jdbc:mysql://localhost:3306/todolist_bd?useSSl=false";
    static  String USER = "root";
    static String password = "inconnu_X2027";

    //save les infos utilisateurs dans la BD
    public void Save() {
        int id  = 2;
        String name = textname.getText();
        String password1 = passwordField1.getText();
        String requete = "INSERT INTO utilisateur(id, motdepasse, nom) VALUES(?, ?, ?)";
        try{
            Connection conn = DriverManager.getConnection(URL, USER, password);

            PreparedStatement statement = conn.prepareStatement(requete);

            statement.setInt(1, id);
            statement.setString(2, password1);
            statement.setString(3, name);

            int lignesAffectees = statement.executeUpdate();
            if(lignesAffectees > 0){
                System.out.println("Requête réussie");
            }else {
                System.out.println("Echec");
            }
        }catch (SQLException e){
            System.out.println("Erreur:"+ e.getMessage());
        }
    }

    public void Cancel(ActionEvent actionEvent) {
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interface_connexion.fxml"));

            Stage secondStage = new Stage();
            secondStage.setTitle("ToDoList");
            secondStage.setScene(new Scene(loader.load()));
            secondStage.setWidth(800);
            secondStage.setHeight(500);
            secondStage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //fonction pour afficher le mot de passe avec bouton showpassword
    @FXML
    public void showPassword() {
        if(passwordField1.isVisible())
            passwordField1.setVisible(false);
        else
            passwordField1.setVisible(true);

    }
}
