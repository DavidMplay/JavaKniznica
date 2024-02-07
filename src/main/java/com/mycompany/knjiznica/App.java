package com.mycompany.knjiznica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setPrefSize(475, 150);
        HBox buttons = new HBox();
        HBox buttons2 = new HBox();
        VBox allButtons = new VBox();

        List<Knjiga> knjige = new ArrayList();
        try {
            Scanner s = new Scanner(new File("src\\main\\java\\com\\mycompany\\knjiznica\\Knjige.txt"));

            while (s.hasNext()) {
                String[] parts = s.nextLine().split(" ");
                knjige.add(new Knjiga(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), parts[2], parts[3]));
            }
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        List<Osoba> osobe = new ArrayList();
        try {
            Scanner s = new Scanner(new File("src\\main\\java\\com\\mycompany\\knjiznica\\Osobe.txt"));

            while (s.hasNext()) {
                String[] parts = s.nextLine().split(" ");
                osobe.add(new Osoba(Integer.parseInt(parts[0]), parts[1], parts[2]));
            }
            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        Map<Osoba, Knjiga> posudeno = new HashMap();
        try {
            Scanner s = new Scanner(new File("src\\main\\java\\com\\mycompany\\knjiznica\\PosudeneKnjige.txt"));

            while (s.hasNext()) {
                String[] mainParts = s.nextLine().split("@");
                String[] personParts = mainParts[0].split(" ");
                String[] bookParts = mainParts[1].split(" ");
                Knjiga tempKnjiga = new Knjiga(Integer.parseInt(bookParts[0]), Integer.parseInt(bookParts[1]), bookParts[2], bookParts[3]);
                Osoba tempOsoba = new Osoba(Integer.parseInt(personParts[0]), personParts[1], personParts[2]);
                posudeno.put(tempOsoba, tempKnjiga);
            }
            s.close();
        } catch (Exception e) {
            System.out.println("error " + e);
        }

        AtomicInteger peopleID;
        AtomicInteger bookID;
        try {
            peopleID = new AtomicInteger(osobe.get(osobe.size() - 1).getID() + 1);
            bookID = new AtomicInteger(knjige.get(knjige.size() - 1).getID() + 1);
        } catch (IndexOutOfBoundsException e) {
            peopleID = new AtomicInteger(0);
            bookID = new AtomicInteger(0);
        }
        BookList listaKnjiga = new BookList(knjige);
        DodajKnjigu dodajKnjigu = new DodajKnjigu(knjige, bookID);
        DodajOsobu dodajOsobu = new DodajOsobu(osobe, peopleID);
        ListaOsoba listaOsoba = new ListaOsoba(osobe);
        Posudba posudba = new Posudba(osobe, knjige, posudeno);
        Vracanje vracanje = new Vracanje(posudeno, osobe, knjige);
        buttons.setSpacing(5);
        allButtons.setAlignment(Pos.CENTER);
        buttons.setAlignment(Pos.CENTER);
        buttons2.setAlignment(Pos.CENTER);
        buttons2.setSpacing(5);

        Button bookList = new Button("Lista knjiga");
        Button addBook = new Button("Dodaj Knjigu");
        Button addPerson = new Button("Dodaj Osobu");
        Button personList = new Button("Lista Osoba");
        Button bookBorowing = new Button("Posudi Knjigu");
        Button bookReturning = new Button("Vrati Knjigu");

        buttons.getChildren().addAll(addBook, bookList, addPerson, personList);
        buttons2.getChildren().addAll(bookBorowing, bookReturning);
        allButtons.getChildren().addAll(buttons, buttons2);
        layout.setTop(allButtons);
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
        bookBorowing.setOnMouseClicked(event -> {
            layout.setCenter(posudba.getView());
        });
        bookReturning.setOnMouseClicked(event -> {
            layout.setCenter(vracanje.getView());
        });

        Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
