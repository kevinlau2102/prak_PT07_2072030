package com.example.prak_pt07_2072030;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MainController {
    @FXML
    private TextArea isiFile;
    private Path p;

    public void openFile() {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text Documents", "*.txt");
        chooser.getExtensionFilters().add(extensionFilter);
        chooser.setSelectedExtensionFilter(extensionFilter);
        File f = chooser.showOpenDialog(isiFile.getScene().getWindow());
        if (f != null) {
            Path p = Paths.get(f.toURI());
            try {
                String jsonString = Files.readString(p);
                isiFile.setText(jsonString);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void save() {
        if (p != null) {
            try {
                Files.write(p, isiFile.getText().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            saveAsFile();
        }

    }

    public void saveAsFile() {
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text Documents", "*.txt");
        chooser.getExtensionFilters().add(extensionFilter);
        chooser.setSelectedExtensionFilter(extensionFilter);
        File f = chooser.showSaveDialog(isiFile.getScene().getWindow());
        if (f != null) {
            p = Paths.get(f.toURI());
            try {
                Files.write(p, isiFile.getText().getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}