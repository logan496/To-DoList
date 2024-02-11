package com.example.todolist;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.*;
public class AjoutTache {
    @FXML
    private TextField textnom;
    @FXML
    private TextField textdescription;
    @FXML
    private TextField category;
    @FXML
    private DatePicker ges_date;
    @FXML
    private Label valueLabel;
    @FXML
    public Button cancel;
    @FXML
    public Button save;
    @FXML
    public ComboBox comboBox;
    @FXML
    public Text addnew;
    @FXML
    public Slider slider;
    @FXML
    private Spinner<Integer> spinnerHours;
    static String URL = "jdbc:mysql://localhost:3306/todolist_bd?useSSl=false";
    static  String USER = "root";
    static String password = "inconnu_X2027";

    public void initialize() {
        spinnerHours.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        spinnerHours.getValueFactory().setConverter(new StringConverter<Integer>() {
            @Override
            public String toString(Integer value) {
                return String.format("%02d", value);
            }

            @Override
            public Integer fromString(String s) {
                return Integer.parseInt(s);
            }
        });

    }
    public void slidervalue(){
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setBlockIncrement(10);
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                valueLabel.setText(String.format("%.2f", newValue));
                double value = slider.getValue();
            }
        });
    }
    public void savetask(ActionEvent actionEvent) {
        String sql = "INSERT INTO infos_taches(name, fullfillment, description, category, date, TIME, priority) VALUES" +
                "(?, ?, ?, ?, ?, ?, ?";
        try{
            Connection conn = DriverManager.getConnection(URL, USER, password);
            PreparedStatement stmt = conn.prepareStatement(sql);
            //ajout des variables à sauvegarder dans la bd
            stmt.executeUpdate();
        }catch (SQLException e){
            Alert echec_enregistrement = new Alert(Alert.AlertType.ERROR);
            echec_enregistrement.setHeaderText("System Error");
            echec_enregistrement.setContentText("échec de l'enregistrement des donées");
            echec_enregistrement.showAndWait();
        }
    }

    public void canceladd(ActionEvent actionEvent) {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interface_principale.FXML"));
            Scene scene = new Scene(loader.load());
            Stage secondstage = new Stage();
            secondstage.setScene(scene);
            secondstage.setTitle("To-Do List");
            secondstage.show();
        }catch (Exception e){
            Alert erreur_chargement = new Alert(Alert.AlertType.ERROR);
            erreur_chargement.setHeaderText("System Error");
            erreur_chargement.setContentText(e.getMessage());
            erreur_chargement.showAndWait();
        }

    }


}
