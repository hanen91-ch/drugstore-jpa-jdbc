package model;


import jakarta.persistence.*;

@Entity
@Table(name = "User")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")



public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "tel")
    private String tel;

    // Constructeur vide obligatoire pour JPA
    public User() {}

    // Constructeur sans id -- avant insertion dans BD
    public User(String name, String tel) {
        this.name = name;
        this.tel = tel;
    }

    // Constructeur avec id -- aprés lecture depuis la BD
    public User(int id, String name, String tel) {
        this.id = id;
        this.name = name;
        this.tel = tel;
    }
    // Getters et Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTel() { return tel; }
    public void setTel(String tel) { this.tel = tel; }

    @Override
    public String toString() {

        return String.format("User{id=%d, name='%s', tel='%s'}", id, name, tel);
    }
}
