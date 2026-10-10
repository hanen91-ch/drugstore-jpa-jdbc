package model;

import jakarta.persistence.*;

@Entity
@Table(name = "AdverseDrugReaction")

public class AdverseDrugReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    public AdverseDrugReaction() {}

    // Constructeur sans id
    public AdverseDrugReaction(String description, String category) {
        this.description = description;
        this.category = category;
    }

    // Constructeur avec id

    public AdverseDrugReaction(int id, String description, String category) {
        this.id = id;
        this.description = description;
        this.category = category;
    }
    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        return String.format(
                "AdverseDrugReaction{id=%d, description='%s', category='%s'}",
                id, description, category
        );
    }
}
