USE bdveterinaria;
INSERT INTO medicamento (nombre, clasificacion, descripcion, fecha_caducidad, existencia, precio, instrucciones_uso) VALUES ('Paracetamol', 'Analgésico', 'Alivia el dolor y reduce la fiebre', '2023-12-01', 150, 10.5, 'Tomar 1 tableta cada 6 horas');
INSERT INTO medicamento (nombre, clasificacion, descripcion, fecha_caducidad, existencia, precio, instrucciones_uso) VALUES ('Ibuprofeno', 'Antiinflamatorio', 'Reduce la inflamación y alivia el dolor', '2023-11-15', 100, 8.75, 'Tomar 1 tableta con alimentos');
INSERT INTO medicamento (nombre, clasificacion, descripcion, fecha_caducidad, existencia, precio, instrucciones_uso) VALUES ('Amoxicilina', 'Antibiótico', 'Trata infecciones bacterianas', '2024-02-28', 50, 15.25, 'Tomar 1 cápsula cada 8 horas');
INSERT INTO medicamento (nombre, clasificacion, descripcion, fecha_caducidad, existencia, precio, instrucciones_uso) VALUES ('Loratadina', 'Antihistamínico', 'Alivia los síntomas de alergias', '2023-10-20', 75, 7.99, 'Tomar 1 tableta diaria');
INSERT INTO medicamento (nombre, clasificacion, descripcion, fecha_caducidad, existencia, precio, instrucciones_uso) VALUES ('Omeprazol', 'Antiácido', 'Reduce la producción de ácido en el estómago', '2023-11-30', 120, 12.0, 'Tomar 1 cápsula antes de las comidas');
INSERT INTO medicamento (nombre, clasificacion, descripcion, fecha_caducidad, existencia, precio, instrucciones_uso) VALUES ('Dipirona', 'Analgésico', 'Alivia el dolor y reduce la fiebre', '2023-12-15', 80, 9.5, 'Tomar 1 tableta cada 4 horas');
INSERT INTO medicamento (nombre, clasificacion, descripcion, fecha_caducidad, existencia, precio, instrucciones_uso) VALUES ('Ciprofloxacino', 'Antibiótico', 'Trata infecciones bacterianas', '2024-01-10', 40, 18.75, 'Tomar 1 tableta cada 12 horas');
INSERT INTO medicamento (nombre, clasificacion, descripcion, fecha_caducidad, existencia, precio, instrucciones_uso) VALUES ('Salbutamol', 'Broncodilatador', 'Alivia la obstrucción de las vías respiratorias', '2023-11-05', 60, 15.0, 'Inhalar 1 vez cada 4 horas');
INSERT INTO medicamento (nombre, clasificacion, descripcion, fecha_caducidad, existencia, precio, instrucciones_uso) VALUES ('Atorvastatina', 'Hipolipemiante', 'Reduce los niveles de colesterol', '2023-12-20', 90, 22.5, 'Tomar 1 tableta diaria antes de dormir');
INSERT INTO medicamento (nombre, clasificacion, descripcion, fecha_caducidad, existencia, precio, instrucciones_uso) VALUES ('Hidroclorotiazida', 'Diurético', 'Aumenta la excreción de agua y sal', '2024-01-25', 55, 14.25, 'Tomar 1 tableta cada mañana');

