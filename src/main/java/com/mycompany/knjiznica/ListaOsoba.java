package com.mycompany.knjiznica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setPrefSize(475, 150);
        ListView<String> peopleList = new ListView();
        try ( BufferedReader reader = new BufferedReader(new FileReader(new File("src\\main\\java\\com\\mycompany\\knjiznica\\Osobe.txt")))) {

            String line;
            while ((line = reader.readLine()) != null) {
                peopleList.getItems().add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        layout.setCenter(peopleList);

        return layout;
    }
}
