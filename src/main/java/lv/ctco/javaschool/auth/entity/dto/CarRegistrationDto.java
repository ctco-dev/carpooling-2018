package lv.ctco.javaschool.auth.entity.dto;


public class CarRegistrationDto {
    private CarDto car;
    private UserLoginDto userLogin;

    public CarDto getCar() { return car; }

    public void setCar(CarDto car) { this.car = car; }

    public UserLoginDto getUserLogin() { return userLogin; }

    public void setUserLogin(UserLoginDto userLogin) { this.userLogin = userLogin; }
}
