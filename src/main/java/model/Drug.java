package model;

import jakarta.persistence.*;

@Entity
@Table(name = "Drug")

public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "standard_dose_per_kg")
    private double standardDosePerkg;

    @Column(name = "interaction_drug")
    private String interactionDrug;

    @Column(name = "interaction_factor")
    private double interactionFactor;

    public Drug() {}

    public Drug(String name, double standardDosePerkg, String interactionDrug, double interactionFactor) {
        this.name = name;
        this.standardDosePerkg = standardDosePerkg;
        this.interactionDrug = interactionDrug;
        this.interactionFactor = interactionFactor;
    }

    public Drug(int id, String name, double standardDosePerkg, String interactionDrug, double interactionFactor) {
        this.id = id;
        this.name = name;
        this.standardDosePerkg = standardDosePerkg;
        this.interactionDrug = interactionDrug;
        this.interactionFactor = interactionFactor;
    }

    public int getId() { return id;}

    public String getName() {
        return name;
    }

    public double getStandardDosePerkg() {
        return standardDosePerkg;
    }

    public String getInteractionDrug() {
        return interactionDrug;
    }

    public double getInteractionFactor() {
        return interactionFactor;
    }

    public void setId(int id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public void setStandardDosePerkg(double standardDosePerkg) {this.standardDosePerkg = standardDosePerkg;}

    public void setInteractionDrug(String interactionDrug) {this.interactionDrug = interactionDrug;}

    public void setInteractionFactor(double interactionFactor) {this.interactionFactor = interactionFactor;}

    @Override
    public String toString() {
        return String.format(
                "Drug[name=%s, dosePerKg=%.2f mg/kg, interactsWith=%s, factor=%.2f]",
                name, standardDosePerkg, interactionDrug, interactionFactor
        );
    }
}
