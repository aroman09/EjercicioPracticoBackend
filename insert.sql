INSERT INTO personas
(direccion, edad, genero, identificacion, nombre, telefono)
VALUES( N'Loja', 25, N'M', N'1105367789', N'Liliana Jara', N'555-1274');
INSERT INTO personas
(direccion, edad, genero, identificacion, nombre, telefono)
VALUES(N'Quito', 30, N'M', N'1100380658', N'Julia Roldan', N'745-8597');
INSERT INTO personas
(direccion, edad, genero, identificacion, nombre, telefono)
VALUES( N'Ambato', 48, N'F', N'1199856325', N'Martin Torres', N'855-1774');
INSERT INTO personas
(direccion, edad, genero, identificacion, nombre, telefono)
VALUES(N'Cuenca', 32, N'M', N'1105378596', N'Juan Perez', N'124-5961');
INSERT INTO personas
(direccion, edad, genero, identificacion, nombre, telefono)
VALUES(N'Calle98586', 25, N'F', N'1105368889', N'Julia Luisa Roldos', N'555-1274');


INSERT INTO clientes
(client_id, estado, password, persona_id)
VALUES(N'jluro', 5, N'123abc..', 8);
INSERT INTO clientes
(client_id, estado, password, persona_id)
VALUES(N'jperez', 4, N'abc123..', 7);
INSERT INTO clientes
(client_id, estado, password, persona_id)
VALUES(N'mtorres', 3, N'yui123..', 7);
INSERT INTO clientes
(client_id, estado, password, persona_id)
VALUES(N'jroldan', 2, N'edr123..', 7);
INSERT INTO clientes
(client_id, estado, password, persona_id)
VALUES(N'lija', 1, N'vgb123..', 7);


INSERT INTO cuentas
(numero_cuenta, estado, id_cliente, saldo_inicial, tipo_cuenta)
VALUES(N'123456789', 1, N'jperez', 10.0, N'AHORRO');
INSERT INTO cuentas
(numero_cuenta, estado, id_cliente, saldo_inicial, tipo_cuenta)
VALUES(N'789654123', 1, N'mtorres', 5.0, N'AHORRO');
INSERT INTO cuentas
(numero_cuenta, estado, id_cliente, saldo_inicial, tipo_cuenta)
VALUES(N'963258741', 1, N'jluro', 10.0, N'CORRIENTE');