package com.mycompany.knjiznica;

import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;

public class ListaOsoba {

    private List<Osoba> osobe;

    public ListaOsoba(List<Osoba> osobe) {
        this.osobe = osobe;
    }

    public Parent getView() {
        BorderPane layout = new BorderPane();
        layout.setPrefSize(350, 150);
        layout.setPadding(new Insets(20, 20, 20, 20));
        ListView<String> peopleList = new ListView();
        for (Osoba osoba : osobe) {
            peopleList.getItems().add(osoba.toString());
        }
        layout.setCenter(peopleList);

        return layout;
    }
}
