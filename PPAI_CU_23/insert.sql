INSERT INTO empleados (nombre, apellido, mail, telefono) VALUES ('Juan', 'Pérez', 'juan.perez@sismos.com', '555-0101');
INSERT INTO empleados (nombre, apellido, mail, telefono) VALUES ('María', 'González', 'maria.gonzalez@sismos.com', '555-0102');
INSERT INTO empleados (nombre, apellido, mail, telefono) VALUES ('Carlos', 'Rodríguez', 'carlos.rodriguez@sismos.com', '555-0103');

INSERT INTO usuarios (nombre_usuario, contraseña, empleado_id) VALUES ('juancete', '123456', 1);
INSERT INTO usuarios (nombre_usuario, contraseña, empleado_id) VALUES ('ks', '123456', 2);
INSERT INTO usuarios (nombre_usuario, contraseña, empleado_id) VALUES ('vegetta', '123456', 3);

INSERT INTO tipos_de_dato (denominacion, nombre_unidad_medida, valor_umbral) VALUES ('Velocidad_De_Onda', 'km_s', 5.0);
INSERT INTO tipos_de_dato (denominacion, nombre_unidad_medida, valor_umbral) VALUES ('Frecuencia_De_Onda', 'Hz', 20.0);
INSERT INTO tipos_de_dato (denominacion, nombre_unidad_medida, valor_umbral) VALUES ('Longitud_De_Onda', 'm', 100.0);

INSERT INTO alcances_sismo (nombre, descripcion) VALUES ('Local', NULL);
INSERT INTO alcances_sismo (nombre, descripcion) VALUES ('Regional', NULL);
INSERT INTO alcances_sismo (nombre, descripcion) VALUES ('Global', NULL);

INSERT INTO clasificaciones_sismo (nombre, km_profundidad_desde, km_profundidad_hasta) VALUES ('Leve', 0.0, 70.0);
INSERT INTO clasificaciones_sismo (nombre, km_profundidad_desde, km_profundidad_hasta) VALUES ('Moderado', 70.1, 300.0);
INSERT INTO clasificaciones_sismo (nombre, km_profundidad_desde, km_profundidad_hasta) VALUES ('Profundo', 300.1, 700.0);

INSERT INTO origenes_de_generacion (nombre, descripcion) VALUES ('Tectónico', 'Generado por el movimiento de placas tectónicas.');
INSERT INTO origenes_de_generacion (nombre, descripcion) VALUES ('Volcánico', 'Generado por actividad volcánica, como erupciones o movimiento de magma.');
INSERT INTO origenes_de_generacion (nombre, descripcion) VALUES ('Antrópico', 'Generado por actividad humana, como explosiones mineras o pruebas nucleares.');

INSERT INTO magnitudes_richter (numero, descripcion_magnitud) VALUES (1, 'Micro - No se siente');
INSERT INTO magnitudes_richter (numero, descripcion_magnitud) VALUES (2, 'Menor - Generalmente no se siente');
INSERT INTO magnitudes_richter (numero, descripcion_magnitud) VALUES (3, 'Ligero - Se siente, pero rara vez causa daños');
INSERT INTO magnitudes_richter (numero, descripcion_magnitud) VALUES (4, 'Ligero - Movimiento de objetos, daños menores');
INSERT INTO magnitudes_richter (numero, descripcion_magnitud) VALUES (5, 'Moderado - Daños en edificios débiles');
INSERT INTO magnitudes_richter (numero, descripcion_magnitud) VALUES (6, 'Fuerte - Daños en una población');
INSERT INTO magnitudes_richter (numero, descripcion_magnitud) VALUES (7, 'Mayor - Daños serios en grandes áreas');

INSERT INTO estaciones_sismologicas (nombre, latitud, longitud, codigo_estacion) VALUES ('Estacion Salta', -24.78, -65.41, 'SLT01');
INSERT INTO estaciones_sismologicas (nombre, latitud, longitud, codigo_estacion) VALUES ('Estacion Cordoba', -31.42, -64.18, 'CBA01');
INSERT INTO estaciones_sismologicas (nombre, latitud, longitud, codigo_estacion) VALUES ('Estacion Buenos aires', -34.60, -58.38, 'BUE01');

