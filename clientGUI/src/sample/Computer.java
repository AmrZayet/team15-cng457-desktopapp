package sample;

import java.util.ArrayList;
import java.util.List;

public class Computer {

    private int computerID;
    private String brand;
    private String model;
    private float screenSize;
    private String screenResolution;
    private String processor;
    private int memory;
    private float storageCapacity;
    private float price;

    private List<Review> reviews;

    public Computer(int computerID, String brand, String model, float screenSize, String screenResolution, String processor, int memory, float storageCapacity, float price) {
        this.computerID = computerID;
        this.brand = brand;
        this.model = model;
        this.screenSize = screenSize;
        this.screenResolution = screenResolution;
        this.processor = processor;
        this.memory = memory;
        this.storageCapacity = storageCapacity;
        this.price = price;
    }

    public int getComputerID() {
        return computerID;
    }

    public void setComputerID(int computerID) {
        this.computerID = computerID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(float screenSize) {
        this.screenSize = screenSize;
    }

    public String getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(String screenResolution) {
        this.screenResolution = screenResolution;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public float getStorageCapacity() {
        return storageCapacity;
    }

    public void setStorageCapacity(float storageCapacity) {
        this.storageCapacity = storageCapacity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getDetails() {
        String tempString = String.format("%s %s ", brand, model);
        if (memory > 16) {
            tempString += "Large Memory ";
        }
        if (storageCapacity > 1024) {
            tempString += "Large Storage ";
        }
        tempString += String.format(" -> ($%.2f)", price);
        return tempString;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "computerID=" + computerID +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", screenSize=" + screenSize +
                ", screenResolution='" + screenResolution + '\'' +
                ", processor='" + processor + '\'' +
                ", memory=" + memory +
                ", storageCapacity=" + storageCapacity +
                ", price=" + price +
                ", reviews=" + reviews +
                '}';
    }
}