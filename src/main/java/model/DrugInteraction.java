package model;


import jakarta.persistence.*;

@Entity
@Table(name = "DrugInteraction")
public class DrugInteraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "interaction_factor")
    private double interaction_factor;

    @ManyToOne
    @JoinColumn(name = "drug1_id")
    private Drug drug1;

    @ManyToOne
    @JoinColumn(name = "drug2_id")
    private Drug drug2;

    public DrugInteraction() {}

    // Constructeur sans id
    public DrugInteraction(Drug drug1, Drug drug2, double interaction_factor) {
        this.drug1              = drug1;
        this.drug2              = drug2;
        this.interaction_factor = interaction_factor;
    }

    // Constructeur avec id
    public DrugInteraction(int id, double interaction_factor, Drug drug1, Drug drug2) {
        this.id = id;
        this.interaction_factor = interaction_factor;
        this.drug1 = drug1;
        this.drug2 = drug2;
    }

    // Getters et Setters
    public int    getId()                 { return id; }
    public Drug   getDrug1()              { return drug1; }
    public Drug   getDrug2()              { return drug2; }
    public double getInteractionFactor()  { return interaction_factor; }
    public void setId(int id)                        { this.id = id; }
    public void setDrug1(Drug drug1)                 { this.drug1 = drug1; }
    public void setDrug2(Drug drug2)                 { this.drug2 = drug2; }
    public void setInteractionFactor(double factor)  { this.interaction_factor = factor; }

    @Override
    public String toString() {
        return String.format(
                "DrugInteraction{id=%d, %s ↔ %s, factor=%.2f}",
                id, drug1.getName(), drug2.getName(), interaction_factor
        );
    }
}
