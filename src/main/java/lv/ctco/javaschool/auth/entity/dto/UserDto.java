package lv.ctco.javaschool.auth.entity.dto;

import java.util.Objects;

public class UserDto {

    private String name;
    private String surname;
    private String phoneNumber;
    private String carModel;
    private String carColor;
    private String carNumber;

    public UserDto() {
    }

    public UserDto(String name, String surname, String phoneNumber, String carModel, String carColor, String carNumber) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.carModel = carModel;
        this.carColor = carColor;
        this.carNumber = carNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(name, userDto.name) &&
                Objects.equals(surname, userDto.surname) &&
                Objects.equals(phoneNumber, userDto.phoneNumber) &&
                Objects.equals(carModel, userDto.carModel) &&
                Objects.equals(carColor, userDto.carColor) &&
                Objects.equals(carNumber, userDto.carNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, surname, phoneNumber, carModel, carColor, carNumber);
    }
}
