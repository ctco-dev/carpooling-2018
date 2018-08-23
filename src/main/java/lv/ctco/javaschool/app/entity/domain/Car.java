package lv.ctco.javaschool.app.entity.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Car {
    @Id
    @GeneratedValue
    private Long id;

    private String carModel;
    private String carColor;
    private String carNumber;

    public String getCarModel() { return carModel; }

    public void setCarModel(String carModel) { this.carModel = carModel; }

    public String getCarColor() { return carColor; }

    public void setCarColor(String carColor) { this.carColor = carColor; }

    public String getCarNumber() { return carNumber; }

    public void setCarNumber(String carNumber) { this.carNumber = carNumber; }
}
