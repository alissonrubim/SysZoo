
USE SysZoo;

CREATE TABLE Funcionario (
    FuncionarioId INT NOT NULL AUTO_INCREMENT,
    Matricula VARCHAR(70) NOT NULL,
    Nome VARCHAR(70) NOT NULL,
    Endereco VARCHAR(256) NOT NULL,
    Telefone VARCHAR(20) NULL,
    PRIMARY KEY (FuncionarioId) 
);

CREATE TABLE Tratador (
    TratadorId INT NOT NULL AUTO_INCREMENT,
    FuncionarioId INT NOT NULL,
    PRIMARY KEY (TratadorId) 
);

ALTER TABLE Tratador ADD CONSTRAINT Tratador_Funcionario_FK FOREIGN KEY (FuncionarioId) REFERENCES Funcionario(FuncionarioId)
