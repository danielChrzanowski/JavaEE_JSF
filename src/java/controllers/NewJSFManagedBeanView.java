package controllers;

import data.Car;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

@ManagedBean
@ApplicationScoped
public class NewJSFManagedBeanView {

    java.util.ArrayList<Car> carList = new java.util.ArrayList<>();

    public NewJSFManagedBeanView() {
        carList.add(new Car(1l, "Ford", "Fiesta", 25, false));
        carList.add(new Car(2l, "Opel", "Insignia", 28, false));
        carList.add(new Car(3l, "Renault", "Clio", 26, false));
    }

    public ArrayList<Car> getCarList() {
        return carList;
    }

    public void setCarList(ArrayList<Car> carList) {
        this.carList = carList;
    }

    public String addCarRow(Car car) {
        int indexObject = carList.indexOf(car);
        long nextId = carList.size() + 1;
        Car newCarTO = new Car(nextId, "", "", true);
        carList.add(indexObject + 1, newCarTO);
        return "";
    }

    public String delCarRow(Car car) {
        int indexObject = carList.indexOf(car);
        carList.remove(indexObject);
        return "";
    }

    public String modifyCarRow(Car car) {
        int indexObject = carList.indexOf(car);
        carList.get(indexObject).setEdited(true);
        return "";
    }

    public String saveCarData() {
        carList.forEach((car) -> {
            car.setEdited(false);
        });
        return "";
    }

    public String refreshCarData() {
        carList.clear();
        carList.add(new Car(1l, "Ford", "Fiesta", 25, false));
        carList.add(new Car(2l, "Opel", "Insignia", 28, false));
        carList.add(new Car(3l, "Renault", "Clio", 26, false));

        carList.forEach((car) -> {
            car.setEdited(false);
        });
        return "";
    }
}
