package model;

import jakarta.persistence.*;

@Entity
@Table(name = "Prescription")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "PDD")
    private double PDD;

    @ManyToOne
    @JoinColumn(name = "drug_id")
    private Drug drug;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "adverse_drug_reaction_id")
    private AdverseDrugReaction adverseDrugReaction;

    public Prescription() {}

    // Constructeur sans id
    public Prescription(double PDD, Drug drug, Patient patient, Doctor doctor, AdverseDrugReaction adverseDrugReaction) {
        this.PDD = PDD;
        this.drug = drug;
        this.patient = patient;
        this.doctor = doctor;
        this.adverseDrugReaction = adverseDrugReaction;
    }

    // Constructeur avec id

    public Prescription(int id, double PDD, Drug drug, Patient patient, Doctor doctor, AdverseDrugReaction adverseDrugReaction) {
        this.id = id;
        this.PDD = PDD;
        this.drug = drug;
        this.patient = patient;
        this.doctor = doctor;
        this.adverseDrugReaction = adverseDrugReaction;
    }

    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public double getPDD() { return PDD; }
    public void setPDD(double PDD) { this.PDD = PDD; }
    public Drug getDrug() { return drug; }
    public void setDrug(Drug drug) { this.drug = drug; }
    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }
    public Doctor getDoctor() { return doctor; }
    public void setDoctor(Doctor doctor) { this.doctor = doctor; }
    public AdverseDrugReaction getAdverseDrugReaction() { return adverseDrugReaction; }
    public void setAdverseDrugReaction(AdverseDrugReaction adverseDrugReaction) { this.adverseDrugReaction = adverseDrugReaction; }

    @Override
        public String toString() {
        return String.format(
                "Prescription{id=%d, drug='%s', PDD=%.2f mg, " +
                        "patient='%s', doctor='%s'}",
                id, drug.getName(), PDD,
                patient.getName(), doctor.getName()
        );
    }
}
