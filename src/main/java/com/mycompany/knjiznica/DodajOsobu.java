package com.mycompany.knjiznica;

import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
    private AtomicInteger id;

    public DodajOsobu(List<Osoba> people, AtomicInteger id) {
        this.osobe = people;
        this.id = id;
    }

    public Parent getView() {
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setPrefSize(475, 150);
        layout.setVgap(5);
        layout.setHgap(40);
        layout.setAlignment(Pos.CENTER_LEFT);

        AtomicInteger id = new AtomicInteger(0);

        Text bookName = new Text("Ime");
        Text bookId = new Text("Prezime");
        TextField firstNameInput = new TextField();
        TextField lastNameInput = new TextField();
        Text confirmation = new Text("");
        Button addPerson = new Button("Dodaj osobu");

        layout.add(bookName, 0, 0);
        layout.add(bookId, 0, 2);
        layout.add(firstNameInput, 0, 1);
        layout.add(lastNameInput, 0, 3);
        layout.add(confirmation, 1, 2);
        layout.add(addPerson, 0, 5);

        addPerson.setOnMouseClicked(event -> {
            if (firstNameInput.getText().isEmpty() || lastNameInput.getText().isEmpty()) {
                confirmation.setText("Sva polja su obavezna");
            } else {

                Osoba osoba = new Osoba(id.intValue(), firstNameInput.getText(), lastNameInput.getText());
                osobe.add(osoba);
                id.incrementAndGet();
                confirmation.setText("Osoba dodana");
                firstNameInput.setText("");
                lastNameInput.setText("");
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(e -> confirmation.setText(""));
                delay.play();
                String buffer = "";
                for (Osoba temp : osobe) {
                    buffer += temp + "\n";
                }
                try {
                    PrintWriter writer = new PrintWriter("src\\main\\java\\com\\mycompany\\knjiznica\\Osobe.txt");
                    writer.println(buffer);
                    writer.close();
                } catch (Exception e) {
                    System.out.println();
                }
            }
        });

        return layout;
    }
}
