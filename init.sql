CREATE DATABASE IF NOT EXISTS f1championship;
USE f1championship;

-- Table: tblOrganization
CREATE TABLE IF NOT EXISTS tblOrganization (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

-- Table: tblTournament
CREATE TABLE IF NOT EXISTS tblTournament (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    year INT NOT NULL,
    idOrganization INT,
    FOREIGN KEY (idOrganization) REFERENCES tblOrganization(id)
);

-- Table: tblStage
CREATE TABLE IF NOT EXISTS tblStage (
    id INT AUTO_INCREMENT PRIMARY KEY,
    stageCode VARCHAR(50) NOT NULL,
    name VARCHAR(255) NOT NULL,
    numberLaps INT,
    location VARCHAR(255),
    time DATE,
    description TEXT,
    idTournament INT,
    FOREIGN KEY (idTournament) REFERENCES tblTournament(id)
);

-- Table: tblTeam
CREATE TABLE IF NOT EXISTS tblTeam (
    id INT AUTO_INCREMENT PRIMARY KEY,
    teamCode VARCHAR(50) NOT NULL,
    name VARCHAR(255) NOT NULL,
    brand VARCHAR(255),
    description TEXT
);

-- Table: tblRacer
CREATE TABLE IF NOT EXISTS tblRacer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    driverCode VARCHAR(50) NOT NULL,
    name VARCHAR(255) NOT NULL,
    nationality VARCHAR(100),
    dob DATE,
    biography TEXT
);

-- Table: tblUser
CREATE TABLE IF NOT EXISTS tblUser (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    fullName VARCHAR(255)
);

-- Table: tblContract
CREATE TABLE IF NOT EXISTS tblContract (
    id INT AUTO_INCREMENT PRIMARY KEY,
    startDate DATE,
    endDate DATE,
    idTeam INT,
    idRacer INT,
    FOREIGN KEY (idTeam) REFERENCES tblTeam(id),
    FOREIGN KEY (idRacer) REFERENCES tblRacer(id)
);

-- Table: tblResult
CREATE TABLE IF NOT EXISTS tblResult (
    id INT AUTO_INCREMENT PRIMARY KEY,
    finishTime VARCHAR(50),
    lapsCompleted INT,
    idStage INT,
    idContract INT,
    idUser INT,
    FOREIGN KEY (idStage) REFERENCES tblStage(id),
    FOREIGN KEY (idContract) REFERENCES tblContract(id),
    FOREIGN KEY (idUser) REFERENCES tblUser(id)
);

-- Sample data
INSERT INTO tblOrganization (name, description) VALUES ('FIA', 'Federation Internationale de l''Automobile');

INSERT INTO tblTournament (name, year, idOrganization) VALUES ('F1 World Championship', 2026, 1);

INSERT INTO tblStage (stageCode, name, numberLaps, location, time, description, idTournament) VALUES 
('BHR', 'Bahrain Grand Prix', 57, 'Bahrain International Circuit', '2026-03-01', '', 1),
('MCO', 'Monaco Grand Prix', 78, 'Circuit de Monaco', '2026-05-14', '', 1),
('GBR', 'British Grand Prix', 52, 'Silverstone Circuit', '2026-07-05', '', 1),
('JPN', 'Japanese Grand Prix', 53, 'Suzuka International Racing Course', '2026-09-27', '', 1),
('UAE', 'Abu Dhabi Grand Prix', 58, 'Yas Marina Circuit', '2026-11-29', '', 1);

INSERT INTO tblTeam (teamCode, name, brand, description) VALUES 
('FER', 'Scuderia Ferrari', 'Ferrari', ''),
('RBR', 'Red bull Racing', 'Red Bull', ''),
('ARS', 'Argentia super', 'Adidas', ''),
('POR', 'Portu', 'Nike', ''),
('GER', 'Germen', 'Puma', '');

INSERT INTO tblRacer (driverCode, name, nationality, dob, biography) VALUES 
('LEC', 'Charles Leclerc', 'Monegasque', '1997-10-16', 'Ferrari driver'),
('VER', 'Max Verstappen', 'Dutch', '1997-09-30', 'Red Bull driver'),
('MES', 'Messi', 'Argentine', '1987-06-24', 'Legendary driver'),
('RON', 'Ronaldo', 'Portuguese', '1985-02-05', 'CR7 speed master'),
('REU', 'Reus', 'German', '1989-05-31', 'Marco Reus F1 star');

INSERT INTO tblUser (username, password, fullName) VALUES 
('Staff1', 'Staff1', 'Staff1'),
('Staff2', 'Staff2', 'Staff2');

INSERT INTO tblContract (startDate, endDate, idTeam, idRacer) VALUES 
('2026-01-01', '2026-12-31', 1, 1), -- Scuderia Ferrari - Charles Leclerc
('2026-01-01', '2026-12-31', 2, 2), -- Red bull Racing - Max Verstappen
('2026-01-01', '2026-12-31', 3, 3), -- Argentia super - Messi
('2026-01-01', '2026-12-31', 4, 4), -- Portu - Ronaldo
('2026-01-01', '2026-12-31', 5, 5); -- Germen - Reus

-- Sample results for Bahrain Grand Prix (Stage 1) matching the user image
INSERT INTO tblResult (finishTime, lapsCompleted, idStage, idContract, idUser) VALUES 
('01:11:20', 12, 1, 1, 1),
('02:12:20', 21, 1, 2, 1),
('1:09:11', 14, 1, 3, 1),
('2:01:11', 21, 1, 4, 1),
('1:20:43', 16, 1, 5, 1);
