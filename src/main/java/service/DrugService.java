package service;

import model.Drug;
import model.Patient;
import persistence.IDrugDAO;
import persistence.IPatientDAO;

public class DrugService {

    IDrugDAO    drugDAO;
    IPatientDAO patientDAO;

    // Injection de dépendances
    public DrugService(IDrugDAO drugDAO, IPatientDAO patientDAO) {
        this.drugDAO    = drugDAO;
        this.patientDAO = patientDAO;
    }

    // US1 – Dose standard pour un médicament donné
    public double findStandardDose(String drugName, int patientId) {
        Drug    drug    = drugDAO.findByName(drugName);
        Patient patient = patientDAO.findById(patientId);

        if (drug == null)    throw new IllegalArgumentException("Drug not found: " + drugName);
        if (patient == null) throw new IllegalArgumentException("Patient not found: " + patientId);

        return patient.getWeight() * drug.getStandardDosePerkg();
    }

    // US2 – Dose ajustée en tenant compte du médicament concomitant
    public double findAdjustedDose(String drugName, String concomitantDrug, int patientId) {
        Drug    drug    = drugDAO.findByName(drugName);
        Patient patient = patientDAO.findById(patientId);

        if (drug == null)    throw new IllegalArgumentException("Drug not found: " + drugName);
        if (patient == null) throw new IllegalArgumentException("Patient not found: " + patientId);

        double sd = patient.getWeight() * drug.getStandardDosePerkg();

        // Chercher le facteur d'interaction si le médicament concomitant correspond
        double factor = 1.0;
        if (drug.getInteractionDrug().equalsIgnoreCase(concomitantDrug))
            factor = drug.getInteractionFactor();

        return sd * factor;
    }
}
