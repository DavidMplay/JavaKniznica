/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.knjiznica;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 *
 * @author Gamer
 */
public class DodajKnjigu {

    private List<Knjiga> knjige;

    public DodajKnjigu(List<Knjiga> books) {
        knjige = books;
    }

    public Parent getView() {
        GridPane layout = new GridPane();
        layout.setPrefSize(350, 150);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setVgap(10);
        layout.setHgap(10);
        layout.setAlignment(Pos.TOP_LEFT);

        Osoba person = new Osoba("David");
        Map<Osoba, List<Knjiga>> posudeneKnjige = new HashMap();

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
            idInput.setText("");
            nameInput.setText("");
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

        return layout;
    }

}
