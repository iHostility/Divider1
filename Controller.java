/*
 * Copyright (c) Voloshin Denis
 */

package proper.calc;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Controller {
    private String result = "";

    @FXML
    private TextField dividend;

    @FXML
    private TextField divider;

    @FXML
    private TextArea output;

    @FXML
    void closeApp(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void showAboutPopup(ActionEvent event) {
        final Stage popup = new Stage();
        popup.setWidth(300);
        popup.setHeight(200);
        popup.initModality(Modality.APPLICATION_MODAL);
        popup.setTitle("About");
        Button okButt = new Button("OK");
        okButt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                popup.close();
            }
        });

        Scene about = new Scene(VBoxBuilder.create()
                .children(new Text("Мой второй калькулятор на JavaFX\nВолошин Денис,\nпри помощи гугла и какой-то матери."), okButt)
                .alignment(Pos.CENTER)
                .padding(new Insets(10, 10, 10, 10))
                .build());
        popup.setScene(about);
        popup.show();
    }


    @FXML
    void input(KeyEvent event) {
        char c = event.getCharacter().charAt(0);
        if (((c < '0') || (c > '9')) && (c != '.')) {
            event.consume();  // Игнорим все кроме цифр и знака "."
        }
    }

    @FXML
    void divide(ActionEvent event) {
        double a = 0, b = 0;
        String aString = dividend.getText();
        String bString = divider.getText();

        if (!aString.isEmpty() && !bString.isEmpty()) {
            a = Double.parseDouble(aString);
            b = Double.parseDouble(bString);
        }

        if (!aString.isEmpty() && !bString.isEmpty()) {
            if (b == 0) output.setText("Деление на 0");
            else {
                result += a / b;
                output.setText(result);
            }
            result = "";
        }
        else output.setText("Поля не заполнены");
    }
}

