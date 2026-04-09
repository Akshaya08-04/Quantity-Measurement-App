CREATE TABLE IF NOT EXISTS quantity_measurement_history (
    id INT AUTO_INCREMENT PRIMARY KEY,
    this_value DOUBLE NOT NULL,
    this_unit VARCHAR(50) NOT NULL,
    that_value DOUBLE,
    that_unit VARCHAR(50),
    measurement_type VARCHAR(50) NOT NULL,
    operation VARCHAR(50) NOT NULL,
    result_value DOUBLE,
    result_unit VARCHAR(50),
    result_message VARCHAR(255)
);