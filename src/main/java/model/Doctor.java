package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("DOCTOR")
public class Doctor extends User {

    @Column(name = "skill")
    private String skill;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // Relation bidirectionnelle : un doctor rédige plusieurs prescriptions
    private List<Prescription> prescriptions;

    public Doctor() {}

    // Constructeur sans id
    public Doctor(String name, String tel, String skill) {
        super(name, tel);
        this.skill = skill;
        this.prescriptions = new ArrayList<>();
    }

    // Constructeur avec id
    public Doctor(int id, String name, String tel, String skill, List<Prescription> prescriptions) {
        super(id, name, tel);
        this.skill = skill;
        this.prescriptions = new ArrayList<>();
    }

    // Getters et Setters
    public String getSkill() { return skill; }
    public void setSkill(String skill) { this.skill = skill; }
    public List<Prescription> getPrescriptions() { return prescriptions; }
    public void setPrescriptions(List<Prescription> prescriptions) { this.prescriptions = prescriptions; }

    @Override
    public String toString() {
        return String.format(
                "Doctor{id=%d, name='%s', tel='%s', skill='%s'}",
                getId(), getName(), getTel(), skill
        );
    }
}
