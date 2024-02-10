package com.example.todolist;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.sql.*;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public void erreur_username(){
        Alert alert_name =  new Alert(Alert.AlertType.ERROR);
        alert_name.setHeaderText("USER name error");
        alert_name.setContentText("your username should have at least 4 characters");
        alert_name.showAndWait();
    }
    public void erreur_password(){
        Alert alert_passw =  new Alert(Alert.AlertType.ERROR);
        alert_passw.setHeaderText("Error password format");
        alert_passw.setContentText("your password dont respect the format it should have at least " +
                "8 character one uper letter and one special character");
        alert_passw.showAndWait();
    }
    public void erreur_password2(){
        Alert different = new Alert(Alert.AlertType.ERROR);
        different.setHeaderText("Error password");
        different.setContentText("You should confirm your password");
        different.showAndWait();
    }

    public void verifications(){
        String characteres = "^(?=.*[A-Z])(?=.*[!@#$%^&_]).{8,}$";
        Pattern pattern = Pattern.compile(characteres);
        Matcher matcher = pattern.matcher(passwordField1.getText());

        String char_name = ".{4,}$";
        Pattern pattern1 = Pattern.compile(char_name);
        Matcher matcher1 = pattern1.matcher(textname.getText());

        if (!matcher1.matches() || !matcher.matches()) {
            if(!matcher1.matches()) {
                erreur_username();
                textname.clear();
            }
            if (!matcher.matches()) {
                erreur_password();
                passwordField1.clear();
            }
            if(!Objects.equals(passwordField1.getText(), passwordField2.getText())){
                erreur_password2();
                passwordField2.clear();
            }
        }else{
            Save(textname.getText(), passwordField1.getText());
        }
    }

    public void Save(String name, String password1) {
        String requete = "INSERT INTO utilisateur(password, nom) VALUES(?, ?)";
        try{
            Connection conn = DriverManager.getConnection(URL, USER, password);

            PreparedStatement statement = conn.prepareStatement(requete);

            statement.setString(1, password1);
            statement.setString(2, name);

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

    //bouton cancel pour revenir à l'interface de connexion
    public void Cancel() {
        Stage stage = (Stage) Cancel.getScene().getWindow();
        stage.close();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interface_connexion.fxml"));

            Stage secondStage = new Stage();
            secondStage.setTitle("To-Do List");
            secondStage.setScene(new Scene(loader.load()));
            secondStage.setWidth(800);
            secondStage.setHeight(500);
            secondStage.setResizable(false);
            secondStage.show();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    //fonction pour afficher le mot de passe avec bouton showpassword
    @FXML
    public void showPassword() {
        String password1 = passwordField1.getText();
        String password2 =  passwordField2.getText();

        passwordField1.setPromptText(password1);
        passwordField1.setText("");
        passwordField1.setDisable(true);

        passwordField2.setPromptText(password2);
        passwordField2.setText("");
        passwordField2.setDisable(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(actionEvent -> {
            passwordField1.setText(password1);
            passwordField1.setDisable(false);

            passwordField2.setText(password2);
            passwordField2.setDisable(false);
        });
        pause.play();
    }
}
