package com.example.todolist;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

public class AjoutTache {
    @FXML
    private Label valueLabel;
    @FXML
    public Button cancel;
    //@FXML
    //public ProgressBar progressBar;
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
    }

    public void canceladd(ActionEvent actionEvent) {
    }


}
