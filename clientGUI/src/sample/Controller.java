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
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {

    private int numberOfDevice=0;

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

    @FXML
    private ListView<String> ComparisionListView;

    private List<String> productList = new ArrayList<>();

    private List<String> computerSearchKeys = Arrays.asList("computer ID", "brand", "model", "screen size", "resolution", "processor", "memory", "storage", "price");
    private List<String> phoneSearchKeys = Arrays.asList("phone ID", "brand", "model", "screen size", "internal memory", "price");
    private List<String> numericKeys = Arrays.asList("screen size", "resolution", "processor", "memory", "internal memory", "storage", "price");


    private List<Computer> computerList = new ArrayList<>();
    private List<Phone> phoneList = new ArrayList<>();

    // private int indexArray[]= {-1,-1,-1};
    //private String deviceType="notDefined";

    @FXML
    private ListView<String>  comparision1ClassListView;

    @FXML
    private ListView<String>  comparision1Device1ListReview;

    @FXML
    private ListView<String>   comparision1Device2ListReview;

    @FXML
    private ListView<String>   comparision1Device3ListReview;



    @FXML
    private Label baseSearchLabel;
    @FXML
    private Label additionalSearchLabel;

    @FXML
    private Label comparision2Name1Label;
    @FXML
    private Label comparision2Name2Label;
    @FXML
    private Label comparision2Name3Label;


    @FXML
    private Label comparision2Rating1Label;
    @FXML
    private Label comparision2Rating2Label;
    @FXML
    private Label comparision2Rating3Label;


    @FXML
    private ListView<String>   comparision2Reviews1ListView;
    @FXML
    private ListView<String>   comparision2Reviews2ListView;
    @FXML
    private ListView<String>   comparision2Reviews3ListView;



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

        comparision1Device1ListReview.setVisible(false);
        comparision1Device2ListReview.setVisible(false);
        comparision1Device3ListReview.setVisible(false);

        comparision2Rating1Label.setVisible(false);
        comparision2Rating2Label.setVisible(false);
        comparision2Rating3Label.setVisible(false);

        comparision2Reviews1ListView.setVisible(false);
        comparision2Reviews2ListView.setVisible(false);
        comparision2Reviews3ListView.setVisible(false);





    }


    public void typeRadioButtonSelected(ActionEvent event) {

        numberOfDevice=0;
        productsListView.getItems().clear();
        computerList.clear();
        phoneList.clear();

        feature1ComboBox.getItems().clear();
        feature2ComboBox.getItems().clear();
        feature3ComboBox.getItems().clear();
        feature4ComboBox.getItems().clear();

        numberOfDevice=0;
        productsListView.getItems().clear();
        comparision1Device1ListReview.getItems().clear();
        comparision1Device2ListReview.getItems().clear();
        comparision1Device3ListReview.getItems().clear();
        comparision1Device1ListReview.setVisible(false);
        comparision1Device2ListReview.setVisible(false);
        comparision1Device3ListReview.setVisible(false);

        comparision2Name1Label.setText("Device 1");
        comparision2Name2Label.setText("Device 2");
        comparision2Name3Label.setText("Device 3");
        comparision2Rating1Label.setVisible(false);
        comparision2Rating2Label.setVisible(false);
        comparision2Rating3Label.setVisible(false);
        comparision2Reviews1ListView.getItems().clear();
        comparision2Reviews2ListView.getItems().clear();
        comparision2Reviews3ListView.getItems().clear();
        comparision2Reviews1ListView.setVisible(false);
        comparision2Reviews2ListView.setVisible(false);
        comparision2Reviews3ListView.setVisible(false);


        if (computerRadioButton.isSelected()) {
            feature1ComboBox.getItems().addAll(computerSearchKeys);
            feature2ComboBox.getItems().addAll(computerSearchKeys);
            feature3ComboBox.getItems().addAll(computerSearchKeys);
            feature4ComboBox.getItems().addAll(computerSearchKeys);
            System.out.println(1);
            comparision1ClassListView.getItems().clear();
            comparision1ClassListView.getItems().add("Device No");
            for(String a:computerSearchKeys){
                comparision1ClassListView.getItems().add(a);
            }
        }
        else if (phoneRadioButton.isSelected()) {
            feature1ComboBox.getItems().addAll(phoneSearchKeys);
            feature2ComboBox.getItems().addAll(phoneSearchKeys);
            feature3ComboBox.getItems().addAll(phoneSearchKeys);
            feature4ComboBox.getItems().addAll(phoneSearchKeys);
            System.out.println(2);

            comparision1ClassListView.getItems().clear();
            comparision1ClassListView.getItems().add("Device No");
            for(String a:phoneSearchKeys){
                comparision1ClassListView.getItems().add(a);
            }
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

    public List<Review> parseReviews(String tempReviewsString) throws ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(tempReviewsString);
        JSONArray reviewsArray = (JSONArray) obj;

        List<Review> tempReviews = new ArrayList<>();

        for (int i = 0; i < reviewsArray.size(); i++) {
            JSONObject tempReviewObj = (JSONObject) reviewsArray.get(i);

            int tempRevID = Integer.parseInt(tempReviewObj.get("revID").toString());
            String tempComment = tempReviewObj.get("comment").toString();
            int tempRate = Integer.parseInt(tempReviewObj.get("rate").toString());
            tempReviews.add(new Review(tempRevID,tempComment, tempRate));
//            productsListView.getItems().add(tempReviews.get(i).toString());
        }
        return tempReviews;
    }

    public Computer parseComputer(JSONObject tempObj) throws ParseException {
        int tempComputerID = Integer.parseInt(tempObj.get("computerID").toString());
        String tempBrand = tempObj.get("brand").toString();
        String tempModel = tempObj.get("model").toString();
        float tempScreenSize = Float.parseFloat(tempObj.get("screenSize").toString());
        String tempScreenResolution = tempObj.get("screenResolution").toString();
        String tempProcessor = tempObj.get("processor").toString();
        int tempMemory = Integer.parseInt(tempObj.get("memory").toString());
        float tempStorageCapacity = Float.parseFloat(tempObj.get("storageCapacity").toString());
        float tempPrice = Float.parseFloat(tempObj.get("price").toString());

        String tempReviewsString = tempObj.get("reviews").toString();
        List<Review> tempReviews = parseReviews(tempReviewsString);

        Computer tempComputer;
        tempComputer = new Computer(tempComputerID, tempBrand,tempModel, tempScreenSize, tempScreenResolution, tempProcessor, tempMemory,tempStorageCapacity, tempPrice);
        tempComputer.setReviews(tempReviews);
        return tempComputer;
    }

    public Phone parsePhone(JSONObject tempObj) throws ParseException {
        int tempPhoneID = Integer.parseInt(tempObj.get("phoneID").toString());
        String tempBrand = tempObj.get("brand").toString();
        String tempModel = tempObj.get("model").toString();
        float tempScreenSize = Float.parseFloat(tempObj.get("screenSize").toString());
        float tempInternalMemory = Float.parseFloat(tempObj.get("internalMemory").toString());
        float tempPrice = Float.parseFloat(tempObj.get("price").toString());

        String tempReviewsString = tempObj.get("reviews").toString();
        List<Review> tempReviews = parseReviews(tempReviewsString);

        Phone tempPhone;
        tempPhone = new Phone(tempPhoneID, tempBrand,tempModel, tempScreenSize, tempInternalMemory, tempPrice);
        tempPhone.setReviews(tempReviews);
        return tempPhone;
    }


    public void getAllProductsButtonPressed(ActionEvent event) throws IOException, ParseException {

        if (!computerRadioButton.isSelected() && !phoneRadioButton.isSelected()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("You should choose either computer or phone");
            a.show();
            return;
        }

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

        String tempBaseFeaturesUrl = "";
        String tempAdditionalFeaturesUrl = "";

        if (bothFeaturesRadioButton.isSelected() || additionalFeaturesRadioButton.isSelected()) {
            if (additionalFeatureComboBox.getValue() != null) {

                tempAdditionalFeaturesUrl += additionalFeatureComboBox.getValue().toString().replace(" ", "-");
            }
        }
        if(bothFeaturesRadioButton.isSelected() || baseFeaturesRadioButton.isSelected()) {
            if (feature1ComboBox.getValue() != null && !feature1TextField.getText().equals("")) {
                String tmpString = feature1ComboBox.getValue().toString();
                if (numericKeys.contains(tmpString)) {
                    if (operation1ComboBox.getValue() != null) {
                        tempBaseFeaturesUrl += String.format("%s=%s=%s", keyParser(tmpString), operationParser(operation1ComboBox.getValue().toString()), feature1TextField.getText());
                    } else {
                        System.out.println("searchRow 1 not suitable for searching");
                    }
                } else {
                    tempBaseFeaturesUrl += String.format("%s=%s", keyParser(tmpString), feature1TextField.getText());
                }
            }

            if (search2CheckBox.isSelected() && feature2ComboBox.getValue() != null && !feature2TextField.getText().equals("")) {
                String tmpString = feature2ComboBox.getValue().toString();
                if (numericKeys.contains(tmpString)) {
                    if (operation2ComboBox.getValue() != null) {
                        if (!tempBaseFeaturesUrl.equals("")) tempBaseFeaturesUrl += "&";
                        tempBaseFeaturesUrl += String.format("%s=%s=%s", keyParser(tmpString), operationParser(operation2ComboBox.getValue().toString()), feature2TextField.getText());
                    } else {
                        System.out.println("searchRow 2 not suitable for searching");
                    }
                } else {
                    if (!tempBaseFeaturesUrl.equals("")) tempBaseFeaturesUrl += "&";
                    tempBaseFeaturesUrl += String.format("%s=%s", keyParser(tmpString), feature2TextField.getText());
                }
            }

            if (search3CheckBox.isSelected() && feature3ComboBox.getValue() != null && !feature3TextField.getText().equals("")) {
                String tmpString = feature3ComboBox.getValue().toString();
                if (numericKeys.contains(tmpString)) {
                    if (operation3ComboBox.getValue() != null) {
                        if (!tempBaseFeaturesUrl.equals("")) tempBaseFeaturesUrl += "&";
                        tempBaseFeaturesUrl += String.format("%s=%s=%s", keyParser(tmpString), operationParser(operation3ComboBox.getValue().toString()), feature3TextField.getText());
                    } else {
                        System.out.println("searchRow 3 not suitable for searching");
                    }
                } else {
                    if (!tempBaseFeaturesUrl.equals("")) tempBaseFeaturesUrl += "&";
                    tempBaseFeaturesUrl += String.format("%s=%s", keyParser(tmpString), feature3TextField.getText());
                }
            }

            if (search4CheckBox.isSelected() && feature4ComboBox.getValue() != null && !feature4TextField.getText().equals("")) {
                String tmpString = feature4ComboBox.getValue().toString();
                if (numericKeys.contains(tmpString)) {
                    if (operation4ComboBox.getValue() != null) {
                        if (!tempBaseFeaturesUrl.equals("")) tempBaseFeaturesUrl += "&";
                        tempBaseFeaturesUrl += String.format("%s=%s=%s", keyParser(tmpString), operationParser(operation4ComboBox.getValue().toString()), feature4TextField.getText());
                    } else {
                        System.out.println("searchRow 4 not suitable for searching");
                    }
                } else {
                    if (!tempBaseFeaturesUrl.equals("")) tempBaseFeaturesUrl += "&";
                    tempBaseFeaturesUrl += String.format("%s=%s", keyParser(tmpString), feature4TextField.getText());
                }
            }
        }

        if (tempAdditionalFeaturesUrl.equals("") && tempBaseFeaturesUrl.equals("")) {
            url = null;
        }
        else if (!tempAdditionalFeaturesUrl.equals("") && !tempBaseFeaturesUrl.equals("")) {
            url += "both/";
            url += tempAdditionalFeaturesUrl;
            url += "__";
            url += tempBaseFeaturesUrl;
        }
        else if (!tempBaseFeaturesUrl.equals("")) {
            url += "base/";
            url += tempBaseFeaturesUrl;
        }
        else if (!tempAdditionalFeaturesUrl.equals("")) {
            url += "add/";
            url += tempAdditionalFeaturesUrl;
        }

        return url;
    }


    public void searchProducts(ActionEvent event) throws IOException, ParseException {

        if (!computerRadioButton.isSelected() && !phoneRadioButton.isSelected()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("You should choose either computer or phone");
            a.show();
            return;
        }
        else if (!baseFeaturesRadioButton.isSelected() && !additionalFeaturesRadioButton.isSelected() && !bothFeaturesRadioButton.isSelected()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("You should choose the search criteria first!");
            a.show();
            return;
        }

        String url = createSearchURL();
        if (url == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("You should select a correct search criteria");
            a.show();
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

        if (!computerRadioButton.isSelected() && !phoneRadioButton.isSelected()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("You should choose either computer or phone");
            a.show();
            return;
        }
        else if (productList.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("You can not sort an empty list");
            a.show();
            return;
        }

        productList.clear();
        productsListView.getItems().clear();

        if (computerRadioButton.isSelected()) {
            Collections.sort(computerList, Comparator.comparingDouble(Computer ::getPrice));
            Collections.reverse(computerList);
            for(Computer tempComputer: computerList) {
                productList.add(tempComputer.getDetails());
                productsListView.getItems().add(tempComputer.getDetails());
            }
        }
        else if (phoneRadioButton.isSelected()) {
            Collections.sort(phoneList, Comparator.comparingDouble(Phone ::getPrice));
            Collections.reverse(phoneList);
            for(Phone tempPhone: phoneList) {
                productList.add(tempPhone.getDetails());
                productsListView.getItems().add(tempPhone.getDetails());
            }
        }


    }
    public void clearComparisionButtonPressed(ActionEvent event) {
        numberOfDevice=0;
        comparision1Device1ListReview.getItems().clear();
        comparision1Device2ListReview.getItems().clear();
        comparision1Device3ListReview.getItems().clear();
        comparision2Name1Label.setText("Device 1");
        comparision2Name2Label.setText("Device 2");
        comparision2Name3Label.setText("Device 3");

        comparision2Reviews1ListView.getItems().clear();
        comparision2Reviews2ListView.getItems().clear();
        comparision2Reviews3ListView.getItems().clear();

        comparision1Device1ListReview.setVisible(false);
        comparision1Device2ListReview.setVisible(false);
        comparision1Device3ListReview.setVisible(false);

        comparision2Rating1Label.setVisible(false);
        comparision2Rating2Label.setVisible(false);
        comparision2Rating3Label.setVisible(false);

        comparision2Reviews1ListView.setVisible(false);
        comparision2Reviews2ListView.setVisible(false);
        comparision2Reviews3ListView.setVisible(false);


    }

    private class addCompareDetails implements Runnable {
        private Product tempProduct;

        public addCompareDetails(Product tempProduct) {
            this.tempProduct = tempProduct;
        }

        @Override
        public void run() {
            if(numberOfDevice == 0) {
                comparision1Device1ListReview.setVisible(true);

                comparision1Device1ListReview.getItems().clear();
                comparision1Device1ListReview.getItems().add("1");
                comparision1Device1ListReview.getItems().addAll(tempProduct.getElementsList());
            }
            else if (numberOfDevice == 1) {
                comparision1Device2ListReview.setVisible(true);

                comparision1Device2ListReview.getItems().clear();
                comparision1Device2ListReview.getItems().add("2");
                comparision1Device2ListReview.getItems().addAll(tempProduct.getElementsList());
            }
            else if (numberOfDevice == 2) {
                comparision1Device3ListReview.setVisible(true);

                comparision1Device3ListReview.getItems().clear();
                comparision1Device3ListReview.getItems().add("3");
                comparision1Device3ListReview.getItems().addAll(tempProduct.getElementsList());
            }
        }
    }

    private class addCompareReviews implements Runnable {
        private Product tempProduct;

        public addCompareReviews(Product tempProduct) {
            this.tempProduct = tempProduct;
        }

        @Override
        public void run() {

            int totalRating = 0;
            float avgRating = 0;

            List<Review> tempProductReviewsList = tempProduct.getLastReviews(3);
            List<String> tempReviewsString = new ArrayList<>();

            for(Review x:tempProductReviewsList){
                totalRating += x.getRate();
                tempReviewsString.add(x.toString());
            }
            avgRating = (float) totalRating / tempProductReviewsList.size();

            if(numberOfDevice == 0) {
                comparision2Rating1Label.setVisible(true);
                comparision2Reviews1ListView.setVisible(true);

                comparision2Name1Label.setText(tempProduct.getModel());
                comparision2Reviews1ListView.getItems().addAll(tempReviewsString);
                comparision2Rating1Label.setText(String.format("%.2f", avgRating));
            }
            else if (numberOfDevice == 1) {
                comparision2Rating2Label.setVisible(true);
                comparision2Reviews2ListView.setVisible(true);

                comparision2Name2Label.setText(tempProduct.getModel());
                comparision2Reviews2ListView.getItems().addAll(tempProduct.getElementsList());
                comparision2Rating2Label.setText(String.format("%.2f", avgRating));
            }
            else if (numberOfDevice == 2) {
                comparision2Rating3Label.setVisible(true);
                comparision2Reviews3ListView.setVisible(true);

                comparision2Name3Label.setText(tempProduct.getModel());
                comparision2Reviews3ListView.getItems().addAll(tempProduct.getElementsList());
                comparision2Rating3Label.setText(String.format("%.2f", avgRating));
            }
        }
    }

    public void addintocompareButtonPressed(ActionEvent event) {

        if (numberOfDevice == 3) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("You have already added 3 devices. Press Clear Comparison!");
            a.show();
            return;
        }
        else if (!computerRadioButton.isSelected() && !phoneRadioButton.isSelected()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("You should choose either computer or phone");
            a.show();
            return;
        }
        else if (productList.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("There is no device to add to comparison.");
            a.show();
            return;
        }

        String tempSelectedString = productsListView.getSelectionModel().getSelectedItem();
        Product tempProduct = null;

        if(computerRadioButton.isSelected()) {
            for (int i = 0; i < computerList.size(); i++) {
                if (computerList.get(i).getDetails().equals(tempSelectedString)) {
                    tempProduct = computerList.get(i);
                    break;
                }
            }
        }
        else if (phoneRadioButton.isSelected()) {
            for (int i = 0; i < phoneList.size(); i++) {
                if (phoneList.get(i).getDetails().equals(tempSelectedString)) {
                    tempProduct = phoneList.get(i);
                    break;
                }
            }
        }


        if (tempProduct == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error!");
            a.setContentText("You should select a device to be added!");
            a.show();
            return;
        }

        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(new addCompareReviews(tempProduct));
        executor.shutdown();

        while (! executor.isTerminated()) {
        }
        numberOfDevice++;
        return;

//        int totalRating = 0;
//        float avgRating = 0;
//
//        List<Review> tempProductReviewsList = tempProduct.getLastReviews(3);
//        List<String> tempReviewsString = new ArrayList<>();
//
//        for(Review x:tempProductReviewsList){
//            totalRating += x.getRate();
//            tempReviewsString.add(x.toString());
//        }
//        avgRating = (float) totalRating / tempProductReviewsList.size();
//
//
//        if(numberOfDevice == 0) {
//            comparision2Rating1Label.setVisible(true);
//            comparision2Reviews1ListView.setVisible(true);
//            comparision1Device1ListReview.setVisible(true);
//
//            comparision1Device1ListReview.getItems().clear();
//            comparision1Device1ListReview.getItems().add("1");
//            comparision1Device1ListReview.getItems().addAll(tempProduct.getElementsList());
//
//            comparision2Name1Label.setText(tempProduct.getModel());
//            comparision2Reviews1ListView.getItems().addAll(tempReviewsString);
//            comparision2Rating1Label.setText(String.format("%.2f", avgRating));
//        }
//        else if (numberOfDevice == 1) {
//            comparision2Rating2Label.setVisible(true);
//            comparision2Reviews2ListView.setVisible(true);
//            comparision1Device2ListReview.setVisible(true);
//
//            comparision1Device2ListReview.getItems().clear();
//            comparision1Device2ListReview.getItems().add("2");
//            comparision1Device2ListReview.getItems().addAll(tempProduct.getElementsList());
//
//            comparision2Name2Label.setText(tempProduct.getModel());
//            comparision2Reviews2ListView.getItems().addAll(tempProduct.getElementsList());
//            comparision2Rating2Label.setText(String.format("%.2f", avgRating));
//        }
//        else if (numberOfDevice == 2) {
//            comparision2Rating3Label.setVisible(true);
//            comparision2Reviews3ListView.setVisible(true);
//            comparision1Device3ListReview.setVisible(true);
//
//            comparision1Device3ListReview.getItems().clear();
//            comparision1Device3ListReview.getItems().add("3");
//            comparision1Device3ListReview.getItems().addAll(tempProduct.getElementsList());
//
//            comparision2Name3Label.setText(tempProduct.getModel());
//            comparision2Reviews3ListView.getItems().addAll(tempProduct.getElementsList());
//            comparision2Rating3Label.setText(String.format("%.2f", avgRating));
//        }
//        numberOfDevice++;
    }
}
