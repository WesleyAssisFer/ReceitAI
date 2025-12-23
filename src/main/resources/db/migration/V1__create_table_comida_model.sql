CREATE TABLE comida_model(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    quantidade INT,
    validade  DATE NOT NULL
);