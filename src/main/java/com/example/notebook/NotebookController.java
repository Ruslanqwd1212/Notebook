package com.example.notebook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NotebookController {
    @FXML
    Button buttonAdd;
    @FXML
    Button buttonDelete;
    @FXML
    TextField inputField;
    @FXML
    ListView <String> listView;

    @FXML
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    public void buttonClickAdd(){
        String inputWords = inputField.getText();
        list.add(inputWords);
        listView.setItems(list);
    }

    @FXML
    public void buttonClickDelete(){
        String inputWordsForDelete = inputField.getText();
        list.remove(inputWordsForDelete);
        listView.setItems(list);
    }
}
