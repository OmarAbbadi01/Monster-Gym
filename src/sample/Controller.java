package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Stage logInStage, primaryStage, newCustomerStage, changePassStage;
    @FXML
    private TextField usernameTextField, searchTextField, customerName, customerAge, customerPhone, customerAddress,
            moneyAmount, notesField, oldPasswordField, newPasswordField, newPasswordConfirmFeild;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button logInButton, signOutButton, dateSearchButton, reportButton, passChangeButton, newCustomerButton,
            searchButton, signUpCustomerButton;
    @FXML
    private Label logInStatusLabel, newCusStatusLabel, passwordChangeStatusLabel;
    @FXML
    private DatePicker searchDatePicker, startDate, endDate;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void logIn(ActionEvent event) throws Exception {
        if (usernameTextField.getText().equals("monster") && passwordField.getText().equals("123")) {
            logInStatusLabel.setTextFill(Color.rgb(60, 170, 57));
            logInStatusLabel.setText("كلمة مرور صحيحة");
            ((Stage) logInButton.getScene().getWindow()).close();
            Parent newRoot = FXMLLoader.load(getClass().getResource("Primary.fxml"));
            primaryStage = new Stage();
            primaryStage = initializeStage(primaryStage, new Scene(newRoot), "الشاشة الرئيسية");
            primaryStage.show();
            DBHelper.startConnection();
        } else if (!usernameTextField.getText().equals("monster")) {
            logInStatusLabel.setTextFill(Color.rgb(208, 46, 46));
            logInStatusLabel.setText("اسم مستخدم خاطئ");
        } else {
            logInStatusLabel.setTextFill(Color.rgb(208, 46, 46));
            logInStatusLabel.setText("كلمة مرور خاطئة");
        }
    }

    public void signOut(ActionEvent event) throws Exception {
        if (newCustomerStage != null) {
            newCustomerStage.close();
        }
        if (changePassStage != null) {
            changePassStage.close();
        }
        ((Stage) signOutButton.getScene().getWindow()).close();
        Parent newRoot = FXMLLoader.load(getClass().getResource("Login.fxml"));
        logInStage = new Stage();
        logInStage = initializeStage(logInStage, new Scene(newRoot), "Monster Gym");
        logInStage.show();
        DBHelper.closeConnection();
    }

    public void newCustomer(ActionEvent event) throws Exception {
        if (newCustomerStage != null && newCustomerStage.isShowing()) {
            newCustomerStage.toFront();
        } else {
            Parent newRoot = FXMLLoader.load(getClass().getResource("NewCustomer.fxml"));
            newCustomerStage = new Stage();
            newCustomerStage = initializeStage(newCustomerStage, new Scene(newRoot), "مشترك جديد");
            newCustomerStage.show();
            // startDate.setValue(LocalDate.now());
        }
    }

    public void signUpCustomer(ActionEvent event) throws Exception {
        if (customerName.getText().isEmpty()) {
            newCusStatusLabel.setTextFill(Color.rgb(208, 46, 46));
            newCusStatusLabel.setText("أدخل اسم المستخدم");
        } else if (customerAge.getText().isEmpty()) {
            newCusStatusLabel.setTextFill(Color.rgb(208, 46, 46));
            newCusStatusLabel.setText("أدخل عمر المستخدم");
        } else if (moneyAmount.getText().isEmpty()) {
            newCusStatusLabel.setTextFill(Color.rgb(208, 46, 46));
            newCusStatusLabel.setText("أدخل المبلغ المدفوع");
        } else if (!checkPhoneNumber(customerPhone.getText())) {
            newCusStatusLabel.setTextFill(Color.rgb(208, 46, 46));
            newCusStatusLabel.setText("رقم الهاتف غير صحيح");
        } else {
            System.out.println("Hi 7ob");
        }
    }

    public void changePassword(ActionEvent event) throws Exception {
        if (changePassStage != null && changePassStage.isShowing()) {
            changePassStage.toFront();
        } else {
            Parent newRoot = FXMLLoader.load(getClass().getResource("PasswordChange.fxml"));
            changePassStage = new Stage();
            changePassStage = initializeStage(changePassStage, new Scene(newRoot), "تغيير كلمة المرور");
            changePassStage.show();
        }
    }

    public void logInInput(KeyEvent event) throws Exception {
        if (event.getCode().equals(KeyCode.ENTER)) {
            logInButton.fire();
        } else {
            logInStatusLabel.setText("تسجيل الدخول");
            logInStatusLabel.setTextFill(Color.BLACK);
        }
    }

    public void newCustomerInput(KeyEvent event) throws Exception {
        if (event.getCode().equals(KeyCode.ENTER)) {
            signUpCustomerButton.fire();
        } else {
            newCusStatusLabel.setText("تسجيل مشترك جديد");
            newCusStatusLabel.setTextFill(Color.BLACK);
        }
    }

    public void setDefaultDate(KeyEvent event) {
        startDate.setValue(LocalDate.now());
        endDate.setValue(LocalDate.now().plusMonths(1));
    }

    public void setNextMonthDate(ActionEvent event) {
        endDate.setValue(startDate.getValue().plusMonths(1));
    }

    private boolean checkPhoneNumber(String s) {
        if (s.isEmpty())
            return true; // No phone number is allowed
        if (s.length() != 10)
            return false;
        if (s.charAt(0) != '0' || s.charAt(1) != '5')
            return false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9')
                return false;
        }
        return true;
    }

    private Stage initializeStage(Stage stage, Scene scene, String title) {
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image("monster gym.jpeg"));
        stage.centerOnScreen();
        stage.setTitle(title);
        return stage;
    }

    public void searchByDate(ActionEvent event) {

    }

}
