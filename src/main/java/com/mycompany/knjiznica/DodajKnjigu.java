package com.mycompany.knjiznica;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
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
        layout.setVgap(5);
        layout.setHgap(5);
        layout.setAlignment(Pos.TOP_LEFT);

        Map<Osoba, List<Knjiga>> posudeneKnjige = new HashMap();
        AtomicInteger id = new AtomicInteger(0);

        Text bookName = new Text("Ime Knjige");
        Text bookWriter = new Text("Pisac Knjige");
        Text bookISBN = new Text("ISBN Knjige");
        TextField nameInput = new TextField();
        TextField ISBNInput = new TextField();
        TextField writerInput = new TextField();
        Text confirmation = new Text("");
        Button addBook = new Button("Dodaj knjigu");
        Button saveBooks = new Button("Spremi dodane knjige");

        layout.add(bookName, 0, 0);
        layout.add(nameInput, 0, 1);
        layout.add(bookWriter, 0, 2);
        layout.add(writerInput, 0, 3);
        layout.add(bookISBN, 0, 4);
        layout.add(ISBNInput, 0, 5);
        layout.add(addBook, 1, 1);
        layout.add(saveBooks, 1, 3);
        layout.add(confirmation, 0, 6);

        addBook.setOnMouseClicked(event -> {
            
            if (nameInput.getText().isEmpty() || ISBNInput.getText().isEmpty() || writerInput.getText().isEmpty()) {
                confirmation.setText("Sva polja su obavezna");
            } else {
                int intISBN;
                try{
                    intISBN = Integer.parseInt(ISBNInput.getText());
                } catch(NumberFormatException ex){
                    confirmation.setText("ISBN mora biti samo broj");
                    return;
                }
                Knjiga knjiga = new Knjiga(id.intValue(), intISBN, nameInput.getText(), writerInput.getText());
                knjige.add(knjiga);
                id.incrementAndGet();
                confirmation.setText("Knjiga dodana");
                ISBNInput.setText("");
                nameInput.setText("");
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                delay.setOnFinished(e -> confirmation.setText(""));
                delay.play();
            }
        });

        saveBooks.setOnMouseClicked(event -> {

            String buffer = "";
            for (Knjiga knjiga : knjige) {
                buffer += knjiga + "\n";
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
