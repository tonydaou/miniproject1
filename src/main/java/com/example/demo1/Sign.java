package com.example.demo1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.time.LocalDate;

public class Sign extends Application {

    private TableView<Member> tableView = new TableView<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sign In Form");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25));

        Text sceneTitle = new Text("Welcome");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label userNameLabel = new Label("Username:");
        grid.add(userNameLabel, 0, 1);
        TextField userNameField = new TextField();
        grid.add(userNameField, 1, 1);

        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 2);
        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        Button signInButton = new Button("Sign In");
        grid.add(signInButton, 1, 4);

        Text errorMsg = new Text();
        errorMsg.setFill(Color.RED);
        grid.add(errorMsg, 0, 5, 2, 1);

        signInButton.setOnAction(event -> {
            String username = userNameField.getText();
            String password = passwordField.getText();

            if (username.isEmpty() || password.isEmpty()) {
                errorMsg.setText("Username and password are required!");
            } else if (!username.equals("manager") || !password.equals("1234")) {
                errorMsg.setText("Incorrect username and/or password!");
            } else {
                primaryStage.close();
                showMemberForm();
            }
        });

        Scene scene = new Scene(grid, 350, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showMemberForm() {
        Stage memberStage = new Stage();
        memberStage.setTitle("Member Management");

        GridPane formGrid = new GridPane();
        formGrid.setPadding(new Insets(20));
        formGrid.setVgap(10);
        formGrid.setHgap(10);

        TextField nameField = new TextField();
        DatePicker datePicker = new DatePicker();

        RadioButton maleButton = new RadioButton("Male");
        RadioButton femaleButton = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        maleButton.setToggleGroup(genderGroup);
        femaleButton.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, maleButton, femaleButton);

        ChoiceBox<String> activityChoice = new ChoiceBox<>();
        activityChoice.getItems().addAll("Bodybuilding", "Calisthenics", "Pilates");
        TextField addressField = new TextField();

        Slider daysSlider = new Slider(2, 5, 3);
        daysSlider.setShowTickLabels(true);
        daysSlider.setShowTickMarks(true);
        daysSlider.setMajorTickUnit(1);
        daysSlider.setMinorTickCount(0);
        daysSlider.setSnapToTicks(true);

        ChoiceBox<String> membershipTypeChoice = new ChoiceBox<>();
        membershipTypeChoice.getItems().addAll("Monthly", "Yearly");

        formGrid.add(new Label("Name:"), 0, 0);
        formGrid.add(nameField, 1, 0);

        formGrid.add(new Label("Date of Subscription:"), 0, 1);
        formGrid.add(datePicker, 1, 1);

        formGrid.add(new Label("Gender:"), 0, 2);
        formGrid.add(genderBox, 1, 2);

        formGrid.add(new Label("Activity:"), 0, 3);
        formGrid.add(activityChoice, 1, 3);

        formGrid.add(new Label("Address:"), 0, 4);
        formGrid.add(addressField, 1, 4);

        formGrid.add(new Label("Days per week:"), 0, 5);
        formGrid.add(daysSlider, 1, 5);

        formGrid.add(new Label("Membership Type:"), 0, 6);
        formGrid.add(membershipTypeChoice, 1, 6);

        Button submitButton = new Button("Add Member");
        formGrid.add(submitButton, 1, 7);

        Text errorMsg = new Text();
        errorMsg.setFill(Color.RED);
        formGrid.add(errorMsg, 0, 8, 2, 1);

        setupTableView();

        submitButton.setOnAction(e -> {
            RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();
            String gender = selectedGender != null ? selectedGender.getText() : "";

            if (nameField.getText().isEmpty() ||
                    datePicker.getValue() == null ||
                    gender.isEmpty() ||
                    activityChoice.getValue() == null ||
                    addressField.getText().isEmpty() ||
                    membershipTypeChoice.getValue() == null) {

                errorMsg.setText("Please fill in all fields!");
            } else {
                Member member = new Member(
                        nameField.getText(),
                        datePicker.getValue().toString(),
                        gender,
                        activityChoice.getValue(),
                        addressField.getText(),
                        String.valueOf((int) daysSlider.getValue()),
                        membershipTypeChoice.getValue()
                );

                tableView.getItems().add(member);

                nameField.clear();
                datePicker.setValue(null);
                genderGroup.selectToggle(null);
                activityChoice.getSelectionModel().clearSelection();
                addressField.clear();
                daysSlider.setValue(7);
                membershipTypeChoice.getSelectionModel().clearSelection();
                errorMsg.setText("");
            }
        });

        VBox vbox = new VBox(20, formGrid, tableView);
        vbox.setPadding(new Insets(20));

        Scene scene = new Scene(vbox, 800, 600);
        memberStage.setScene(scene);
        memberStage.show();
    }

    private void setupTableView() {
        TableColumn<Member, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Member, String> dateCol = new TableColumn<>("Date of Subscription");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateOfSubscription"));

        TableColumn<Member, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Member, String> activityCol = new TableColumn<>("Activity");
        activityCol.setCellValueFactory(new PropertyValueFactory<>("activity"));

        TableColumn<Member, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Member, String> daysCol = new TableColumn<>("Days per week");
        daysCol.setCellValueFactory(new PropertyValueFactory<>("days"));

        TableColumn<Member, String> membershipCol = new TableColumn<>("Membership Type");
        membershipCol.setCellValueFactory(new PropertyValueFactory<>("membershipType"));

        tableView.getColumns().addAll(nameCol, dateCol, genderCol, activityCol, addressCol, daysCol, membershipCol);
    }

    public static class Member {
        private final String name;
        private final String dateOfSubscription;
        private final String gender;
        private final String activity;
        private final String address;
        private final String days;
        private final String membershipType;

        public Member(String name, String dateOfSubscription, String gender, String activity, String address, String days, String membershipType) {
            this.name = name;
            this.dateOfSubscription = dateOfSubscription;
            this.gender = gender;
            this.activity = activity;
            this.address = address;
            this.days = days;
            this.membershipType = membershipType;
        }

        public String getName() { return name; }
        public String getDateOfSubscription() { return dateOfSubscription; }
        public String getGender() { return gender; }
        public String getActivity() { return activity; }
        public String getAddress() { return address; }
        public String getDays() { return days; }
        public String getMembershipType() { return membershipType; }
    }
}
//test
//test2