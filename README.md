# CajeroSpring

Proyecto de **Cajero Automático** desarrollado en **Java** con **Spring Boot** y **MySQL**, que permite gestionar clientes, retiros, transferencias y control de billetes en un cajero realista.

---

## 🔹 Descripción del Proyecto

Este proyecto simula un **cajero automático** con las siguientes funcionalidades:

- **Login seguro**: Acceso con documento y clave de 4 dígitos.
- **Control de intentos**: Máximo 3 intentos fallidos antes de bloquear al usuario por 10 minutos.
- **Transacciones**: Retiros y transferencias entre clientes con actualización de saldo en tiempo real.
- **Gestión de efectivo**: Control de **estado del cajero**, mostrando la disponibilidad de billetes por denominación.
- **Persistencia**: Integración con **MySQL** para que los datos perduren tras reiniciar la aplicación.
- **Seguridad de sesión**: Cierre automático si el cliente no interactúa durante 180 segundos.

El proyecto está diseñado como una API REST, lista para conectarse a una interfaz gráfica (JavaFX/Swing) o consumirse desde **Postman/Frontend web**.

---

## 🔹 Tecnologías Usadas

* **Java 21**
* **Spring Boot 4**
* **Spring Data JPA / Hibernate**
* **MySQL** (Base de datos relacional)
* **DBeaver** (Administración de DB)
* **Maven** (Gestión de dependencias)
* **Postman** (Pruebas de Endpoints)

---

## 🔹 Estructura del Proyecto

```text
CajeroSpring/
├── src/                   # Código fuente Spring Boot
│   ├── main/
│   │   ├── java/
│   │   │   └── com.app_cajero.cajero/
│   │   │       ├── controller/     # Controladores REST
│   │   │       ├── dto/            # DTOs para requests
│   │   │       ├── model/          # Entidades JPA
│   │   │       └── service/        # Lógica de negocio
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml                 # Configuración Maven
├── database/
│   └── CajeroDB.sql         # Script de estructura y datos iniciales
├── documentación/
│   └── CajeroSpring.pdf     # Guía y capturas de Postman
└── README.md


```

## 🔹 Endpoints Principales (REST API)

* /api/auth/login	POST	Login de cliente (documento y clave)
* /api/cajero/retiro	POST	Retiro de dinero por cliente
* /api/cajero/transferencia	POST	Transferencia entre clientes
* /api/cajero/estado	GET	Mostrar billetes disponibles en el cajero


## 🔹 Ejemplos de JSON (Payloads)
* Login JSON
```
{
  "documento": "123",
  "clave": "1111"
}
```
* Retiro JSON
```
{
  "documento": "123",
  "valor": 200000
}
```
* Transferencia JSON
```
{
  "origen": "123",
  "destino": "456",
  "valor": 150000
}
```
## 🔹 Futuras Mejoras

Implementar interfaz gráfica con JavaFX o entorno Web (React/Angular). Módulo de administración para registro de nuevos usuarios. Visualización gráfica (dashboards) del inventario de billetes. Historial detallado de transacciones (Log de auditoría).


## 🔹 Autor

Wilmer Andrés Pacheco Abaunza