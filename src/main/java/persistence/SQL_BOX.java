package persistence;

public class SQL_BOX {

    // ===================== DRUG — SQL natif (JDBC) =====================

    static final String FIND_DRUG_BY_ID =
            """
                SELECT *
                FROM Drug
                WHERE id = ?
            """;

    static final String FIND_DRUG_BY_NAME =
            """
                SELECT *
                FROM Drug
                WHERE name = ?
            """;

    static final String FIND_ALL_DRUGS =
            """
                SELECT *
                FROM Drug
            """;

    static final String ADD_DRUG =
            """
                INSERT INTO Drug(name, standard_dose_per_kg, interaction_drug, interaction_factor)
                VALUES (?, ?, ?, ?)
            """;

    static final String DELETE_DRUG =
            """
                DELETE FROM Drug
                WHERE id = ?
            """;

    // ===================== DRUG — JPQL (JPA) =====================

    static final String FIND_DRUG_BY_ID_JPQL =
            """
                SELECT d
                FROM Drug d
                WHERE d.id = :id
            """;

    static final String FIND_DRUG_BY_NAME_JPQL =
            """
                SELECT d
                FROM Drug d
                WHERE d.name = :name
            """;

    static final String FIND_ALL_DRUGS_JPQL =
            """
                SELECT d
                FROM Drug d
            """;

    static final String DELETE_DRUG_JPQL =
            """
                DELETE
                FROM Drug d
                WHERE d.id = :id
            """;

    // ===================== PATIENT — SQL natif (JDBC) =====================

    static final String FIND_PATIENT_BY_ID =
            """
                SELECT *
                FROM User
                WHERE id = ?
                  AND dtype = 'PATIENT'
            """;

    static final String FIND_ALL_PATIENTS =
            """
                SELECT *
                FROM User
                WHERE dtype = 'PATIENT'
            """;

    static final String ADD_PATIENT =
            """
                INSERT INTO User(name, tel, weight, age, allergies, dtype)
                VALUES (?, ?, ?, ?, ?, 'PATIENT')
            """;

    static final String DELETE_PATIENT =
            """
                DELETE FROM User
                WHERE id = ?
                  AND dtype = 'PATIENT'
            """;

    // ===================== PATIENT — JPQL (JPA) =====================

    static final String FIND_PATIENT_BY_ID_JPQL =
            """
                SELECT p
                FROM Patient p
                WHERE p.id = :id
            """;

    static final String FIND_ALL_PATIENTS_JPQL =
            """
                SELECT p
                FROM Patient p
            """;

    static final String DELETE_PATIENT_JPQL =
            """
                DELETE
                FROM Patient p
                WHERE p.id = :id
            """;

    // ===================== DOCTOR — SQL natif (JDBC) =====================

    static final String FIND_DOCTOR_BY_ID =
            """
                SELECT *
                FROM User
                WHERE id = ?
                  AND dtype = 'DOCTOR'
            """;

    static final String FIND_ALL_DOCTORS =
            """
                SELECT *
                FROM User
                WHERE dtype = 'DOCTOR'
            """;

    static final String ADD_DOCTOR =
            """
                INSERT INTO User(name, tel, skill, dtype)
                VALUES (?, ?, ?, 'DOCTOR')
            """;

    static final String DELETE_DOCTOR =
            """
                DELETE FROM User
                WHERE id = ?
                  AND dtype = 'DOCTOR'
            """;

    // ===================== DOCTOR — JPQL (JPA) =====================

    static final String FIND_DOCTOR_BY_ID_JPQL =
            """
                SELECT d
                FROM Doctor d
                WHERE d.id = :id
            """;

    static final String FIND_ALL_DOCTORS_JPQL =
            """
                SELECT d
                FROM Doctor d
            """;

    static final String DELETE_DOCTOR_JPQL =
            """
                DELETE
                FROM Doctor d
                WHERE d.id = :id
            """;
}
