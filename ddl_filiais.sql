CREATE TABLE emp_func
(
    emp_id  VARCHAR(255) NOT NULL,
    func_id VARCHAR(255) NOT NULL,
    CONSTRAINT pk_emp_func PRIMARY KEY (emp_id, func_id)
);

CREATE TABLE filiais
(
    id        VARCHAR(255) NOT NULL,
    name      VARCHAR(255) NOT NULL,
    type      VARCHAR(255) NOT NULL,
    timestamp datetime     NULL,
    CONSTRAINT pk_filiais PRIMARY KEY (id)
);

ALTER TABLE emp_func
    ADD CONSTRAINT fk_emp_func_on_filial FOREIGN KEY (emp_id) REFERENCES filiais (id);

ALTER TABLE emp_func
    ADD CONSTRAINT fk_emp_func_on_funcionario FOREIGN KEY (func_id) REFERENCES funcionarios (id);