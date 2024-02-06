package com.mycompany.knjiznica;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
        layout.setPadding(new Insets(20,20,20,20));
        layout.setPrefSize(350, 150);
        HBox buttons = new HBox();
        List<Knjiga> knjige = new ArrayList();
        BookList listaKnjiga = new BookList(knjige);
        DodajKnjigu dodajKnjigu = new DodajKnjigu(knjige);

        buttons.setSpacing(25);
        
        Button bookList = new Button("Lista knjiga");
        Button addBook = new Button("Dodaj Knjigu");
        Button addPerson = new Button("Dodaj Osobu");

        buttons.getChildren().addAll(addBook, bookList, addPerson);
        layout.setTop(buttons);
        layout.setCenter(dodajKnjigu.getView());

        bookList.setOnMouseClicked(event -> {
            layout.setCenter(listaKnjiga.getView());
        });
        addBook.setOnMouseClicked(event -> {
            layout.setCenter(dodajKnjigu.getView());
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
