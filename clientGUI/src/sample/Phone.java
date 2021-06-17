package sample;

public class Phone {

    private int phoneID;
    private String brand;
    private String model;
    private float screenSize;
    private float internalMemory;
    private float price;

    public Phone(int phoneID, String brand, String model, float screenSize, float internalMemory, float price) {
        this.phoneID = phoneID;
        this.brand = brand;
        this.model = model;
        this.screenSize = screenSize;
        this.internalMemory = internalMemory;
        this.price = price;
    }

    public int getPhoneID() {
        return phoneID;
    }

    public void setPhoneID(int phoneID) {
        this.phoneID = phoneID;
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

    public float getInternalMemory() {
        return internalMemory;
    }

    public void setInternalMemory(float internalMemory) {
        this.internalMemory = internalMemory;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDetails() {
        String tempString = String.format("%s %s ", brand, model);
        if (screenSize > 6) {
            tempString += "Large Memory ";
        }
        if (internalMemory > 128) {
            tempString += "Large Storage ";
        }
        return tempString;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phoneID=" + phoneID +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", screenSize=" + screenSize +
                ", internalMemory=" + internalMemory +
                ", price=" + price +
                '}';
    }
}