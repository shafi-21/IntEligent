package snps.hack.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "bu")
public class BU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private BUName name;

    public BU() {}

//    public Role(RoleName name) {
//        this.name = name;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BUName getName() {
        return name;
    }

    public void setName(BUName name) {
        this.name = name;
    }
}