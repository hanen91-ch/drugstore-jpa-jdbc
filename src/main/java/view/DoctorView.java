package view;

import model.Drug;
import model.Patient;
import persistence.*;
import service.DrugService;

import java.util.List;
import java.util.Scanner;

public class DoctorView {

    public static void run() {

        Scanner sc = new Scanner(System.in);

        // ── Sélection du backend au démarrage ────────────────────────────────
        IDrugDAO drugDAO    = null;
        IPatientDAO patientDAO = null;

        System.out.println("\n====== Choisir le repository ======");
        System.out.println("1 – MockDB");
        System.out.println("2 – JDBC");
        System.out.println("3 – JPA");
        System.out.print("Choix : ");
        String repo = sc.nextLine().trim();

        switch (repo) {
            case "1" -> {
                drugDAO    = new DrugDAO_MockDB();
                patientDAO = new PatientDAO_MockDB();
                System.out.println(">>> Repository : MockDB");
            }
            case "2" -> {
                drugDAO    = new DrugDAO_JDBC();
                patientDAO = new PatientDAO_JDBC();
                System.out.println(">>> Repository : JDBC");
            }
            case "3" -> {
                drugDAO    = new DrugDAO_JPA();
                patientDAO = new PatientDAO_JPA();
                System.out.println(">>> Repository : JPA");
            }
            default -> {
                System.out.println("Choix invalide. MockDB utilisé par défaut.");
                drugDAO    = new DrugDAO_MockDB();
                patientDAO = new PatientDAO_MockDB();
            }
        }

        // ── Injection de dépendances ─────────────────────────────────────────
        DrugService service = new DrugService(drugDAO, patientDAO);

        boolean running = true;

        while (running) {
            System.out.println("\n====== Drugstore Menu ======");
            System.out.println("1 – Standard dose for a drug");
            System.out.println("2 – Adjusted dose (with concomitant drug)");
            System.out.println("3 – Display patient file");
            System.out.println("4 – List all drugs");
            System.out.println("0 – Exit");
            System.out.print("Choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> doStandardDose(sc, service);
                case "2" -> doAdjustedDose(sc, service);
                case "3" -> doPatientFile(sc, patientDAO);
                case "4" -> doListDrugs(drugDAO);
                case "0" -> running = false;
                default  -> System.out.println("Invalid choice.");
            }
        }
        System.out.println("Goodbye!");
    }

    // US1 ─────────────────────────────────────────────────────────────────────
    private static void doStandardDose(Scanner sc, DrugService service) {
        try {
            System.out.print("Drug name: ");
            String drugName = sc.nextLine().trim();
            System.out.print("Patient ID: ");
            int patientId = Integer.parseInt(sc.nextLine().trim());

            double sd = service.findStandardDose(drugName, patientId);
            System.out.printf("Standard dose (SD) for %s: %.2f mg%n", drugName, sd);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // US2 ─────────────────────────────────────────────────────────────────────
    private static void doAdjustedDose(Scanner sc, DrugService service) {
        try {
            System.out.print("Drug name: ");
            String drugName = sc.nextLine().trim();
            System.out.print("Concomitant drug: ");
            String concomitant = sc.nextLine().trim();
            System.out.print("Patient ID: ");
            int patientId = Integer.parseInt(sc.nextLine().trim());

            double ad = service.findAdjustedDose(drugName, concomitant, patientId);
            System.out.printf("Adjusted dose (AD) for %s + %s: %.2f mg%n", drugName, concomitant, ad);

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // US3 ─────────────────────────────────────────────────────────────────────
    private static void doPatientFile(Scanner sc, IPatientDAO patientDAO) {
        try {
            System.out.print("Patient ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());
            Patient p = patientDAO.findById(id);
            if (p == null)
                System.out.println("Patient not found.");
            else
                System.out.println(p);

        } catch (NumberFormatException e) {
            System.out.println("Error: please enter a valid number.");
        }
    }

    // Helper ──────────────────────────────────────────────────────────────────
    private static void doListDrugs(IDrugDAO drugDAO) {
        List<Drug> drugs = drugDAO.findAll();
        drugs.forEach(System.out::println);
    }
}