package data;

public class Car {

    Long id;
    String brand, model;
    int wheelSize;
    boolean edited;

    public Car(Long id, String brand, String model, int wheelSize, boolean edited) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.wheelSize = wheelSize;
        this.edited = edited;
    }

    public Car(Long id, String brand, String model, boolean edited) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.edited = edited;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getWheelSize() {
        return wheelSize;
    }

    public void setWheelSize(int wheelSize) {
        this.wheelSize = wheelSize;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

}
