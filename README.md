# 🛒 API de Compras con Roles y Carrito - Spring Boot

[![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green?style=for-the-badge)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge)](https://www.postgresql.org/)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-Auth-red?style=for-the-badge)](https://spring.io/projects/spring-security)
[![JWT](https://img.shields.io/badge/JWT-Token-yellow?style=for-the-badge)](https://jwt.io/)
[![Lombok](https://img.shields.io/badge/Lombok-Annotations-green?style=for-the-badge)](https://projectlombok.org/)
[![Maven](https://img.shields.io/badge/Maven-Dependency-orange?style=for-the-badge)](https://maven.apache.org/)

🚀 **API REST de compras** desarrollada con **Java y Spring Boot**, que implementa **JSON Web Token (JWT)** para la autenticación y autorización de usuarios con diferentes roles (**ADMIN y CLIENTE**).  

📌 Funcionalidades principales:
- 🔐 **Autenticación con JWT**
- 👥 **Roles de usuario (ADMIN y CLIENTE)**
- 🛍️ **Gestión de productos (CRUD)**
- 🛒 **Carrito de compras (añadir/eliminar productos)**
- 📦 **Base de datos PostgreSQL**
- 🔑 **Cifrado de contraseñas con BCrypt**

---

## 🛒 ¿Cómo funciona la aplicación?

### 🔐 1. Autenticación y Roles de Usuario
La API implementa **Spring Security** y **JWT** para gestionar la autenticación y autorización.  
Existen **dos roles principales**:

- **ADMIN** 🛠️: Puede crear, actualizar y eliminar productos.
- **CLIENTE** 🛍️: Puede ver productos, añadir productos al carrito y realizar compras.

#### 📌 Flujo de autenticación:
1. Un usuario se **registra** (`POST /api/users`).
2. Luego, inicia sesión (`POST /api/auth/login`).
3. Si las credenciales son correctas, recibe un **token JWT**.
4. Para acceder a los endpoints protegidos, debe enviar este token en cada solicitud como **Bearer Token** en el header `Authorization`.

## 🛍️ 2. Gestión de Productos
- Un **ADMIN** puede **crear, actualizar y eliminar** productos.
- Un **CLIENTE** solo puede **ver** los productos disponibles.

| Método  | Endpoint                   | Descripción                 | Rol |
|---------|----------------------------|-----------------------------|-----------|
| `GET`   | `/api/products`            | Listar todos los productos  | ✅ Cliente/Admin |
| `GET`   | `/api/products/{id}`       | Obtener producto por ID     | ✅ Cliente/Admin |
| `POST`  | `/api/products`            | Crear un nuevo producto     | ✅ Admin |
| `PUT`   | `/api/products/{id}`       | Actualizar un producto      | ✅ Admin |
| `DELETE`| `/api/products/{id}`       | Eliminar un producto        | ✅ Admin |
## 🛒 3. Carrito de Compras
Los clientes pueden agregar productos a su carrito y gestionar su compra:
| Método  | Endpoint                                      | Descripción                         | Rol |
|---------|----------------------------------------------|-------------------------------------|-----------|
| `POST`  | `/api/cart/{userId}/add/{productId}/{quantity}` | Agregar producto al carrito        | ✅ Cliente |
| `DELETE`| `/api/cart/{userId}/remove/{productId}`     | Eliminar producto del carrito      | ✅ Cliente |
| `GET`   | `/api/cart/{userId}`                        | Ver contenido del carrito          | ✅ Cliente |
| `DELETE`| `/api/cart/{userId}/clear`                  | Vaciar el carrito    |✅ Cliente|

## 👥 5. Gestión de Usuarios
| Método  | Endpoint          | Descripción                 | Rol |
|---------|------------------|-----------------------------|-----------|
| `POST`  | `/api/users`      | Registrar un nuevo usuario  | ❌ Público |
| `GET`   | `/api/users`      | Listar todos los usuarios   | ✅ Protegido con JWT |
