package com.mycompany.knjiznica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class BookList {

    private List<Knjiga> knjige;

    public BookList(List<Knjiga> books) {
        this.knjige = books;
    }
//"src\\main\\java\\com\\mycompany\\knjiznica\\Knjige.txt"

    public Parent getView() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(350, 150);
        layout.setPadding(new Insets(20, 20, 20, 20));
        ListView<String> bookList = new ListView();
        try ( BufferedReader reader = new BufferedReader(new FileReader(new File("src\\main\\java\\com\\mycompany\\knjiznica\\Knjige.txt")))) {

            String line;
            while ((line = reader.readLine()) != null) {
                bookList.getItems().add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        layout.setCenter(bookList);

        return layout;

    }

}