INSERT INTO veterinario (nombre, apellido_paterno, apellido_materno, fecha_nacimiento, celular, correo, especialidad, hora_entrada, hora_salida) VALUES ('Ana', 'García', 'López', '1985-03-12', '555-123-4567', 'ana.garcia@email.com', 'Medicina General', '09:00', '17:00');
INSERT INTO veterinario (nombre, apellido_paterno, apellido_materno, fecha_nacimiento, celular, correo, especialidad, hora_entrada, hora_salida) VALUES ('Carlos', 'Martínez', 'Pérez', '1990-06-25', '555-234-5678', 'carlos.martinez@email.com', 'Cirugía', '10:30', '18:30');
INSERT INTO veterinario (nombre, apellido_paterno, apellido_materno, fecha_nacimiento, celular, correo, especialidad, hora_entrada, hora_salida) VALUES ('Laura', 'Sánchez', 'Ramírez', '1988-09-18', '555-345-6789', 'laura.sanchez@email.com', 'Dermatología', '08:00', '16:00');
INSERT INTO veterinario (nombre, apellido_paterno, apellido_materno, fecha_nacimiento, celular, correo, especialidad, hora_entrada, hora_salida) VALUES ('Javier', 'Hernández', 'Gómez', '1982-12-05', '555-456-7890', 'javier.hernandez@email.com', 'Oftalmología', '11:00', '19:00');
INSERT INTO veterinario (nombre, apellido_paterno, apellido_materno, fecha_nacimiento, celular, correo, especialidad, hora_entrada, hora_salida) VALUES ('Mónica', 'Guzmán', 'Díaz', '1993-02-20', '555-567-8901', 'monica.guzman@email.com', 'Odontología', '08:30', '16:30');
INSERT INTO veterinario (nombre, apellido_paterno, apellido_materno, fecha_nacimiento, celular, correo, especialidad, hora_entrada, hora_salida) VALUES ('Alejandro', 'Lara', 'Moreno', '1980-07-15', '555-678-9012', 'alejandro.lara@email.com', 'Nutrición', '09:30', '17:30');
INSERT INTO veterinario (nombre, apellido_paterno, apellido_materno, fecha_nacimiento, celular, correo, especialidad, hora_entrada, hora_salida) VALUES ('Sofía', 'Rodríguez', 'Santos', '1987-11-28', '555-789-0123', 'sofia.rodriguez@email.com', 'Cardiología', '10:00', '18:00');
INSERT INTO veterinario (nombre, apellido_paterno, apellido_materno, fecha_nacimiento, celular, correo, especialidad, hora_entrada, hora_salida) VALUES ('Ricardo', 'Pérez', 'Flores', '1995-04-10', '555-890-1234', 'ricardo.perez@email.com', 'Ortopedia', '12:00', '20:00');
INSERT INTO veterinario (nombre, apellido_paterno, apellido_materno, fecha_nacimiento, celular, correo, especialidad, hora_entrada, hora_salida) VALUES ('María', 'López', 'Herrera', '1984-08-23', '555-901-2345', 'maria.lopez@email.com', 'Gastroenterología', '08:45', '16:45');
INSERT INTO veterinario (nombre, apellido_paterno, apellido_materno, fecha_nacimiento, celular, correo, especialidad, hora_entrada, hora_salida) VALUES ('Jorge', 'Gómez', 'Castillo', '1998-01-15', '555-012-3456', 'jorge.gomez@email.com', 'Radiología', '11:30', '19:30');

INSERT INTO propietario (nombre, apellido_paterno, apellido_materno, direccion, celular, correo, fecha_nacimiento, ocupacion) VALUES ('Ana', 'Gutierrez', 'Lopez', 'Calle 789', '6669876543', 'ana@example.com', '1988-05-30', 'Maestra');
INSERT INTO propietario (nombre, apellido_paterno, apellido_materno, direccion, celular, correo, fecha_nacimiento, ocupacion) VALUES ('Carlos', 'Hernandez', 'Ramos', 'Avenida 567', '1112223344', 'carlos@example.com', '1975-12-18', 'Ingeniero Civil');
INSERT INTO propietario (nombre, apellido_paterno, apellido_materno, direccion, celular, correo, fecha_nacimiento, ocupacion) VALUES ('Laura', 'Diaz', 'Santos', 'Calle 456', '9876543210', 'laura@example.com', '1992-08-20', 'Enfermera');
INSERT INTO propietario (nombre, apellido_paterno, apellido_materno, direccion, celular, correo, fecha_nacimiento, ocupacion) VALUES ('Miguel', 'Rodriguez', 'Vargas', 'Calle 789', '5554443333', 'miguel@example.com', '1980-04-12', 'Contador');
INSERT INTO propietario (nombre, apellido_paterno, apellido_materno, direccion, celular, correo, fecha_nacimiento, ocupacion) VALUES ('Sofia', 'Martinez', 'Fernandez', 'Avenida 123', '9998887777', 'sofia@example.com', '1983-09-25', 'Diseñadora');
INSERT INTO propietario (nombre, apellido_paterno, apellido_materno, direccion, celular, correo, fecha_nacimiento, ocupacion) VALUES ('Ricardo', 'Luna', 'Ortega', 'Calle 234', '7776665555', 'ricardo@example.com', '1995-01-08', 'Estudiante');
INSERT INTO propietario (nombre, apellido_paterno, apellido_materno, direccion, celular, correo, fecha_nacimiento, ocupacion) VALUES ('Carmen', 'Sanchez', 'Gomez', 'Avenida 567', '1231231234', 'carmen@example.com', '1978-06-15', 'Arquitecta');
INSERT INTO propietario (nombre, apellido_paterno, apellido_materno, direccion, celular, correo, fecha_nacimiento, ocupacion) VALUES ('Javier', 'Mendoza', 'Castillo', 'Calle 890', '3216549870', 'javier@example.com', '1987-11-03', 'Programador');
INSERT INTO propietario (nombre, apellido_paterno, apellido_materno, direccion, celular, correo, fecha_nacimiento, ocupacion) VALUES ('Isabel', 'Fuentes', 'Jimenez', 'Avenida 345', '9991112222', 'isabel@example.com', '1984-02-17', 'Psicóloga');
INSERT INTO propietario (nombre, apellido_paterno, apellido_materno, direccion, celular, correo, fecha_nacimiento, ocupacion) VALUES ('Alejandro', 'Ramirez', 'Perez', 'Calle 678', '4567890123', 'alejandro@example.com', '1998-07-05', 'Estudiante de Medicina');

