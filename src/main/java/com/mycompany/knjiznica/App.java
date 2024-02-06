package com.mycompany.knjiznica;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        GridPane layout = new GridPane();
        layout.setPrefSize(350, 150);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setAlignment(Pos.TOP_LEFT);

        Osoba person = new Osoba("David");
        Map<Osoba, List<Knjiga>> posudeneKnjige = new HashMap();
        List<Knjiga> knjige = new ArrayList<>();

        Text bookName = new Text("Ime Knjige");
        Text bookId = new Text("ID Knjige");
        TextField nameInput = new TextField();
        TextField idInput = new TextField();
        Text confirmation = new Text("");
        Button addBook = new Button("Dodaj knjigu");
        Button saveBooks = new Button("Spremi dodane knjige");

        layout.add(bookName, 0, 0);
        layout.add(bookId, 0, 2);
        layout.add(nameInput, 0, 1);
        layout.add(idInput, 0, 3);
        layout.add(addBook, 1, 1);
        layout.add(saveBooks, 1, 3);
        layout.add(confirmation, 0, 5);

        addBook.setOnMouseClicked(event -> {
            Knjiga knjiga = new Knjiga(Integer.valueOf(idInput.getText()), nameInput.getText());
            knjige.add(knjiga);
            confirmation.setText("Knjiga dodana");
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(e -> confirmation.setText(""));
            delay.play();
        });

        saveBooks.setOnMouseClicked(event -> {
            String buffer = "";
            for (Knjiga knjiga : knjige) {
                buffer += knjiga + "\n";
            }
            try {
                PrintWriter writer = new PrintWriter("C:\\Users\\Gamer\\Documents\\NetBeansProjects\\Knjiznica\\src\\main\\java\\com\\mycompany\\knjiznica\\Knjige.txt");
                writer.println(buffer);
                writer.close();
            } catch (Exception e) {
                System.out.println();
            }
        });
        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            try {
                PrintWriter writer = new PrintWriter("C:\\Users\\Gamer\\Documents\\NetBeansProjects\\Knjiznica\\src\\main\\java\\com\\mycompany\\knjiznica\\Knjige.txt");
                writer.println("");
                writer.close();
            } catch (Exception e) {
                System.out.println();
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
