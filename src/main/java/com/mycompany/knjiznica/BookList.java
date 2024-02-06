package com.mycompany.knjiznica;

import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class BookList {

    private List<Knjiga> knjige;

    public BookList(List<Knjiga> books) {
        this.knjige = books;
    }

    public Parent getView() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(350, 150);
        layout.setPadding(new Insets(20, 20, 20, 20));
        Button back = new Button ("Back");
        ListView<String> bookList = new ListView();
        for (Knjiga knjiga : knjige) {
            bookList.getItems().add(knjiga.toString());
        }
        layout.setCenter(bookList);


        
        return layout;
    }

}
