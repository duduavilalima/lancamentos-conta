--liquibase formatted sql

-- changeset user:01_create_conta_table
CREATE TABLE Conta (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    saldo DOUBLE NOT NULL,
    version INTEGER
);

-- changeset user:01_create_lancamento_table
CREATE TABLE Lancamento (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_conta BIGINT NOT NULL,
    valor DOUBLE NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    data_lancamento TIMESTAMP NOT NULL,
    CONSTRAINT fk_conta FOREIGN KEY (id_conta) REFERENCES Conta(id)
);

-- Insert 5 rows into the Conta table with saldo between 1,000,000 and 5,000,000
INSERT INTO Conta (saldo) VALUES (1500000);
INSERT INTO Conta (saldo) VALUES (2500000);
INSERT INTO Conta (saldo) VALUES (3500000);
INSERT INTO Conta (saldo) VALUES (4500000);
INSERT INTO Conta (saldo) VALUES (5000000);
