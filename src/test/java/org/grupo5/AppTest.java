package org.grupo5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.WebElement;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppTest {
    private AndroidDriver driver;

    private final String DEVICENAME = "emulator-5554";
    private final String PLATFORMNAME = "Android";
    private final String WDIOPACKAGE = "com.wdiodemoapp";
    private final String APPIUMURL = "http://127.0.0.1:4723";
    private final String DRIVERNOTINICIALIZEDERROR = "El driver no está inicializado.";
    
    private final String WDIOLOGINBUTTON = "//android.widget.TextView[@text=\"󰍂\"]";
    private final String WDIOLOGINEMAILINPUT = "input-email";
    private final String WDIOLOGINPASSWORDINPUT = "input-password";
    private final String WDIOLOGINSUBMIT = "button-LOGIN";
    private final String WDIOLOGINALERTTITLE = "android:id/alertTitle";
    private final String WDIOLOGINALERTMESSAGE = "android:id/message";
    private final String WDIOLOGINALERTOKBUTTON = "android:id/button1";
    private final String WDIOLOGINALERTTITLETEXT = "Success";
    private final String WDIOLOGINALERTMESSAGETEXT = "You are logged in!";
    private final String WDIOLOGINEMAILERROR = "Please enter a valid email address";
    private final String WDIOLOGINPASSWORDERROR = "Please enter at least 8 characters";
    
    private final String WDIOLOGININVALIDEMAILMESSAGE = "//android.widget.TextView[@text=\"Please enter a valid email address\"]";
    private final String WDIOLOGININVALIDPASSWORDMESSAGE = "//android.widget.TextView[@text=\"Please enter at least 8 characters\"]";
    
    private final String TYPEDTEXT = "un texto cualquiera";
    private final String WDIOFORMSBUTTON = "Forms";
    private final String WDIOINPUTFIELD = "text-input";
    private final String WDIOYOUHAVETYPEDFIELD = "input-text-result";
    
    private AndroidDriver createConnection() throws MalformedURLException {
        System.out.println("Creando conexión con Appium...");
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(DEVICENAME);
        options.setPlatformName(PLATFORMNAME);
        options.setUdid(DEVICENAME);
        URL appiumServerURL = URI.create(APPIUMURL).toURL();
        System.out.println("Conexión creada exitosamente.");
        return new AndroidDriver(appiumServerURL, options);
    }
    
    @BeforeEach
    public void setUp() throws MalformedURLException, InterruptedException {
        System.out.println("Configurando el entorno de prueba...");
        driver = createConnection();
        System.out.println("Entorno de prueba configurado.");
    }
    
    @AfterEach
    public void tearDown() {
        System.out.println("Cerrando el driver...");
        if (driver != null) {
            driver.quit();
            System.out.println("Driver cerrado correctamente.");
        } else {
            System.out.println("El driver ya estaba cerrado.");
        }
    }
    
    private void validateInitializedDriver() {
        if (driver == null) {
            throw new IllegalStateException(DRIVERNOTINICIALIZEDERROR);
        }
    }
    
    private void openApp(String appPackage) {
        System.out.println("Abriendo la aplicación: " + appPackage);
        validateInitializedDriver();
        driver.activateApp(appPackage);
        waitSeconds(1);
        System.out.println("Aplicación abierta.");
    }
    
    private void waitSeconds(int seconds) {
        System.out.println("Esperando " + seconds + " segundos...");
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    @Order(1)
    public void login() {
        System.out.println("Iniciando prueba: login");
        openApp(WDIOPACKAGE);
        waitSeconds(1);
        System.out.println("Haciendo clic en el botón de login...");
        driver.findElement(AppiumBy.xpath(WDIOLOGINBUTTON)).click();
        waitSeconds(1);
        System.out.println("Ingresando correo electrónico...");
        driver.findElement(AppiumBy.accessibilityId(WDIOLOGINEMAILINPUT)).sendKeys("admin@gmail.com");
        waitSeconds(1);
        System.out.println("Ingresando contraseña...");
        driver.findElement(AppiumBy.accessibilityId(WDIOLOGINPASSWORDINPUT)).sendKeys("12345678");
        waitSeconds(1);
        System.out.println("Haciendo clic en el botón de enviar...");
        driver.findElement(AppiumBy.accessibilityId(WDIOLOGINSUBMIT)).click();
        waitSeconds(3);
        System.out.println("Validando el mensaje de éxito...");
        String alertTitle = driver.findElement(AppiumBy.id(WDIOLOGINALERTTITLE)).getText();
        String alertMessage = driver.findElement(AppiumBy.id(WDIOLOGINALERTMESSAGE)).getText();
        assertTrue(alertTitle.equals(WDIOLOGINALERTTITLETEXT) && alertMessage.equals(WDIOLOGINALERTMESSAGETEXT));
        WebElement okButton = driver.findElement(AppiumBy.id(WDIOLOGINALERTOKBUTTON));
        okButton.click();
        System.out.println("Prueba de login completada exitosamente.");
    }
    
    @Test
    @Order(2)
    public void failedLogin() {
        System.out.println("Iniciando prueba: failedLogin");
        driver.findElement(AppiumBy.accessibilityId(WDIOLOGINEMAILINPUT)).sendKeys("admingmail.com");
        waitSeconds(1);
        driver.findElement(AppiumBy.accessibilityId(WDIOLOGINPASSWORDINPUT)).sendKeys("1234567");
        waitSeconds(1);
        driver.findElement(AppiumBy.accessibilityId(WDIOLOGINSUBMIT)).click();
        waitSeconds(3);
        System.out.println("Validando mensajes de error...");
        WebElement emailError = driver.findElement(AppiumBy.xpath(WDIOLOGININVALIDEMAILMESSAGE));
        String emailErrorText = emailError.getText();
        WebElement passwordError = driver.findElement(AppiumBy.xpath(WDIOLOGININVALIDPASSWORDMESSAGE));
        String passwordErrorText = passwordError.getText();
        assertTrue(emailErrorText.equals(WDIOLOGINEMAILERROR)
                && passwordErrorText.equals(WDIOLOGINPASSWORDERROR));
        assertTrue(emailError.isDisplayed() && passwordError.isDisplayed());
        System.out.println("Prueba de failedLogin completada exitosamente.");
    }
    
    @Test
    @Order(3)
    public void seccionForms() {
        System.out.println("Iniciando prueba: seccionForms");
        waitSeconds(1);
        WebElement formsButton = driver.findElement(AppiumBy.accessibilityId(WDIOFORMSBUTTON));
        formsButton.click();
        waitSeconds(1);
        System.out.println("Ingresando texto en el campo de formulario...");
        WebElement inputField = driver.findElement(AppiumBy.accessibilityId(WDIOINPUTFIELD));
        inputField.sendKeys(TYPEDTEXT);
        waitSeconds(1);
        WebElement youHaveTypedField = driver.findElement(AppiumBy.accessibilityId(WDIOYOUHAVETYPEDFIELD));
        assertEquals(youHaveTypedField.getText(), TYPEDTEXT);
        System.out.println("Prueba de seccionForms completada exitosamente.");
    }
}