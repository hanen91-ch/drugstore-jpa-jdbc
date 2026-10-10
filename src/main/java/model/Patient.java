package model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends User{

    @Column(name = "weight")
    private int weight;

    @Column(name = "age")
    private int age;

    @Column(name = "allergies")
    private String allergies;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // Relation vers Prescription (1 patient → * prescriptions) -- Relation uni-directionnel
    private List<Prescription> prescriptions;

    public Patient() {}

    // Constructeur sans id
    public Patient(String name, String tel, int weight, int age, String allergies) {
        super(name, tel); // appel du constructeur
        this.weight = weight;
        this.age = age;
        this.allergies = allergies;
        this.prescriptions = new ArrayList<>();
    }

    // Constructeur avec id -- lecture depuis BD
    public Patient(int id, String name, String tel, int weight, int age, String allergies, List<Prescription> prescriptions) {
        super(id, name, tel);
        this.weight = weight;
        this.age = age;
        this.allergies = allergies;
        this.prescriptions = new ArrayList<>();
    }
    // Getters et Setters
    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getAllergies() { return allergies; }
    public void setAllergies(String allergies) { this.allergies = allergies; }
    public List<Prescription> getPrescriptions() { return prescriptions; }
    public void setPrescriptions(List<Prescription> prescriptions) { this.prescriptions = prescriptions; }

    @Override
    public String toString()
    {
        return String.format(
                "Patient{id=%d, name='%s', tel='%s', " +
                        "weight=%d kg, age=%d, allergies='%s'}",
                getId(), getName(), getTel(), weight, age, allergies
        );
    }
}
