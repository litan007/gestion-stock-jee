INSERT INTO roles (id, nom, description) VALUES
  (1, 'ADMIN', 'Administrateur avec accès complet'),
  (2, 'USER', 'Utilisateur standard')
ON DUPLICATE KEY UPDATE nom=VALUES(nom);

INSERT INTO utilisateurs (id, username, email, mot_de_passe, nom_complet, role_id, actif, date_creation, date_modification)
VALUES
  (1, 'admin', 'admin@gestionstock.com', '$2b$12$fNJMB9zFCnwlP7p56n/hK.vOJQQpOMm1J/h.1Pgl4NxzBJrcrBSVK', 'Administrateur', 1, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (2, 'user', 'user@gestionstock.com', '$2b$12$HQxu01jgUmIsuK/QXavbKeOtTakPVHDxrUGsav20eXqzBaMM1.6tG', 'Utilisateur Standard', 2, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
ON DUPLICATE KEY UPDATE username=VALUES(username);

INSERT INTO produits (nom, reference, description, categorie, prix, quantite, quantite_minimale, fournisseur, actif, date_creation, date_modification) VALUES
('Clavier Mécanique', 'PRD-001', 'Clavier mécanique RGB', 'Informatique', 79.99, 25, 5, 'TechPro', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Souris Sans Fil', 'PRD-002', 'Souris ergonomique sans fil', 'Informatique', 29.90, 4, 5, 'TechPro', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Écran 24 pouces', 'PRD-003', 'Moniteur full HD', 'Informatique', 149.00, 0, 3, 'DisplayX', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Chaise de Bureau', 'PRD-004', 'Chaise ergonomique', 'Mobilier', 199.00, 8, 3, 'OfficePlus', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Bureau Compact', 'PRD-005', 'Bureau en bois', 'Mobilier', 259.99, 2, 4, 'OfficePlus', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Ramette A4', 'PRD-006', 'Papier imprimante 500 feuilles', 'Fournitures', 6.99, 100, 20, 'PaperCo', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Stylo Bleu', 'PRD-007', 'Lot de 10 stylos', 'Fournitures', 4.50, 40, 10, 'PaperCo', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Imprimante Laser', 'PRD-008', 'Imprimante monochrome', 'Informatique', 220.00, 1, 2, 'PrintOne', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Disque SSD 1To', 'PRD-009', 'Stockage rapide SSD', 'Informatique', 89.00, 12, 4, 'StorageMax', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('Câble HDMI', 'PRD-010', 'Câble HDMI 2m', 'Accessoires', 12.99, 0, 5, 'CableNet', TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
