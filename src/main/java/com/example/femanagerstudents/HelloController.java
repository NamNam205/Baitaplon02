package com.example.femanagerstudents;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class HelloController {
    public TextField UsernameTextField;
    public PasswordField PasswordTextField;
    public AnchorPane welcomeScence;
    @FXML
    private Label welcomeText;
    @FXML
    private Label LoginMessageLabel;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void Login()throws IOException {
            String username = UsernameTextField.getText();
            String password = PasswordTextField.getText();
            if(username.equals("admin")&& password.equals("1")) {
                // Nếu tài khoản và mật khẩu là "admin" và "1"
                ToolFXML.openFXML("Home.fxml");
                ToolFXML.closeFXML(welcomeScence);
            }
            else if (username.equals("user") && password.equals("1")) {
                // Nếu tài khoản và mật khẩu là "user" và "1"
                ToolFXML.openFXML("Homestudent.fxml");
                ToolFXML.closeFXML(welcomeScence);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Thông Báo!");
                alert.setHeaderText("Tài Khoản Không Hợp Lệ");
                alert.setContentText("Xin Vui Lòng Thử Lại!");
                alert.showAndWait();
            }
            //check login



    }

}
