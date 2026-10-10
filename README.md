# 💊 Drugstore — calcul de doses médicamenteuses (JDBC / JPA)

![Java](https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white)
![JPA](https://img.shields.io/badge/JPA-JPQL-59666C)
![JDBC](https://img.shields.io/badge/JDBC-MariaDB-003545?logo=mariadb&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-build-C71A36?logo=apachemaven)

Application console destinée aux médecins pour obtenir la dose standard d'un médicament, calculer une **dose ajustée** selon le poids du patient et les interactions médicamenteuses, et consulter un dossier patient.

Le point fort du projet : **la même application fonctionne avec trois sources de données interchangeables** (MockDB en mémoire, JDBC, JPA) grâce au patron **DAO**.

> Projet — cours **420-A10 Programmation objet et bases de données relationnelles** — Collège de Bois-de-Boulogne, AEC Analyste-programmeur, 2026. Réalisé en équipe de 2.

---

## 📋 User stories

| # | En tant que | Je veux | Afin de |
|---|---|---|---|
| US1 | Médecin | obtenir la dose standard d'un médicament | prescrire la bonne dose |
| US2 | Médecin | calculer une dose ajustée (poids + médicament concomitant) | réduire les effets indésirables |
| US3 | Médecin | afficher le dossier d'un patient à partir de son ID | suivre son traitement |

Formules : `SD = poids × dose_standard_par_kg` puis `AD = SD × facteur_d_interaction`

## 🏗️ Architecture en 5 couches

```
view (DoctorView) ─► control (AppDriver) ─► service (DrugService)
                                                  │
                         persistence (interfaces IDrugDAO, IPatientDAO, IDoctorDAO)
                           ├── *_MockDB  → repository/InMemory_MockDB
                           ├── *_JDBC    → ConnectionProvider + SQL_BOX (requêtes SQL)
                           └── *_JPA     → EntityManagerProvider + JPQL
```

Au lancement, l'utilisateur choisit le repository : **1 – MockDB**, **2 – JDBC**, **3 – JPA**.

## 🛠️ Technologies

Java 21 · JDBC · JPA (Jakarta Persistence, JPQL) · MariaDB · Maven · Log4j 2 · UML

## 🚀 Exécution

```bash
git clone https://github.com/hanen91-ch/drugstore-jpa-jdbc.git
cd drugstore-jpa-jdbc
./mvnw clean compile      # mvnw.cmd sous Windows
```

- **Mode MockDB** : aucune base de données requise, lancer `control.AppDriver` et choisir `1`.
- **Modes JDBC / JPA** :
  1. Créer la base avec `src/main/java/repository/db_schema.sql` puis `db_data.sql`.
  2. Remplacer `VOTRE_MOT_DE_PASSE` (et l'hôte si besoin) dans `src/main/resources/db.properties` et `META-INF/persistence.xml`.
  3. Lancer `control.AppDriver` et choisir `2` ou `3`.

## 👩‍💻 Auteure

**Hanen Chtourou** — Analyste-programmeure
[LinkedIn](https://linkedin.com/in/hanen-chtourou-45097a104) · [GitHub](https://github.com/hanen91-ch)
