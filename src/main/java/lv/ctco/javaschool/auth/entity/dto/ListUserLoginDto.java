package lv.ctco.javaschool.auth.entity.dto;

import java.util.List;

public class ListUserLoginDto {

    List<UserLoginDto> passengers;

    public List<UserLoginDto> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<UserLoginDto> passengers) {
        this.passengers = passengers;
    }
}
