package com.mycompany.knjiznica;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setPrefSize(475, 150);
        HBox buttons = new HBox();
        List<Knjiga> knjige = new ArrayList();
        List<Osoba> osobe = new ArrayList();
        Map<Osoba, Knjiga> posudeno = new HashMap();
        AtomicInteger bookID = new AtomicInteger(0);
        AtomicInteger peopleID = new AtomicInteger(0);
        BookList listaKnjiga = new BookList(knjige);
        DodajKnjigu dodajKnjigu = new DodajKnjigu(knjige, bookID);
        DodajOsobu dodajOsobu = new DodajOsobu(osobe, peopleID);
        ListaOsoba listaOsoba = new ListaOsoba(osobe);
        Posudba posudba = new Posudba(osobe, knjige, posudeno);
        buttons.setSpacing(5);

        Button bookList = new Button("Lista knjiga");
        Button addBook = new Button("Dodaj Knjigu");
        Button addPerson = new Button("Dodaj Osobu");
        Button personList = new Button("Lista Osoba");
        Button bookBorowing = new Button("Posudi Knjigu");

        buttons.getChildren().addAll(addBook, bookList, addPerson, personList, bookBorowing);
        layout.setTop(buttons);
        layout.setCenter(dodajKnjigu.getView());

        bookList.setOnMouseClicked(event -> {
            layout.setCenter(listaKnjiga.getView());
        });
        addBook.setOnMouseClicked(event -> {
            layout.setCenter(dodajKnjigu.getView());
        });
        addPerson.setOnMouseClicked(event -> {
            layout.setCenter(dodajOsobu.getView());
        });
        personList.setOnMouseClicked(event -> {
            layout.setCenter(listaOsoba.getView());
        });
        bookBorowing.setOnMouseClicked(event ->{
            layout.setCenter(posudba.getView());
        });

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
