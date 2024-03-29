package com.mycompany.knjiznica;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Posudba {

    private List<Osoba> people;
    private List<Knjiga> books;
    Map<Osoba, Knjiga> posudeno;

    public Posudba(List<Osoba> people, List<Knjiga> books, Map<Osoba, Knjiga> posudeno) {
        this.people = people;
        this.books = books;
        this.posudeno = posudeno;
    }

    public Parent getView() {
        GridPane layout = new GridPane();
        HBox dropdowns = new HBox();
        HBox btnAndList = new HBox();
        VBox btnAndText = new VBox();
        Text confirmation = new Text("");
        dropdowns.setAlignment(Pos.TOP_CENTER);
        dropdowns.setSpacing(5);
        btnAndList.setSpacing(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setPrefSize(475, 150);
        layout.setVgap(5);
        layout.setHgap(10);

        Button borrow = new Button("Posudi");
        ComboBox<Osoba> peopleCB = new ComboBox<>();
        ComboBox<Knjiga> booksCB = new ComboBox<>();
        ListView<String> listaPosudbi = new ListView();

        peopleCB.setPromptText("Izaberi osobu");
        booksCB.setPromptText("Izaberi knjigu");
        peopleCB.setPrefSize(200, 25);
        booksCB.setPrefSize(200, 25);
        borrow.setPrefSize(100, 25);

        for (Osoba osoba : people) {
            peopleCB.getItems().add(osoba);
        }
        for (Knjiga knjiga : books) {
            booksCB.getItems().add(knjiga);
        }
        btnAndText.getChildren().addAll(borrow, confirmation);
        btnAndList.getChildren().addAll(btnAndText, listaPosudbi);
        dropdowns.getChildren().addAll(peopleCB, booksCB);
        layout.add(dropdowns, 0, 0);
        layout.add(btnAndList, 0, 1);

        borrow.setOnMouseClicked(event -> {
            try {
                try {
                    listaPosudbi.getItems().add(peopleCB.getValue().toStringLists() + " je posudio " + booksCB.getValue().toStringLists());
                } catch (NullPointerException e) {
                    confirmation.setText("Potreban je odabir");
                }
                posudeno.put(peopleCB.getValue(), booksCB.getValue());
                books.remove(booksCB.getValue());
                peopleCB.setPromptText("Izaberi osobu");
                booksCB.setPromptText("Izaberi knjigu");
                people.remove(peopleCB.getValue());
                booksCB.getItems().remove(booksCB.getValue());
                peopleCB.getItems().remove(peopleCB.getValue());
                String bookTxtRefreshBuffer = "";
                for (Knjiga knjiga : books) {
                    bookTxtRefreshBuffer += knjiga + "\n";
                }
                try {
                    PrintWriter writer = new PrintWriter("src\\main\\java\\com\\mycompany\\knjiznica\\Knjige.txt");
                    writer.println(bookTxtRefreshBuffer);
                    writer.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                String personTxtRefreshBuffer = "";
                for (Osoba osoba : people) {
                    personTxtRefreshBuffer += osoba + "\n";
                }
                try {
                    PrintWriter writer = new PrintWriter("src\\main\\java\\com\\mycompany\\knjiznica\\Osobe.txt");
                    writer.println(personTxtRefreshBuffer);
                    writer.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
                String borrowedTxtRefreshBuffer = "";
                for (Map.Entry<Osoba, Knjiga> newPosudeno : posudeno.entrySet()) {
                    borrowedTxtRefreshBuffer += newPosudeno.getKey().toString() + " @" + newPosudeno.getValue().toString() + "\n";
                }
                try {
                    PrintWriter writer = new PrintWriter("src\\main\\java\\com\\mycompany\\knjiznica\\PosudeneKnjige.txt");
                    writer.println(borrowedTxtRefreshBuffer);
                    writer.close();
                } catch (Exception e) {
                    System.out.println(e);
                }
            } catch (NullPointerException e) {
                confirmation.setText("Potreban je odabir");

            }
        });

        return layout;
    }

}
