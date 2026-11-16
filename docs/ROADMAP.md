# Roadmap de Origin

Este documento distingue la versión original de las mejoras propuestas. Ninguna tarea pendiente se considera implementada por estar descrita aquí.

## Base actual

- [x] Modelo de usuarios, billeteras y cuentas con colecciones de objetos.
- [x] Jerarquías de cuentas y lectores mediante clases abstractas y polimorfismo.
- [x] Depósitos, retiros, transferencias, comisiones, metas, sobregiro y rendimientos.
- [x] Autenticación simulada con un lector por transacción.
- [x] Demostración de cuatro operaciones en la clase `Origin`.

## Fase 1: corregir validaciones y autorización

- [ ] Vincular las credenciales con los usuarios autorizados para operar las cuentas.
- [ ] Conceder los beneficios de autenticación fuerte únicamente tras una validación exitosa.
- [ ] Impedir que un retiro o transferencia evada la autorización mediante el booleano recibido por el constructor o por métodos financieros directos.
- [ ] Consolidar la lógica de débitos, créditos y comisiones, hoy repetida entre `Transaccion` y `CuentaBancaria`.
- [ ] Rechazar montos no finitos, inválidos o no positivos; definir precisión monetaria y redondeo.
- [ ] Validar estados de objetos, referencias requeridas y coherencia entre el estado de la billetera y sus cuentas.

Criterio de aceptación: operaciones no autorizadas, credenciales inválidas y montos inválidos no deben modificar saldos ni conceder descuentos de comisión.

## Fase 2: seguridad adaptativa y multifactor

- [ ] Definir una política de riesgo según tipo y monto de operación, con umbrales académicos explícitos.
- [ ] Determinar los requisitos de autorización dentro del sistema.
- [ ] Modelar varias verificaciones asociadas a una misma transacción y requerir factores distintos cuando corresponda (p. ej., biometría y token de posesión simulado).
- [ ] Mantener el carácter simulado de los lectores; no afirmar integración con hardware o estándares criptográficos sin implementarla y verificarla.

Criterio de aceptación: una operación de alto riesgo solo se ejecuta cuando todos los factores exigidos por la política se validan correctamente.

## Fase 3: funcionalidad y pruebas

- [ ] Añadir historial de transacciones y consulta de sus estados.
- [ ] Crear un menú interactivo en consola para operar sin modificar `main()`.
- [ ] Incorporar pruebas automatizadas de operaciones válidas, fondos insuficientes, sobregiro, credenciales vencidas, lectores inactivos, autorización insuficiente, cancelación y ejecuciones repetidas.
- [ ] Documentar la ejecución y actualizar la arquitectura conforme evolucione el código.

La persistencia de datos y la integración real con sensores o proveedores de autenticación se reservan como posibles extensiones futuras, fuera del alcance de la versión original.
