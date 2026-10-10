package repository;

import model.*;
import java.util.ArrayList;
import java.util.List;

public class InMemory_MockDB {

    private static InMemory_MockDB instance;

    private List<Drug> drugs;
    private List<User> users;  // contient à la fois Doctor et Patient (polymorphisme)

    private InMemory_MockDB() { this.initialize(); }

    public static InMemory_MockDB getInstance() {
        if (InMemory_MockDB.instance == null)
            InMemory_MockDB.instance = new InMemory_MockDB();
        return InMemory_MockDB.instance;
    }

    public void initialize() {
        this.drugs = new ArrayList<>();
        this.users = new ArrayList<>();

        // Drugs
        drugs.add(new Drug(1, "Amoxicillin",   25.0, "Warfarin",     1.5));
        drugs.add(new Drug(2, "Ibuprofen",     10.0, "Aspirin",      0.8));
        drugs.add(new Drug(3, "Metformin",      5.0, "Insulin",      1.2));
        drugs.add(new Drug(4, "Atorvastatin",   2.0, "Cyclosporine", 2.0));
        drugs.add(new Drug(5, "Lisinopril",     0.1, "Potassium",    1.3));

        // Doctors — instanciés comme Doctor, stockés dans la liste User
        Doctor martin = new Doctor(1, "Dr. Martin", "514-999-0001", "Cardiology", new ArrayList<>());
        Doctor nguyen  = new Doctor(2, "Dr. Nguyen",  "514-999-0002", "Neurology",   new ArrayList<>());
        this.users.add(martin);
        this.users.add(nguyen);

        // Patients — instanciés comme Patient, stockés dans la liste User
        Patient alice = new Patient(3, "Alice Tremblay", "514-111-1111", 65, 35, "Penicillin", new ArrayList<>());
        Patient bob   = new Patient(4, "Bob Gagnon",     "514-222-2222", 80, 50, "None",       new ArrayList<>());
        Patient clara = new Patient(5, "Clara Lavoie",   "514-333-3333", 55, 28, "Aspirin",    new ArrayList<>());
        this.users.add(alice);
        this.users.add(bob);
        this.users.add(clara);
    }

    public List<Drug> getDrugs() { return drugs; }
    public List<User> getUsers() { return users; }
}
