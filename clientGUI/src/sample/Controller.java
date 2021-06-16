package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    @FXML
    private RadioButton computerRadioButton;
    @FXML
    private RadioButton phoneRadioButton;
    @FXML
    private ListView<String> productsListView;

    private List<String> productList = new ArrayList<>();

    @FXML
    private TableView<String> compareDetailsTableView;

    public void getProductsButtonPressed(ActionEvent event) throws IOException, ParseException {
        if (computerRadioButton.isSelected()) {
            productsListView.getItems().removeAll(productList);
//            productsListView.getItems().addAll("Computers");
            String response = "";
            HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/getComputers").openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if(responseCode == 200) {
                Scanner scanner = new Scanner(connection.getInputStream());
                while (scanner.hasNextLine()) {
                    response += scanner.nextLine();
                    response += "\n";
                }
                scanner.close();
            }
            JSONParser parser = new JSONParser();

            Object obj = parser.parse(response);
            JSONArray array = (JSONArray) obj;
            String tempProduct = "";
            for (int i = 0; i < array.size(); i++) {
                tempProduct = "";
                JSONObject tempObj = (JSONObject) array.get(i);
                tempProduct += tempObj.get("brand").toString() + " ";
                tempProduct += tempObj.get("model").toString() + " ";
                int tempMemory = Integer.parseInt(tempObj.get("memory").toString());
                float tempStorageCapacity = Float.parseFloat(tempObj.get("storageCapacity").toString());

                if (tempMemory > 16) {
                    tempProduct += "Large Memory ";
                }
                if (tempStorageCapacity > 128) {
                    tempProduct += "Large Storage ";
                }
                productList.add(tempProduct);
                productsListView.getItems().add(tempProduct);

            }
//            productsListView.getItems().addAll(0, productList);

        }
        else if (phoneRadioButton.isSelected()) {
            productsListView.getItems().removeAll(productList);
//            productsListView.getItems().add("phone");
            String response = "";
            HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/getPhones").openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if(responseCode == 200) {
                Scanner scanner = new Scanner(connection.getInputStream());
                while (scanner.hasNextLine()) {
                    response += scanner.nextLine();
                    response += "\n";
                }
                scanner.close();
            }
            JSONParser parser = new JSONParser();

            Object obj = parser.parse(response);
            JSONArray array = (JSONArray) obj;
            String tempProduct = "";
            for (int i = 0; i < array.size(); i++) {
                tempProduct = "";
                JSONObject tempObj = (JSONObject) array.get(i);
                tempProduct += tempObj.get("brand").toString() + " ";
                tempProduct += tempObj.get("model").toString() + " ";
                float tempScreenSize = Float.parseFloat(tempObj.get("screenSize").toString());
                float tempInternalMemory = Float.parseFloat(tempObj.get("internalMemory").toString());

                if (tempScreenSize > 6.0) {
                    tempProduct += "Large Screen ";
                }
                if (tempInternalMemory > 1024) {
                    tempProduct += "Large Storage ";
                }
                productList.add(tempProduct);
                productsListView.getItems().add(tempProduct);
            }
        }
    }

    public void sortButtonPressed(ActionEvent event) {

    }

    public void compareButtonPressed(ActionEvent event) {
        ObservableList<String> rows = FXCollections.observableArrayList();
        List<String> row = new ArrayList<>();
        row.add("1");
        row.add("11");
        row.add("model");
        row.add("brand");
        row.add("fjf");
        row.add("jfj");
        row.add("jfj");
        row.add("pa;");
        row.add("jfj");
        row.add("jfjf");

        compareDetailsTableView.getItems().addAll(row);
        compareDetailsTableView.getItems().addAll(row);
//
////        rows.addAll("model");
//        compareDetailsTableView.setItems(rows);
    }
}
