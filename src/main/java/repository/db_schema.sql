DROP TABLE IF EXISTS Prescription;
DROP TABLE IF EXISTS DrugInteraction;
DROP TABLE IF EXISTS AdverseDrugReaction;
DROP TABLE IF EXISTS Drug;
DROP TABLE IF EXISTS User;

CREATE TABLE User (
    id        INT          NOT NULL AUTO_INCREMENT,
    dtype     VARCHAR(10)  NOT NULL,   -- 'DOCTOR' ou 'PATIENT'
    name      VARCHAR(100) NOT NULL,
    tel       VARCHAR(20),
    skill     VARCHAR(100),            -- Doctor uniquement
    weight    INT,                     -- Patient uniquement
    age       INT,                     -- Patient uniquement
    allergies VARCHAR(200),            -- Patient uniquement
    PRIMARY KEY (id)
);

CREATE TABLE Drug (
    id                   INT          NOT NULL AUTO_INCREMENT,
    name                 VARCHAR(100) NOT NULL,
    standard_dose_per_kg DOUBLE       NOT NULL,
    interaction_drug     VARCHAR(100),
    interaction_factor   DOUBLE,
    PRIMARY KEY (id)
);

CREATE TABLE DrugInteraction (
    id                 INT    NOT NULL AUTO_INCREMENT,
    drug1_id           INT    NOT NULL,
    drug2_id           INT    NOT NULL,
    interaction_factor DOUBLE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (drug1_id) REFERENCES Drug(id),
    FOREIGN KEY (drug2_id) REFERENCES Drug(id)
);

CREATE TABLE AdverseDrugReaction (
    id          INT          NOT NULL AUTO_INCREMENT,
    description VARCHAR(500) NOT NULL,
    category    VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE Prescription (
    id                       INT    NOT NULL AUTO_INCREMENT,
    PDD                      DOUBLE NOT NULL,
    drug_id                  INT    NOT NULL,
    patient_id               INT    NOT NULL,
    doctor_id                INT    NOT NULL,
    adverse_drug_reaction_id INT,
    PRIMARY KEY (id),
    FOREIGN KEY (drug_id)                  REFERENCES Drug(id),
    FOREIGN KEY (patient_id)               REFERENCES User(id),
    FOREIGN KEY (doctor_id)                REFERENCES User(id),
    FOREIGN KEY (adverse_drug_reaction_id) REFERENCES AdverseDrugReaction(id)
);
