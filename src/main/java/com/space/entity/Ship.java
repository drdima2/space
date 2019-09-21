package com.space.entity;

import com.space.model.ShipType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "ship")
public class Ship implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;               //ID корабля


    @org.hibernate.validator.constraints.NotBlank
    @Length(max = 50)
    @Column(nullable = false, length = 50)
    private String name;           //Название корабля (до 50 знаков включительно)


    @org.hibernate.validator.constraints.NotBlank
    @Length(max = 50)
    @Column(nullable = false, length = 50)
    private String planet;         //Планета пребывания (до 50 знаков включительно)


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShipType shipType;     //Тип корабля


    private Date prodDate;          //Дата выпуска. Диапазон значений года 2800..3019 включительно


    private Boolean isUsed;        //Использованный / новый


    @NotNull(message = "speed must be 0.01 to 0.99")
    @Column(nullable = false)
    @DecimalMin("0.01")
    @DecimalMax("0.99")
    private Double speed;          //Максимальная скорость корабля. Диапазон значений 0,01..0,99 включительно. Используй математическое округление до сотых


    @NotNull(message = "crew size must be 1 to 9999")
    @Range(min = 1, max = 9999)
    @Column(nullable = false)
    private Integer crewSize;      // Количество членов экипажа. Диапазон значений 1..9999 включительно


    private Double rating;         // Рейтинг корабля. Используй математическое округление до сотых.


    public Ship() {
    }

    public Ship(Long id, String name, String planet, ShipType shipType) {
        this.id = id;
        this.name = name;
        this.planet = planet;

    }

    public Ship(Long id, String name, String planet, ShipType shipType, Date prodDate, Boolean isUsed, Double speed, Integer crewSize, Double rating) {
        this.id = id;
        this.name = name;
        this.planet = planet;
    }


    public boolean isShipContainRequiredParameters() {
        if (isEmpty()) return false;
        if ((getName() == null) || getName().trim().isEmpty() || getName().trim().length() > 50 ||
                (getPlanet() == null) || getPlanet().trim().isEmpty() || getPlanet().trim().length() > 50 ||
                (getShipType() == null) ||
                getYear() < 2800 ||
                getYear() > 3019 ||
                (getUsed() == null) ||
                (getSpeed() == null) || getSpeed() < 0.01 || getSpeed()>0.99 ||
                (getCrewSize() == null) || getCrewSize() < 1 || getCrewSize() > 9999
                ) {
            return false;
        }
        return true;
    }


    public boolean isEmpty() {
        if ((getName() == null) &&
                (getPlanet() == null) &&
                (getShipType() == null) &&
                getProdDate() == null &&
                (getSpeed() == null) &&
                (getCrewSize() == null)
                ) return true;
        return false;
    }


    public BigDecimal calculateRating() {
        BigDecimal currentYear = BigDecimal.valueOf(3019);
        BigDecimal rating = BigDecimal.ZERO;
        BigDecimal koofitzent = getUsed() ? BigDecimal.valueOf(0.5) : BigDecimal.valueOf(1);
        BigDecimal speed = BigDecimal.valueOf(getSpeed());
        BigDecimal yearDiff = currentYear.subtract(BigDecimal.valueOf(getYear())).add(BigDecimal.ONE);
        BigDecimal firstPart = BigDecimal.valueOf(80).multiply(speed.multiply(koofitzent));
        rating = firstPart.divide(yearDiff, 2, BigDecimal.ROUND_HALF_UP);
        return rating;
    }


    public int getYear() {
        if (getProdDate() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(getProdDate());
            return calendar.get(Calendar.YEAR);
        }
        return 0;

    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ship{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", planet='").append(planet).append('\'');
        sb.append(", shipType=").append(shipType);
        sb.append(", prodDate=").append(prodDate);
        sb.append(", isUsed=").append(isUsed);
        sb.append(", speed=").append(speed);
        sb.append(", crewSize=").append(crewSize);
        sb.append(", rating=").append(rating);
        sb.append('}');
        return sb.toString();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}