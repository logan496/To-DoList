package com.example.todolist;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class InterfacePrincipale {

    @FXML
    public Button Addnew;
    @FXML
    public Button All;
    @FXML
    public Button Todo;
    @FXML
    public Button completed;

    public void ajoutertaches(ActionEvent actionEvent) {
        Stage stage = (Stage) Addnew.getScene().getWindow();
        stage.close();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Ajout_tache.FXML"));
            Scene scene = new Scene(loader.load());
            Stage secondstage =  new Stage();
            secondstage.setScene(scene);
            secondstage.setTitle("To-Do List");
            secondstage.show();
        }catch (Exception e){
            Alert echec_ajout = new Alert(Alert.AlertType.ERROR);
            echec_ajout.setHeaderText("System Error");
            echec_ajout.setContentText("Erreur de chargement de l'interface d'ajout");
            echec_ajout.showAndWait();
        }
    }

    public void toutafficher(ActionEvent actionEvent) {
    }

    public void afficherTodo(ActionEvent actionEvent) {
    }

    public void affichertachescompletees(ActionEvent actionEvent) {
    }
}
