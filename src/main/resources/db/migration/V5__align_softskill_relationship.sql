ALTER TABLE soft_skill ADD COLUMN colaborador_id INT;

ALTER TABLE soft_skill ADD CONSTRAINT fk_softskill_colaborador FOREIGN KEY (colaborador_id) REFERENCES colaborador(id);

DROP TABLE colaborador_softskill;
