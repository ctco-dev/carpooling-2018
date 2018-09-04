package lv.ctco.javaschool.app.entity.dto;

public class CarDto {
    private String carModel;
    private String carColor;
    private String carNumber;

    public CarDto(){
    }

    public String getCarModel() { return carModel; }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarNumber() { return carNumber; }

    public void setCarNumber(String carNumber) { this.carNumber = carNumber; }

}
