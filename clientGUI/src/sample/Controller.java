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
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Controller {

    @FXML
    private RadioButton computerRadioButton;
    @FXML
    private RadioButton phoneRadioButton;
    @FXML
    private RadioButton baseFeaturesRadioButton;
    @FXML
    private RadioButton additionalFeaturesRadioButton;
    @FXML
    private RadioButton bothFeaturesRadioButton;

    @FXML
    private ListView<String> productsListView;

    private List<String> productList = new ArrayList<>();

    private List<String> computerSearchKeys = Arrays.asList("computer ID", "brand", "model", "screen size", "resolution", "processor", "memory", "storage", "price");
    private List<String> phoneSearchKeys = Arrays.asList("phone ID", "brand", "model", "screen size", "internal memory", "price");
    private List<String> numericKeys = Arrays.asList("screen size", "resolution", "processor", "memory", "internal memory", "storage", "price");

    @FXML
    private TableView<String> compareDetailsTableView;

    private List<Computer> computerList = new ArrayList<>();
    private List<Phone> phoneList = new ArrayList<>();


    @FXML
    private Label baseSearchLabel;
    @FXML
    private Label additionalSearchLabel;

    @FXML
    private CheckBox search2CheckBox;
    @FXML
    private CheckBox search3CheckBox;
    @FXML
    private CheckBox search4CheckBox;

    @FXML
    private ComboBox feature1ComboBox;
    @FXML
    private ComboBox feature2ComboBox;
    @FXML
    private ComboBox feature3ComboBox;
    @FXML
    private ComboBox feature4ComboBox;
    @FXML
    private ComboBox additionalFeatureComboBox;

    @FXML
    private ComboBox operation1ComboBox;
    @FXML
    private ComboBox operation2ComboBox;
    @FXML
    private ComboBox operation3ComboBox;
    @FXML
    private ComboBox operation4ComboBox;


    @FXML
    public TextField feature1TextField;
    @FXML
    public TextField feature2TextField;
    @FXML
    public TextField feature3TextField;
    @FXML
    public TextField feature4TextField;

    public String operationParser(String operation) {
        if (operation.equals("=")) return "eq";
        else if (operation.equals("<")) return "lt";
        else if (operation.equals(">")) return "gt";
        else if (operation.equals("<=")) return "le";
        else if (operation.equals(">=")) return "ge";
        else return "";
    }
    public String keyParser(String key) {
        if(key.equals("computer ID")) return "computerID";
        else if (key.equals("screen size")) return "screenSize";
        else if (key.equals("resolution")) return "screenResolution";
        else if (key.equals("storage")) return "storageCapacity";
        else if (key.equals("phone ID")) return "phoneID";
        else if (key.equals("internal memory")) return "internalMemory";
        else return key;
    }

    public void initialize() throws IOException, ParseException {

        getAdditionalFeatures();

        operation1ComboBox.getItems().addAll("=", ">", "<", ">=", "<=");
        operation2ComboBox.getItems().addAll("=", ">", "<", ">=", "<=");
        operation3ComboBox.getItems().addAll("=", ">", "<", ">=", "<=");
        operation4ComboBox.getItems().addAll("=", ">", "<", ">=", "<=");

        baseSearchLabel.setVisible(false);
        additionalSearchLabel.setVisible(false);

        search2CheckBox.setVisible(false);
        search3CheckBox.setVisible(false);
        search4CheckBox.setVisible(false);

        feature1ComboBox.setVisible(false);
        feature2ComboBox.setVisible(false);
        feature3ComboBox.setVisible(false);
        feature4ComboBox.setVisible(false);

        operation1ComboBox.setVisible(false);
        operation2ComboBox.setVisible(false);
        operation3ComboBox.setVisible(false);
        operation4ComboBox.setVisible(false);

        feature1TextField.setVisible(false);
        feature2TextField.setVisible(false);
        feature3TextField.setVisible(false);
        feature4TextField.setVisible(false);

        additionalFeatureComboBox.setVisible(false);
    }


    public void typeRadioButtonSelected(ActionEvent event) {
        feature1ComboBox.getItems().clear();
        feature2ComboBox.getItems().clear();
        feature3ComboBox.getItems().clear();
        feature4ComboBox.getItems().clear();
        if (computerRadioButton.isSelected()) {
            feature1ComboBox.getItems().addAll(computerSearchKeys);
            feature2ComboBox.getItems().addAll(computerSearchKeys);
            feature3ComboBox.getItems().addAll(computerSearchKeys);
            feature4ComboBox.getItems().addAll(computerSearchKeys);
            System.out.println(1);
        }
        else if (phoneRadioButton.isSelected()) {
            feature1ComboBox.getItems().addAll(phoneSearchKeys);
            feature2ComboBox.getItems().addAll(phoneSearchKeys);
            feature3ComboBox.getItems().addAll(phoneSearchKeys);
            feature4ComboBox.getItems().addAll(phoneSearchKeys);
            System.out.println(2);
        }
    }

    public void setVisibleBaseSearch(boolean tmp) {
        baseSearchLabel.setVisible(tmp);
        feature1ComboBox.setVisible(tmp);
        feature1TextField.setVisible(tmp);
        search2CheckBox.setVisible(tmp);
        search3CheckBox.setVisible(tmp);
        search4CheckBox.setVisible(tmp);

        if(!tmp) {
            search2CheckBox.setSelected(false);
            search3CheckBox.setSelected(false);
            search4CheckBox.setSelected(false);

            feature2ComboBox.setVisible(false);
            feature3ComboBox.setVisible(false);
            feature4ComboBox.setVisible(false);

            operation1ComboBox.setVisible(false);
            operation2ComboBox.setVisible(false);
            operation3ComboBox.setVisible(false);
            operation4ComboBox.setVisible(false);

            feature1TextField.setVisible(false);
            feature2TextField.setVisible(false);
            feature3TextField.setVisible(false);
            feature4TextField.setVisible(false);
        }
    }

    public void setVisibleAdditionalSearch(boolean tmp) {
        additionalSearchLabel.setVisible(tmp);
        additionalFeatureComboBox.setVisible(tmp);
    }

    public void searchRadioButtonSelected(ActionEvent event) {
        if (bothFeaturesRadioButton.isSelected()) {
            setVisibleAdditionalSearch(true);
            setVisibleBaseSearch(true);
            return;
        }

        if (baseFeaturesRadioButton.isSelected()) {
            setVisibleBaseSearch(true);
            setVisibleAdditionalSearch(false);
        }
        else if (additionalFeaturesRadioButton.isSelected()) {
            setVisibleAdditionalSearch(true);
            setVisibleBaseSearch(false);
        }
    }


    public void addSearchLine(ActionEvent event) {
        String tmpString = "";
        if (search2CheckBox.isSelected()) {
            feature2ComboBox.setVisible(true);
            feature2TextField.setVisible(true);
//            operation2ComboBox.setVisible(numericKeys.contains(feature2ComboBox.getValue().toString()));
            Object t = feature2ComboBox.getValue();
            if (t != null) tmpString = t.toString(); else tmpString = "";
            operation2ComboBox.setVisible(numericKeys.contains(tmpString));

        } else {
            feature2ComboBox.setVisible(false);
            feature2TextField.setVisible(false);
            operation2ComboBox.setVisible(false);
        }


        if (search3CheckBox.isSelected()) {
            feature3ComboBox.setVisible(true);
            feature3TextField.setVisible(true);
//            operation3ComboBox.setVisible(numericKeys.contains(feature3ComboBox.getValue().toString()));
            Object t = feature3ComboBox.getValue();
            if (t != null) tmpString = t.toString(); else tmpString = "";
            operation3ComboBox.setVisible(numericKeys.contains(tmpString));
        } else {
            feature3ComboBox.setVisible(false);
            feature3TextField.setVisible(false);
            operation3ComboBox.setVisible(false);
        }

        if (search4CheckBox.isSelected()) {
            feature4ComboBox.setVisible(true);
            feature4TextField.setVisible(true);
//            operation4ComboBox.setVisible(numericKeys.contains(feature4ComboBox.getValue().toString()));
            Object t = feature4ComboBox.getValue();
            if (t != null) tmpString = t.toString(); else tmpString = "";
            operation4ComboBox.setVisible(numericKeys.contains(tmpString));
        } else {
            feature4ComboBox.setVisible(false);
            feature4TextField.setVisible(false);
            operation4ComboBox.setVisible(false);
        }

    }

    public void featureChosen(ActionEvent event) {
        String tmpString;

//        operation1ComboBox.setVisible(numericKeys.contains(feature1ComboBox.getValue().toString()));
        Object t1 = feature1ComboBox.getValue();
        if (t1 != null) tmpString = t1.toString(); else tmpString = "";
        operation1ComboBox.setVisible(numericKeys.contains(tmpString));

        if (search2CheckBox.isSelected()) {
//           && numericKeys.contains(feature2ComboBox.getValue().toString())) {
        Object t = feature2ComboBox.getValue();
        if (t != null) tmpString = t.toString(); else tmpString = "";
        operation2ComboBox.setVisible(numericKeys.contains(tmpString));
        }
        else {
            operation2ComboBox.setVisible(false);
        }

        if (search3CheckBox.isSelected()) {
            Object t = feature3ComboBox.getValue();
            if (t != null) tmpString = t.toString(); else tmpString = "";
            operation3ComboBox.setVisible(numericKeys.contains(tmpString));
        }
        else {
            operation3ComboBox.setVisible(false);
        }

        if (search4CheckBox.isSelected()) {
            Object t = feature4ComboBox.getValue();
            if (t != null) tmpString = t.toString(); else tmpString = "";
            operation4ComboBox.setVisible(numericKeys.contains(tmpString));
        }
        else {
            operation4ComboBox.setVisible(false);
        }
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void getAdditionalFeatures() throws IOException, ParseException {
        String response = "";
        HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/getFeatures").openConnection();
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
        additionalFeatureComboBox.getItems().clear();
        for (int i = 0; i < array.size(); i++) {
            JSONObject tempObj = (JSONObject) array.get(i);

            String tempFeatureName = tempObj.get("featureName").toString();
            additionalFeatureComboBox.getItems().add(tempFeatureName);
        }
    }

    public Computer parseComputer(JSONObject tempObj) {
        int tempComputerID = Integer.parseInt(tempObj.get("computerID").toString());
        String tempBrand = tempObj.get("brand").toString();
        String tempModel = tempObj.get("model").toString();
        float tempScreenSize = Float.parseFloat(tempObj.get("screenSize").toString());
        String tempScreenResolution = tempObj.get("screenResolution").toString();
        String tempProcessor = tempObj.get("processor").toString();
        int tempMemory = Integer.parseInt(tempObj.get("memory").toString());
        float tempStorageCapacity = Float.parseFloat(tempObj.get("storageCapacity").toString());
        float tempPrice = Float.parseFloat(tempObj.get("price").toString());

        Computer tempComputer;
        tempComputer = new Computer(tempComputerID, tempBrand,tempModel, tempScreenSize, tempScreenResolution, tempProcessor, tempMemory,tempStorageCapacity, tempPrice);
        return tempComputer;
    }

    public Phone parsePhone(JSONObject tempObj) {
        int tempPhoneID = Integer.parseInt(tempObj.get("phoneID").toString());
        String tempBrand = tempObj.get("brand").toString();
        String tempModel = tempObj.get("model").toString();
        float tempScreenSize = Float.parseFloat(tempObj.get("screenSize").toString());
        float tempInternalMemory = Float.parseFloat(tempObj.get("internalMemory").toString());
        float tempPrice = Float.parseFloat(tempObj.get("price").toString());

        Phone tempPhone;
        tempPhone = new Phone(tempPhoneID, tempBrand,tempModel, tempScreenSize, tempInternalMemory, tempPrice);
        return tempPhone;
    }


    public void getAllProductsButtonPressed(ActionEvent event) throws IOException, ParseException {
        if (computerRadioButton.isSelected()) {
            productsListView.getItems().clear();
            computerList.clear();
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
                JSONObject tempObj = (JSONObject) array.get(i);


                Computer tempComputer = parseComputer(tempObj);
                computerList.add(tempComputer);

                tempProduct = tempComputer.getDetails();
                productList.add(tempProduct);
                productsListView.getItems().add(tempProduct);
            }
        }
        else if (phoneRadioButton.isSelected()) {
            productsListView.getItems().clear();
            phoneList.clear();
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
                JSONObject tempObj = (JSONObject) array.get(i);

                Phone tempPhone = parsePhone(tempObj);
                phoneList.add(tempPhone);

                tempProduct = tempPhone.getDetails();
                productList.add(tempProduct);
                productsListView.getItems().add(tempProduct);
            }
        }
    }

    public String createSearchURL() {

        String url = "http://localhost:8080/search";
        if (computerRadioButton.isSelected()) {
            url += "Computers/";
        }
        else if(phoneRadioButton.isSelected()) {
            url += "Phones/";
        }
        else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("You should choose either computer or phone");
            a.show();
            return null;
        }

        int searchCount = 0;
        if(bothFeaturesRadioButton.isSelected()) {

        }
        else if (bothFeaturesRadioButton.isSelected() || additionalFeaturesRadioButton.isSelected()) {
            if (additionalFeatureComboBox.getValue() != null) {
                if (bothFeaturesRadioButton.isSelected()) url += "both/";
                else url += "add/";

                url += additionalFeatureComboBox.getValue().toString();
                searchCount +=1;
            }
        }
        if(bothFeaturesRadioButton.isSelected() || baseFeaturesRadioButton.isSelected()) {
            if (bothFeaturesRadioButton.isSelected() && searchCount > 0) url += "_";
            else url += "base/";

            if (feature1ComboBox.getValue() != null && !feature1TextField.getText().equals("")) {
                String tmpString = feature1ComboBox.getValue().toString();
                if (numericKeys.contains(tmpString)) {
                    if (operation1ComboBox.getValue() != null) {
                        url += String.format("%s=%s=%s", keyParser(tmpString), operationParser(operation1ComboBox.getValue().toString()), feature1TextField.getText());
                        searchCount += 1;
                    } else {
                        System.out.println("searchRow 1 not suitable for searching");
                    }
                } else {
                    url += String.format("%s=%s", keyParser(tmpString), feature1TextField.getText());
                    searchCount += 1;
                }
            }

            if (search2CheckBox.isSelected() && feature2ComboBox.getValue() != null && !feature2TextField.getText().equals("")) {
                String tmpString = feature2ComboBox.getValue().toString();
                if (numericKeys.contains(tmpString)) {
                    if (operation2ComboBox.getValue() != null) {
                        if (searchCount > 0) url += "&";
                        url += String.format("%s=%s=%s", keyParser(tmpString), operationParser(operation2ComboBox.getValue().toString()), feature2TextField.getText());
                        searchCount += 1;
                    } else {
                        System.out.println("searchRow 2 not suitable for searching");
                    }
                } else {
                    if (searchCount > 0) url += "&";
                    url += String.format("%s=%s", keyParser(tmpString), feature2TextField.getText());
                    searchCount += 1;
                }
            }

            if (search3CheckBox.isSelected() && feature3ComboBox.getValue() != null && !feature3TextField.getText().equals("")) {
                String tmpString = feature3ComboBox.getValue().toString();
                if (numericKeys.contains(tmpString)) {
                    if (operation3ComboBox.getValue() != null) {
                        if (searchCount > 0) url += "&";
                        url += String.format("%s=%s=%s", keyParser(tmpString), operationParser(operation3ComboBox.getValue().toString()), feature3TextField.getText());
                        searchCount += 1;
                    } else {
                        System.out.println("searchRow 3 not suitable for searching");
                    }
                } else {
                    if (searchCount > 0) url += "&";
                    url += String.format("%s=%s", keyParser(tmpString), feature3TextField.getText());
                    searchCount += 1;
                }
            }

            if (search4CheckBox.isSelected() && feature4ComboBox.getValue() != null && !feature4TextField.getText().equals("")) {
                String tmpString = feature4ComboBox.getValue().toString();
                if (numericKeys.contains(tmpString)) {
                    if (operation4ComboBox.getValue() != null) {
                        if (searchCount > 0) url += "&";
                        url += String.format("%s=%s=%s", keyParser(tmpString), operationParser(operation4ComboBox.getValue().toString()), feature4TextField.getText());
                        searchCount += 1;
                    } else {
                        System.out.println("searchRow 4 not suitable for searching");
                    }
                } else {
                    if (searchCount > 0) url += "&";
                    url += String.format("%s=%s", keyParser(tmpString), feature4TextField.getText());
                    searchCount += 1;
                }
            }

        }
        else if(additionalFeaturesRadioButton.isSelected()) {

        }
        if (searchCount == 0) return null;
        else return url;
    }


    public void searchProducts(ActionEvent event) throws IOException, ParseException {
        String url = createSearchURL();
        System.out.println(url);
        if (url == null) {
            return;
        }


        productsListView.getItems().clear();
        String response = "";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
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

        if (computerRadioButton.isSelected()) {
            computerList.clear();
            for (int i = 0; i < array.size(); i++) {
                JSONObject tempObj = (JSONObject) array.get(i);

                Computer tempComputer = parseComputer(tempObj);
                computerList.add(tempComputer);

                tempProduct = tempComputer.getDetails();
                productList.add(tempProduct);
                productsListView.getItems().add(tempProduct);
            }
        }
        else if (phoneRadioButton.isSelected()) {
            phoneList.clear();
            for (int i = 0; i < array.size(); i++) {
                JSONObject tempObj = (JSONObject) array.get(i);

                Phone tempPhone = parsePhone(tempObj);
                phoneList.add(tempPhone);

                tempProduct = tempPhone.getDetails();
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
