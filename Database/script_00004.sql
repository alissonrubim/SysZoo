INSERT INTO FUNCIONARIO(NOME, MATRICULA, ENDERECO, TELEFONE)
VALUES('Alisson', '0001','Rua José', '999999999'),('Sabrina', '0002','Rua A', '988445555');


CREATE TABLE Veterinario (
	VeterinarioId INT NOT NULL AUTO_INCREMENT,
    FuncionarioId INT NOT NULL,
    RegistroCRMV VARCHAR(200) NOT NULL,
    DataCRMV DATE NOT NULL,
    PRIMARY KEY (VeterinarioId)
);

ALTER TABLE Veterinario ADD CONSTRAINT Veterinario_Funcionario_FK FOREIGN KEY (FuncionarioId) REFERENCES Funcionario(FuncionarioId);

CREATE TABLE Medicamento(
	MedicamentoId INT NOT NULL AUTO_INCREMENT,
    Nome VARCHAR(200) NOT NULL,
    PRIMARY KEY (MedicamentoId)
);

INSERT INTO Medicamento(Nome)
VALUES('Dipirona'),('Rivotril'),('H1N1'),('Testosterona'),('Vinaticelina'),('Amobil em gel');


CREATE TABLE Receita(
	ReceitaId INT NOT NULL AUTO_INCREMENT,
    `Data` DATE NOT NULL,
    Observacao VARCHAR(200) NOT NULL,
    PRIMARY KEY (ReceitaId)
);

CREATE TABLE ReceitaMedicamento(
	ReceitaMedicamentoId INT NOT NULL AUTO_INCREMENT,
    ReceitaId INT NOT NULL,
    MedicamentoId INT NOT NULL,
    Dose FLOAT NOT NULL,
    Frequencia INT NOT NULL,
    PRIMARY KEY (ReceitaMedicamentoId)
);
ALTER TABLE ReceitaMedicamento ADD CONSTRAINT ReceitaMedicamento_Receita_FK FOREIGN KEY (ReceitaId) REFERENCES Receita(ReceitaId);
ALTER TABLE ReceitaMedicamento ADD CONSTRAINT ReceitaMedicamento_Medicamento_FK FOREIGN KEY (MedicamentoId) REFERENCES Medicamento(MedicamentoId);

/*Planejando*/
CREATE TABLE Tarefa(
   TarefaId INT NOT NULL AUTO_INCREMENT,
   Descricao VARCHAR(200) NOT NULL,
   PRIMARY KEY (TarefaId)
);
INSERT INTO Tarefa(Descricao)
VALUES('Banho no animal'),('Limpar o espaço do animal'),('Alimentar o animal'),('Verificar a temperatura do animal');

CREATE TABLE RotinaTratamento (
   RotinaTratamentoId INT NOT NULL AUTO_INCREMENT,
   AnimalId INT NOT NULL,
   DataValidade DATE NOT NULL,
   PRIMARY KEY (RotinaTratamentoId)
);
ALTER TABLE RotinaTratamento ADD CONSTRAINT RotinaTratamento_Animal_FK FOREIGN KEY (AnimalId) REFERENCES Animal(AnimalId);

CREATE TABLE RotinaTratamentoTarefa (
   RotinaTratamentoTarefaId INT NOT NULL AUTO_INCREMENT,
   RotinaTratamentoId INT NOT NULL,
   TarefaId INT NOT NULL,
   PRIMARY KEY (RotinaTratamentoTarefaId)
);
ALTER TABLE RotinaTratamentoTarefa ADD CONSTRAINT RotinaTratamentoTarefa_RotinaTratamento_FK FOREIGN KEY (RotinaTratamentoId) REFERENCES RotinaTratamento(RotinaTratamentoId);
ALTER TABLE RotinaTratamentoTarefa ADD CONSTRAINT RotinaTratamentoTarefa_Tarefa_FK FOREIGN KEY (TarefaId) REFERENCES Tarefa(TarefaId);

CREATE TABLE RotinaTratamentoReceita (
   RotinaTratamentoReceitaId INT NOT NULL AUTO_INCREMENT,
   RotinaTratamentoId INT NOT NULL,
   ReceitaId INT NOT NULL,
   PRIMARY KEY (RotinaTratamentoReceitaId)
);
ALTER TABLE RotinaTratamentoReceita ADD CONSTRAINT RotinaTratamentoReceita_RotinaTratamento_FK FOREIGN KEY (RotinaTratamentoId) REFERENCES RotinaTratamento(RotinaTratamentoId);
ALTER TABLE RotinaTratamentoReceita ADD CONSTRAINT RotinaTratamentoReceita_Receita_FK FOREIGN KEY (ReceitaId) REFERENCES Receita(ReceitaId);

DROP TABLE RotinaTratamentoTarefa;
DELETE FROM Tarefa;
ALTER TABLE Tarefa ADD RotinaTratamentoId INT NOT NULL;
ALTER TABLE Tarefa ADD CONSTRAINT Tarefa_RotinaTratamento_FK FOREIGN KEY (RotinaTratamentoId) REFERENCES RotinaTratamento(RotinaTratamentoId);

DELETE FROM Receita;
ALTER TABLE Receita ADD VeterinarioId INT NOT NULL;
ALTER TABLE Receita ADD CONSTRAINT Receita_Veterinario_FK FOREIGN KEY (VeterinarioId) REFERENCES Veterinario(VeterinarioId);

