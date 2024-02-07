package com.mycompany.knjiznica;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Vracanje {

    private Map<Osoba, Knjiga> posudeno;
    private List<Osoba> osobe;
    private List<Knjiga> knjige;

    public Vracanje(Map<Osoba, Knjiga> posudeno, List<Osoba> osobe, List<Knjiga> knjige) {
        this.knjige = knjige;
        this.osobe = osobe;
        this.posudeno = posudeno;
    }

    public Parent getView() {
        GridPane layout = new GridPane();
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setPrefSize(475, 150);
        layout.setVgap(5);
        layout.setHgap(40);
        layout.setAlignment(Pos.CENTER);

        ComboBox<Osoba> posudeneOsobe = new ComboBox();
        posudeneOsobe.setPrefSize(150, 25);
        Text posudenaKnjiga = new Text("");
        Text confirmation = new Text("");
        Button vracanje = new Button("Vrati Knjigu");

        for (Map.Entry<Osoba, Knjiga> posudeno : posudeno.entrySet()) {
            posudeneOsobe.getItems().add(posudeno.getKey());
        }

        posudeneOsobe.setOnAction(event -> posudenaKnjiga.setText(posudeno.get(posudeneOsobe.getValue()).toString()));
        vracanje.setOnMouseClicked(event -> {
            try {
                String[] osobaParts = posudeneOsobe.getValue().toString().split(" ");
                String[] knjigaParts = posudenaKnjiga.getText().toString().split(" ");
                Knjiga tempKnjiga = new Knjiga(Integer.parseInt(knjigaParts[0]), Integer.parseInt(knjigaParts[1]), knjigaParts[2], knjigaParts[3]);
                knjige.add(tempKnjiga);
                Osoba tempOsoba = new Osoba(Integer.parseInt(osobaParts[0]), osobaParts[1], osobaParts[2]);
                osobe.add(tempOsoba);
                try {
                    Scanner s = new Scanner(new File("src\\main\\java\\com\\mycompany\\knjiznica\\PosudeneKnjige.txt"));

                    while (s.hasNext()) {
                        String[] mainParts = s.nextLine().split("@");
                        String[] personParts = mainParts[0].split(" ");
                        String[] bookParts = mainParts[1].split(" ");
                        Knjiga tempknjiga = new Knjiga(Integer.parseInt(bookParts[0]), Integer.parseInt(bookParts[1]), bookParts[2], bookParts[3]);
                        Osoba temposoba = new Osoba(Integer.parseInt(personParts[0]), personParts[1], personParts[2]);
                        posudeno.put(temposoba, tempknjiga);
                    }
                    s.close();
                } catch (Exception e) {
                    System.out.println("error " + e);
                }
                String bookTxtRefreshBuffer = "";
                for (Knjiga knjiga : knjige) {
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
                for (Osoba osoba : osobe) {
                    personTxtRefreshBuffer += osoba + "\n";
                }
                try {
                    PrintWriter writer = new PrintWriter("src\\main\\java\\com\\mycompany\\knjiznica\\Osobe.txt");
                    writer.println(personTxtRefreshBuffer);
                    writer.close();
                } catch (Exception e) {
                    System.out.println(e);
                }

            } catch (NullPointerException e) {
                confirmation.setText("not selected");
            }
        });

        layout.add(posudeneOsobe, 0, 0);
        layout.add(posudenaKnjiga, 1, 0);
        layout.add(vracanje, 0, 1);
        layout.add(confirmation, 1, 1);

        return layout;
    }
}
