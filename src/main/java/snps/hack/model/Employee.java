package snps.hack.model;


import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    private Long employee_id;

    private LocalDate joinDate;

    private LocalDate EndDate;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private BUName name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Funds> fundsList;

    public Employee(Long employee_id) {
        this.employee_id = employee_id;
    }

    public Employee(Long employee_id, LocalDate joinDate, LocalDate endDate, BUName name, List<Funds> fundsList) {
        this.employee_id = employee_id;
        this.joinDate = joinDate;
        EndDate = endDate;
        this.name = name;
        this.fundsList = fundsList;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public LocalDate getEndDate() {
        return EndDate;
    }

    public void setEndDate(LocalDate endDate) {
        EndDate = endDate;
    }

    public BUName getName() {
        return name;
    }

    public void setName(BUName name) {
        this.name = name;
    }

    public List<Funds> getFundsList() {
        return fundsList;
    }

    public void setFundsList(List<Funds> fundsList) {
        this.fundsList = fundsList;
    }
}