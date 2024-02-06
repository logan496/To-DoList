package com.example.todolist;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class MainTodo extends Application {


    private final int width = 800;
    private final int height = 500;
    //, 300, 275


    @Override
    public void start(Stage Primarystage) {
        try {
            FXMLLoader loader = new FXMLLoader(MainTodo.class.getResource("interface_connexion.fxml"));
            Scene scene = new Scene(loader.load());
            Primarystage.setScene(scene);
        } catch (Exception e) {
            System.out.println("Erreur:" + e.getMessage());
        }
        Primarystage.setWidth(width);
        Primarystage.setHeight(height);
        Primarystage.setTitle("TodoList");
        Primarystage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}

