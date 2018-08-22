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
    private int carNumber;
}
