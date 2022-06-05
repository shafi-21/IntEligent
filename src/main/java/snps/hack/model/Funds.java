package snps.hack.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Table(name = "buddy")
public class Funds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fundId;

    private Double compoundInterest;

    private Double simpleInterest;

    private int year;

    @ManyToOne
    private Employee employee;

    public Funds(Long fundId, Double compoundInterest, Double simpleInterest, int year, Employee employee) {
        this.fundId = fundId;
        this.compoundInterest = compoundInterest;
        this.simpleInterest = simpleInterest;
        this.year = year;
        this.employee = employee;
    }

    public Long getFundId() {
        return fundId;
    }

    public void setFundId(Long fundId) {
        this.fundId = fundId;
    }

    public Double getCompoundInterest() {
        return compoundInterest;
    }

    public void setCompoundInterest(Double compoundInterest) {
        this.compoundInterest = compoundInterest;
    }

    public Double getSimpleInterest() {
        return simpleInterest;
    }

    public void setSimpleInterest(Double simpleInterest) {
        this.simpleInterest = simpleInterest;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}

