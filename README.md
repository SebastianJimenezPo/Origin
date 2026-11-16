# Origin

Proyecto académico de Programación Orientada a Objetos (POO) en Java. Origin modela una billetera digital con distintos productos financieros y mecanismos simulados de autenticación para las transacciones.

## Estado actual

- Gestión de usuarios y billeteras con una colección de cuentas (`ArrayList`).
- Cuentas de ahorros, corriente e inversión: metas, sobregiro y cálculo de rendimientos.
- Depósitos, retiros, transferencias y comisiones según el tipo de cuenta.
- Lectores simulados de huella, reconocimiento facial y token NFC.
- Ejemplo ejecutable de cuatro transacciones desde `Origin.main()`.

**Alcance:** prototipo académico en memoria. No maneja dinero real, no usa sensores biométricos ni protocolos criptográficos reales. La autenticación actual usa un lector por operación y su exigencia se indica al construir la transacción. La autenticación multifactor, evaluación automática de riesgo y persistencia no están implementadas. No es apto para producción.

## Requisitos y ejecución

- JDK 21.
- Maven 3.9+ para compilar con el proyecto Maven, o NetBeans con soporte Maven.

Desde la raíz del repositorio:

```bash
mvn clean compile
java -cp target/classes poo.origin.Origin
```

También puede abrirse `pom.xml` como proyecto Maven en NetBeans y ejecutar la clase `poo.origin.Origin`.

Alternativa sin Maven (terminal con JDK):

```bash
javac -encoding UTF-8 -d out src/main/java/poo/origin/*.java
java -cp out poo.origin.Origin
```

La demostración parte de cuentas con saldo cero y ejecuta un depósito en ahorros de 5000, una transferencia de 1000 a corriente con huella, un retiro de 300 con reconocimiento facial y un depósito de 2000 en inversión con NFC. Los saldos finales esperados son 4000, 699.10 y 2000, respectivamente. Los datos son ficticios y los montos ilustrativos.

## Estructura

```text
Origin/
├── README.md
├── pom.xml
├── .gitignore
├── docs/
│   ├── ARQUITECTURA.md
│   └── ROADMAP.md
└── src/main/java/poo/origin/
    └── 13 clases Java
```

- [Arquitectura y aplicación de POO](docs/ARQUITECTURA.md)
- [Roadmap y limitaciones conocidas](docs/ROADMAP.md)

El código fuente de esta versión se conserva tal como fue entregado por el equipo.
