package com.mycompany.knjiznica;

import java.io.PrintWriter;
import java.util.List;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class DodajOsobu {

    private List<Osoba> osobe;

    public DodajOsobu(List<Osoba> people) {
        this.osobe = people;
    }

    public Parent getView() {
        GridPane layout = new GridPane();
        layout.setPrefSize(350, 150);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setAlignment(Pos.TOP_LEFT);
        Text bookName = new Text("Ime");
        Text bookId = new Text("Prezime");
        TextField firstNameInput = new TextField();
        TextField lastNameInput = new TextField();
        Text confirmation = new Text("");
        Button addPerson = new Button("Dodaj osobu");
        Button savePersons = new Button("Spremi dodane osobe");

        layout.add(bookName, 0, 0);
        layout.add(bookId, 0, 2);
        layout.add(firstNameInput, 0, 1);
        layout.add(lastNameInput, 0, 3);
        layout.add(addPerson, 1, 1);
        layout.add(savePersons, 1, 3);
        layout.add(confirmation, 0, 5);

        addPerson.setOnMouseClicked(event -> {
            Osoba osoba = new Osoba(lastNameInput.getText(), firstNameInput.getText());
            osobe.add(osoba);
            confirmation.setText("Osoba dodana");
            firstNameInput.setText("");
            lastNameInput.setText("");
            PauseTransition delay = new PauseTransition(Duration.seconds(2));
            delay.setOnFinished(e -> confirmation.setText(""));
            delay.play();
        });

        savePersons.setOnMouseClicked(event -> {
            String buffer = "";
            for (Osoba osoba : osobe) {
                buffer += osoba + "\n";
            }
            try {
                PrintWriter writer = new PrintWriter("src\\main\\java\\com\\mycompany\\knjiznica\\Knjige.txt");
                writer.println(buffer);
                writer.close();
            } catch (Exception e) {
                System.out.println();
            }
        });

        return layout;
    }
}
