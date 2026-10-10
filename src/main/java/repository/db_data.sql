-- Doctors
INSERT INTO User(dtype, name, tel, skill)
VALUES ('DOCTOR', 'Dr. Martin', '514-999-0001', 'Cardiology'),
       ('DOCTOR', 'Dr. Nguyen', '514-999-0002', 'Neurology');

-- Patients
INSERT INTO User(dtype, name, tel, weight, age, allergies)
VALUES ('PATIENT', 'Alice Tremblay', '514-111-1111', 65, 35, 'Penicillin'),
       ('PATIENT', 'Bob Gagnon',     '514-222-2222', 80, 50, 'None'),
       ('PATIENT', 'Clara Lavoie',   '514-333-3333', 55, 28, 'Aspirin');

-- Drugs
INSERT INTO Drug(name, standard_dose_per_kg, interaction_drug, interaction_factor)
VALUES ('Amoxicillin',   25.0, 'Warfarin',     1.5),
       ('Ibuprofen',     10.0, 'Aspirin',       0.8),
       ('Metformin',      5.0, 'Insulin',       1.2),
       ('Atorvastatin',   2.0, 'Cyclosporine',  2.0),
       ('Lisinopril',     0.1, 'Potassium',     1.3);

-- AdverseDrugReactions
INSERT INTO AdverseDrugReaction(description, category)
VALUES ('Nausea and vomiting',     'Gastrointestinal'),
       ('Skin rash and itching',   'Allergic'),
       ('Increased bleeding risk', 'Hematological'),
       ('Dizziness and headache',  'Neurological'),
       ('Elevated liver enzymes',  'Hepatic');

-- DrugInteractions (1=Amoxicillin, 2=Ibuprofen, 3=Metformin, 4=Atorvastatin, 5=Lisinopril)
INSERT INTO DrugInteraction(drug1_id, drug2_id, interaction_factor)
VALUES (1, 2, 1.5),
       (2, 3, 0.8),
       (3, 4, 1.2),
       (4, 5, 2.0),
       (1, 5, 1.3);
