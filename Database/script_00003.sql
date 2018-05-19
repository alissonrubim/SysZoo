USE SysZoo;

INSERT INTO Especie(Nome)
VALUES('Cachorro'), 
('Gato'), 
('Tigre'), 
('Leão'), 
('Macaco'), 
('Urso'),
('Girafa'), 
('Leopardo');

INSERT INTO Animal(Nome, Regiao, DataNascimento, Peso, EspecieId)
VALUES('Janaina', 'Canadá', '2001-01-01', 185000, (SELECT EspecieId FROM Especie WHERE Nome = 'Tigre')),
('Caco', 'Indonésia', '2006-02-25', 8021, (SELECT EspecieId FROM Especie WHERE Nome = 'Macaco'))