INSERT INTO sismografos (identificador_sismografo, nro_serie, fecha_adquisicion, estacion_sismologica_id) VALUES ('SISM-SLT-001', 'SN-A1B2', '2022-01-10 00:00:00', 1);
INSERT INTO sismografos (identificador_sismografo, nro_serie, fecha_adquisicion, estacion_sismologica_id) VALUES ('SISM-CBA-001', 'SN-C3D4', '2022-02-15 00:00:00', 2);
INSERT INTO sismografos (identificador_sismografo, nro_serie, fecha_adquisicion, estacion_sismologica_id) VALUES ('SISM-BUE-001', 'SN-E5F6', '2022-03-20 00:00:00', 3);

INSERT INTO series_temporales (condicion_alarma, fecha_hora_inicio_registro, fecha_hora_registro, frecuencia_muestreo, estado_id, sismografo_id) VALUES ('Alerta Nivel 1', '2024-05-20 14:30:00', '2024-05-20 14:30:00', 100.0, NULL, 1);
INSERT INTO muestras_sismicas (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-05-20 14:30:01', 1);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (5.2, 1, 1);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (20.0, 2, 1);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (520, 3, 1);
INSERT INTO muestras_sismicas (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-05-20 14:32:12', 1);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (6.3, 1, 2);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (22.3, 2, 2);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (370, 3, 2);

INSERT INTO series_temporales (condicion_alarma, fecha_hora_inicio_registro, fecha_hora_registro, frecuencia_muestreo, estado_id, sismografo_id) VALUES ('Alerta Nivel 2', '2024-05-18 14:30:00', '2024-05-18 14:30:00', 100.0, NULL, 2);
INSERT INTO muestras_sismicas (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-05-18 14:30:01', 2);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (8.7, 1, 3);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (35.0, 2, 3);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (700, 3, 3);
INSERT INTO muestras_sismicas (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-05-18 14:33:05', 2);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (8.2, 1, 4);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (32, 2, 4);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (675, 3, 4);

INSERT INTO series_temporales (condicion_alarma, fecha_hora_inicio_registro, fecha_hora_registro, frecuencia_muestreo, estado_id, sismografo_id) VALUES ('Alerta Nivel 3', '2024-05-25 14:30:00', '2024-05-25 14:30:00', 100.0, NULL, 3);
INSERT INTO muestras_sismicas (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-05-25 14:30:01', 3);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (10.3, 1, 5);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (25, 2, 5);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (340, 3, 5);
INSERT INTO muestras_sismicas (fecha_hora_muestra, serie_temporal_id) VALUES ('2024-05-25 14:33:01', 3);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (9.2, 1, 6);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (28.2, 2, 6);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (390, 3, 6);

INSERT INTO series_temporales (condicion_alarma, fecha_hora_inicio_registro, fecha_hora_registro, frecuencia_muestreo, estado_id, sismografo_id) VALUES ('Alerta Nivel 3', '2023-08-12 00:00:00', '2023-08-12 00:00:00', 100.0, NULL, 3);
INSERT INTO muestras_sismicas (fecha_hora_muestra, serie_temporal_id) VALUES ('2023-08-12 00:00:01', 4);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (9.2, 1, 7);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (30, 2, 7);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (540, 3, 7);
INSERT INTO muestras_sismicas (fecha_hora_muestra, serie_temporal_id) VALUES ('2023-08-12 00:05:01', 4);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (9.3, 1, 8);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (28.9, 2, 8);
INSERT INTO detalles_muestra_sismica (valor, tipo_de_dato_id, muestra_sismica_id) VALUES (399, 3, 8);

INSERT INTO estados (nombre, ambito) VALUES ('Auto_Detectado', 'Evento_Sismico');
INSERT INTO estado_auto_detectado (id) VALUES (1);
INSERT INTO eventos_sismicos (fecha_hora_ocurrencia, latitud_hipocentro, longitud_hipocentro, latitud_epicentro, longitud_epicentro, valor_magnitud, clasificacion_sismo_id, origen_de_generacion_id, alcance_sismo_id, estado_id, magnitud_richter_id) VALUES ('2024-05-20 14:30:01', -30.5, -31.2, -64.5, -59.4, 2.5, 1, 1, 1, 1, 3);
INSERT INTO cambios_de_estado (fecha_hora_inicio, fecha_hora_fin, estado_id, empleado_id, evento_sismico_id, sismografo_id) VALUES ('2024-05-20 14:30:01', NULL, 1, 1, 1, NULL);
INSERT INTO evento_sismico_series (evento_sismico_id, serie_temporal_id) VALUES (1, 1);

INSERT INTO estados (nombre, ambito) VALUES ('Auto_Detectado', 'Evento_Sismico');
INSERT INTO estado_auto_detectado (id) VALUES (2);
INSERT INTO eventos_sismicos (fecha_hora_ocurrencia, latitud_hipocentro, longitud_hipocentro, latitud_epicentro, longitud_epicentro, valor_magnitud, clasificacion_sismo_id, origen_de_generacion_id, alcance_sismo_id, estado_id, magnitud_richter_id) VALUES ('2024-05-18 14:30:01', 21.9, 23.4, 37.1, 31.9, 1.8, 2, 2, 2, 2, 2);
INSERT INTO cambios_de_estado (fecha_hora_inicio, fecha_hora_fin, estado_id, empleado_id, evento_sismico_id, sismografo_id) VALUES ('2024-05-18 14:30:01', NULL, 2, 2, 2, NULL);
INSERT INTO evento_sismico_series (evento_sismico_id, serie_temporal_id) VALUES (2, 2);

INSERT INTO estados (nombre, ambito) VALUES ('Auto_Detectado', 'Evento_Sismico');
INSERT INTO estado_auto_detectado (id) VALUES (3);
INSERT INTO eventos_sismicos (fecha_hora_ocurrencia, latitud_hipocentro, longitud_hipocentro, latitud_epicentro, longitud_epicentro, valor_magnitud, clasificacion_sismo_id, origen_de_generacion_id, alcance_sismo_id, estado_id, magnitud_richter_id) VALUES ('2024-05-25 14:30:01', -103.6, -104.9, -99.5, -92.7, 0.6, 3, 3, 3, 3, 1);
INSERT INTO cambios_de_estado (fecha_hora_inicio, fecha_hora_fin, estado_id, empleado_id, evento_sismico_id, sismografo_id) VALUES ('2024-05-25 14:30:01', NULL, 3, 3, 3, NULL);
INSERT INTO evento_sismico_series (evento_sismico_id, serie_temporal_id) VALUES (3, 3);

INSERT INTO estados (nombre, ambito) VALUES ('Auto_Confirmado', 'Evento_Sismico');
INSERT INTO estado_auto_confirmado (id) VALUES (4);
INSERT INTO eventos_sismicos (fecha_hora_ocurrencia, latitud_hipocentro, longitud_hipocentro, latitud_epicentro, longitud_epicentro, valor_magnitud, clasificacion_sismo_id, origen_de_generacion_id, alcance_sismo_id, estado_id, magnitud_richter_id) VALUES ('2023-08-12 00:00:01', -33.5, -33.4, -69.5, -69.4, 0.6, 3, 3, 3, 4, 1);
INSERT INTO cambios_de_estado (fecha_hora_inicio, fecha_hora_fin, estado_id, empleado_id, evento_sismico_id, sismografo_id) VALUES ('2023-08-12 00:00:01', NULL, 4, 3, 4, NULL);
INSERT INTO evento_sismico_series (evento_sismico_id, serie_temporal_id) VALUES (4, 4);

INSERT INTO estados (nombre, ambito) VALUES ('Pendiente_De_Revision', 'Evento_Sismico');
INSERT INTO estado_pendiente_de_revision (id) VALUES (5);
INSERT INTO eventos_sismicos (fecha_hora_ocurrencia, latitud_hipocentro, longitud_hipocentro, latitud_epicentro, longitud_epicentro, valor_magnitud, clasificacion_sismo_id, origen_de_generacion_id, alcance_sismo_id, estado_id, magnitud_richter_id) VALUES ('2024-05-20 14:30:00', -18.5, -18.0, -71.0, -70.5, 35.5, 1, 2, 1, 5, 2);
INSERT INTO cambios_de_estado (fecha_hora_inicio, fecha_hora_fin, estado_id, empleado_id, evento_sismico_id, sismografo_id) VALUES ('2024-05-20 14:30:00', NULL, 5, 3, 5, NULL);
INSERT INTO evento_sismico_series (evento_sismico_id, serie_temporal_id) VALUES (5, 4);

INSERT INTO sesiones (fecha_hora_logeo, fecha_hora_deslogeo, usuario_id) VALUES ('2025-11-10 20:09:00', NULL, 3);
