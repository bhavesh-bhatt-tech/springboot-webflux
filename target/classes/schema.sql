DROP TABLE IF EXISTS EMPLOYEE;
CREATE TABLE EMPLOYEE (id INTEGER NOT NULL AUTO_INCREMENT, departmentId INTEGER, firstName VARCHAR(255), lastName VARCHAR(255), dateOfBirth VARCHAR(255), PRIMARY KEY (id));