-- Asignación de propietario_id
INSERT INTO mascota (nombre, especie, raza, edad, altura, peso, sexo, color, propietario_id) VALUES ('Pelusa', 'Gato', 'Persa', 3, 25.5, 3.5, 'Hembra', 'Blanco', 1);
INSERT INTO mascota (nombre, especie, raza, edad, altura, peso, sexo, color, propietario_id) VALUES ('Max', 'Perro', 'Labrador', 2, 60.2, 25.8, 'Macho', 'Negro', 2);
INSERT INTO mascota (nombre, especie, raza, edad, altura, peso, sexo, color, propietario_id) VALUES ('Firulais', 'Perro', 'Chihuahua', 1, 15.0, 2.0, 'Macho', 'Café', 3);
INSERT INTO mascota (nombre, especie, raza, edad, altura, peso, sexo, color, propietario_id) VALUES ('Luna', 'Gato', 'Siames', 4, 22.0, 4.0, 'Hembra', 'Gris', 4);
INSERT INTO mascota (nombre, especie, raza, edad, altura, peso, sexo, color, propietario_id) VALUES ('Rocky', 'Perro', 'Bulldog', 5, 30.8, 18.5, 'Macho', 'Blanco y Marrón', 5);
INSERT INTO mascota (nombre, especie, raza, edad, altura, peso, sexo, color, propietario_id) VALUES ('Mia', 'Gato', 'Maine Coon', 2, 28.5, 5.2, 'Hembra', 'Atigrado', 6);
INSERT INTO mascota (nombre, especie, raza, edad, altura, peso, sexo, color, propietario_id) VALUES ('Bobby', 'Perro', 'Poodle', 3, 20.3, 10.1, 'Macho', 'Blanco', 7);
INSERT INTO mascota (nombre, especie, raza, edad, altura, peso, sexo, color, propietario_id) VALUES ('Coco', 'Perro', 'Cocker Spaniel', 4, 35.0, 15.7, 'Macho', 'Dorado', 8);
INSERT INTO mascota (nombre, especie, raza, edad, altura, peso, sexo, color, propietario_id) VALUES ('Misty', 'Gato', 'Angora', 1, 18.2, 3.0, 'Hembra', 'Blanco', 9);
INSERT INTO mascota (nombre, especie, raza, edad, altura, peso, sexo, color, propietario_id) VALUES ('Buddy', 'Perro', 'Golden Retriever', 6, 65.5, 28.0, 'Macho', 'Dorado', 10);

SELECT * FROM medicamento;
SELECT * FROM veterinario;
SELECT * FROM propietario;
SELECT * FROM mascota;
