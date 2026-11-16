# Arquitectura de Origin

## Organización actual

Las 13 clases se encuentran en `poo.origin`. La aplicación ejecuta un escenario de demostración en memoria; no existe una capa de persistencia ni una interfaz interactiva.

| Clase | Responsabilidad |
| --- | --- |
| `Origin` | Punto de entrada; crea los objetos y ejecuta las cuatro transacciones de ejemplo. |
| `Usuario` | Identificador, nombre, correo y referencia a una billetera. |
| `Billetera` | Administración de cuentas mediante `ArrayList<CuentaBancaria>`: agregar, buscar, eliminar y listar. |
| `CuentaBancaria` | Clase abstracta; saldo, titular, estado, depósitos, retiros, transferencias y contrato de comisiones. |
| `CuentaAhorros` | Meta y progreso de ahorro; comisión propia. |
| `CuentaCorriente` | Cupo de sobregiro; especialización del débito y de la comisión. |
| `CuentaInversion` | Tasa y plazo; cálculo de rendimiento y comisión. |
| `Transaccion` | Tipo, monto, fecha, estado, cuentas de origen/destino y autenticación; coordinación de la ejecución. |
| `CredencialAutenticacion` | Tipo, identificador, fecha de expiración y nivel de coincidencia. |
| `LectorAutenticacion` | Clase abstracta; identificador, nivel, estado y contrato de validación. |
| `LectorHuella` | Validación simulada de tipo HUELLA y vigencia, si el lector está activo. |
| `LectorReconocimientoFacial` | Validación simulada de tipo FACIAL, vigencia y coincidencia mínima. |
| `LectorTokenNFC` | Validación simulada de tipo NFC y vigencia, si el lector está activo. |

## Relaciones entre objetos

- Herencia: las tres cuentas concretas extienden `CuentaBancaria`; los tres lectores concretos extienden `LectorAutenticacion`.
- Agregación: `Billetera` conserva referencias a cuentas creadas independientemente y admite cero o más cuentas.
- Asociación: `Usuario` recibe una `Billetera`; `Transaccion` conserva referencias a cuentas, lector y credencial, que pueden ser nulas según la operación. La implementación de `Usuario.mostrarInformacion()` presupone una billetera no nula.
- Dependencia: el método `LectorAutenticacion.validarIdentidad()` recibe una `CredencialAutenticacion` como argumento.

## Aplicación de los cuatro pilares de POO

- **Abstracción:** las clases base abstractas `CuentaBancaria` y `LectorAutenticacion` definen contratos comunes para sus especializaciones.
- **Encapsulamiento:** los atributos son privados y el saldo se modifica mediante operaciones. La lista devuelta por `Billetera.getCuentas()` es una copia superficial de la colección. Todavía se deben reforzar los límites de autorización.
- **Herencia:** las especializaciones reutilizan atributos y comportamiento de las clases padre a través de `extends` y `super`.
- **Polimorfismo:** `calcularComision()` y `validarIdentidad()` se implementan en las subclases y se invocan mediante referencias a las clases base. `CuentaCorriente` también sobrescribe `puedeDebitar()`.

Otros conceptos: constructores y sobrecarga de constructores de `CredencialAutenticacion`, métodos con parámetros y retorno, modificadores de acceso, `@Override`, colecciones, fechas y estados.

## Flujo actual de una transacción

1. Crear una `Transaccion` con tipo, monto, cuentas y requisito de autenticación indicado por el llamador.
2. Verificar que esté pendiente y que el monto sea positivo (la validación actual no detecta todos los valores no finitos).
3. Si se exige autenticación, validar la credencial con el lector asociado.
4. Clasificar autenticación fuerte según el nivel del lector (actualmente no exige que se haya validado exitosamente).
5. Ejecutar el depósito o calcular la comisión, comprobar fondos y aplicar el débito/crédito según el tipo de operación.
6. Cambiar el estado a EXITOSA o RECHAZADA. Una transacción pendiente también puede cancelarse.

## Reglas financieras implementadas

| Cuenta | Comisión ordinaria | Comisión fuerte |
| --- | ---: | ---: |
| Ahorros | 0,5 % | 0 % |
| Corriente | 0,8 % | 0,3 % |
| Inversión | 1 % | 0,5 % |

Las comisiones se aplican a retiros y transferencias. Los depósitos no tienen comisión en el flujo actual. La cuenta corriente permite débitos hasta el límite de sobregiro; la inversión calcula rendimiento simple como saldo × tasa × días / 360. Los niveles simulados de autenticación son NFC 1, huella 2 y facial 3; el código considera fuerte un nivel mayor o igual a 2. Estos valores son reglas internas del prototipo, no estándares de seguridad o tarifas bancarias reales.
