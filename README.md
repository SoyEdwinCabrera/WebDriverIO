# WebdriverIO Appium Test Project

Este proyecto es un conjunto de pruebas automatizadas para una aplicación móvil utilizando **Appium** y **JUnit 5**. Las pruebas están diseñadas para ejecutarse en un emulador o dispositivo Android y validan funcionalidades clave de la aplicación, como el inicio de sesión, manejo de errores y formularios.

## 🚀 **Descripción del Proyecto**

El objetivo de este proyecto es automatizar pruebas funcionales de una aplicación móvil utilizando Appium como framework de automatización y JUnit 5 como framework de pruebas. Las pruebas incluyen:

1. **Inicio de sesión exitoso:** Validación de credenciales correctas.
2. **Inicio de sesión fallido:** Validación de mensajes de error para credenciales incorrectas.
3. **Sección de formularios:** Validación de entrada de texto en un formulario.

El proyecto utiliza un enfoque modular para facilitar la reutilización del código y la configuración de pruebas.

[![Video de demostración](WebDriverIO/assets/WebDriverIO.gif)](WebDriverIO/assets/WebDriverIO.gif)

---

## 🛠️ **Herramientas y Tecnologías Utilizadas**

- **Java 23**: Lenguaje de programación principal.
- **JUnit 5**: Framework para la escritura y ejecución de pruebas.
- **Appium**: Framework de automatización para aplicaciones móviles.
- **Selenium WebDriver**: Biblioteca para interactuar con elementos de la interfaz de usuario.
- **Maven**: Herramienta de gestión de dependencias y construcción del proyecto.
- **Android Emulator**: Emulador de Android para ejecutar las pruebas.
- **Appium Java Client**: Biblioteca de cliente para interactuar con el servidor de Appium.

---

## 📂 **Estructura del Proyecto**

```
webdriverio/
├── src/
│   ├── main/
│   │   └── java/
│   └── test/
│       └── java/
│           └── org/
│               └── grupo5/
│                   └── AppTest.java
├── pom.xml
└── README.md

- **`AppTest.java`**: Contiene las pruebas automatizadas.
- **`pom.xml`**: Archivo de configuración de Maven para gestionar dependencias y plugins.

---

## ⚙️ **Configuración del Entorno**

### **Requisitos Previos**

1. **Java Development Kit (JDK)**: Asegúrate de tener instalado JDK 17 o superior.
2. **Maven**: Herramienta de construcción para gestionar dependencias.
3. **Appium**: Instala Appium globalmente:
   ```bash
   npm install -g appium
   ```
4. **Android SDK**: Configura un emulador o conecta un dispositivo físico.

### **Instalación**

1. Clona este repositorio:
   ```bash
   git clone https://github.com/SoyEdwinCabrera/WebDriverIO.git
   cd webdriverio
   ```

2. Instala las dependencias del proyecto:
   ```bash
   mvn clean install
   ```

3. Inicia el servidor de Appium:
   ```bash
   appium
   ```

4. Asegúrate de que el emulador o dispositivo Android esté en ejecución.

---

## 🧪 **Ejecución de Pruebas**

### **Ejecutar Todas las Pruebas**

Para ejecutar todas las pruebas definidas en `AppTest.java`, utiliza el siguiente comando:

```bash
mvn test
```

### **Ejecutar una Prueba Específica**

Para ejecutar una prueba específica, como `login`, utiliza:

```bash
mvn -Dtest=AppTest#login test
```

---

## 📋 **Pruebas Implementadas**

### **1. Prueba de Inicio de Sesión Exitoso (`login`)**
- **Descripción:** Valida que el usuario pueda iniciar sesión con credenciales correctas.
- **Pasos:**
  1. Abre la aplicación.
  2. Ingresa el correo electrónico y la contraseña correctos.
  3. Valida que se muestre un mensaje de éxito.

### **2. Prueba de Inicio de Sesión Fallido (`failedLogin`)**
- **Descripción:** Valida que se muestren mensajes de error cuando las credenciales son incorrectas.
- **Pasos:**
  1. Abre la aplicación.
  2. Ingresa un correo electrónico y contraseña inválidos.
  3. Valida que se muestren los mensajes de error correspondientes.

### **3. Prueba de Sección de Formularios (`seccionForms`)**
- **Descripción:** Valida que el texto ingresado en un formulario se muestre correctamente.
- **Pasos:**
  1. Navega a la sección de formularios.
  2. Ingresa texto en el campo de entrada.
  3. Valida que el texto ingresado se muestre correctamente.

---

## 🛠️ **Resolución de Problemas**

### **Error: `java.lang.NoSuchMethodError`**
- Asegúrate de que las versiones de **Appium Java Client** y **Selenium WebDriver** sean compatibles.
- Verifica las dependencias en el archivo `pom.xml`.

### **Error: `El driver no está inicializado`**
- Asegúrate de que el servidor de Appium esté en ejecución.
- Verifica que el emulador o dispositivo Android esté conectado y accesible.

---

## 📜 **Dependencias**

Las dependencias principales están definidas en el archivo `pom.xml`. Algunas de las más importantes son:

```xml
<dependencies>
    <!-- Selenium WebDriver -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.8.3</version>
    </dependency>

    <!-- Appium Java Client -->
    <dependency>
        <groupId>io.appium</groupId>
        <artifactId>java-client</artifactId>
        <version>8.3.0</version>
    </dependency>

    <!-- JUnit 5 -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.8.2</version>
        <scope>test</scope>
    </dependency>
</dependencies>
```

---

## 👨‍💻 **Contribuciones**

Si deseas contribuir a este proyecto, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama para tus cambios:
   ```bash
   git checkout -b feature/nueva-funcionalidad
   ```
3. Realiza tus cambios y haz un commit:
   ```bash
   git commit -m "Descripción de los cambios"
   ```
4. Envía tus cambios:
   ```bash
   git push origin feature/nueva-funcionalidad
   ```
5. Abre un Pull Request en el repositorio original.
