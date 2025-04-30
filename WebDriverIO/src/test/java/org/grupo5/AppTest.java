package org.grupo5;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;
import java.util.Collections;

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
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

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
    private final String WDIOFORMBUTTONACTIVE = "button-Active";
    private final String WDIOFORMACTIVEMESSAGE = "android:id/message";
    private final String WDIOFORMACTIVEEXPECTEDMESSAGE = "This button is active";
    private final String WDIOFORMACTIVEMESSAGEOKBUTTON = "android:id/button1";

    private final String WDIOSWIPE = "Swipe";
    private final String WDIOSWIPEJSTITLE = "//android.widget.TextView[@text=\"JS.FOUNDATION\"]";
    private final String WDIOSWIPEJSEXPECTEDTITLE = "JS.FOUNDATION";

    private final String WDIOWEBVIEW = "Webview";
    private final String WDIOWEBVIEWTOGGLE = "//android.widget.Button[@text=\"Toggle navigation bar\"]";
    private final String WDIOWEBVIEWLANGUAGE = "//android.widget.Button[@text=\"Languages\"]";
    private final String WDIOWEBVIEWSPANISH = "//android.view.View[@text=\"Main\"]/android.view.View[3]/android.view.View[2]/android.view.View[1]/android.widget.ListView/android.view.View[8]/android.widget.ListView/android.view.View[3]";
    private final String WDIOWEBVIEWSPANISHTITLE = "//android.widget.TextView[@text=\"Marco de prueba de automatización móvil y navegador de próxima generación para Node.js\"]";
    private final String WDIOWEBVIEWEXPECTEDSPANISHTITLE = "Marco de prueba de automatización móvil y navegador de próxima generación para Node.js";

    private final String WDIOWEBVIEWETWITTER = "@webdriverio on Twitter";

    private final String WDIODRAG = "Drag";
    private final int[][] WDIODRAGCOORDS = {
            { 631, 1912, 539, 631 },
            { 806, 1912, 530, 1078 },
            { 954, 1912, 728, 852 },
            { 129, 1912, 327, 852 },
            { 290, 1912, 750, 1087 },
            { 447, 1912, 756, 645 },
            { 364, 2078, 534, 861 },
            { 548, 2068, 322, 654 },
            { 696, 2091, 309, 1073 }
    };
    private final String WDIODRAGMESSAGE = "//android.widget.TextView[@text=\"You made it, click retry if you want to try it again.\"]";
    private final String WDIODRAGEXPECTEDMESSAGE = "You made it, click retry if you want to try it again.";

    private AndroidDriver createConnection() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(DEVICENAME);
        options.setPlatformName(PLATFORMNAME);
        options.setUdid(DEVICENAME);
        URL appiumServerURL = URI.create(APPIUMURL).toURL();
        return new AndroidDriver(appiumServerURL, options);
    }

    @BeforeEach
    public void setUp() throws MalformedURLException, InterruptedException {
        // System.out.println("Configurando el entorno de prueba...");
        driver = createConnection();
        // System.out.println("Entorno de prueba configurado.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null)
            driver.quit();
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
        // System.out.println("Esperando " + seconds + " segundos...");
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void login() {
        System.out.println("""
        
            ****************************************
            Iniciando prueba: login
            ****************************************
            """);
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
        System.out.println("""
            
            ****************************************
            Iniciando prueba: failedLogin
            ****************************************
            """);
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
        System.out.println("""
            
            ****************************************
            Iniciando prueba: seccionForms
            ****************************************
            """);
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

    @Test
    @Order(4)
    public void buttonActive() {
        System.out.println("""
            
            ****************************************
            Iniciando prueba: buttonActive
            ****************************************
            """);
        waitSeconds(1);
        driver.findElement(AppiumBy.accessibilityId(WDIOFORMBUTTONACTIVE)).click();
        waitSeconds(1);
        String messageText = driver.findElement(AppiumBy.id(WDIOFORMACTIVEMESSAGE)).getText();
        waitSeconds(1);
        assertTrue(WDIOFORMACTIVEEXPECTEDMESSAGE.equals(messageText));
        waitSeconds(1);
        driver.findElement(AppiumBy.id(WDIOFORMACTIVEMESSAGEOKBUTTON)).click();
    }

    @Test
    @Order(5)
    public void swipe() {
        System.out.println("""
            
            ****************************************
            Iniciando prueba: swipe
            ****************************************
            """);
        driver.findElement(AppiumBy.accessibilityId(WDIOSWIPE)).click();
        waitSeconds(1);
        final PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 900, 1650));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), 180, 1650));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
        driver.perform(Collections.singletonList(swipe));
        String jsTittle = driver.findElement(AppiumBy.xpath(WDIOSWIPEJSTITLE)).getText();
        assertTrue(jsTittle.equals(WDIOSWIPEJSEXPECTEDTITLE));
    }

    @Test
    @Order(6)
    public void changeLanguage() {
        System.out.println("""
            
            ****************************************
            Iniciando prueba: changeLanguage
            ****************************************
            """);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.findElement(AppiumBy.accessibilityId(WDIOWEBVIEW)).click();
        driver.findElement(AppiumBy.xpath(WDIOWEBVIEWTOGGLE)).click();
        driver.findElement(AppiumBy.xpath(WDIOWEBVIEWLANGUAGE)).click();
        driver.findElement(AppiumBy.xpath(WDIOWEBVIEWSPANISH)).click();
        String spanishTitle = driver.findElement(AppiumBy.xpath(WDIOWEBVIEWSPANISHTITLE)).getText();
        assertTrue(spanishTitle.equals(WDIOWEBVIEWEXPECTEDSPANISHTITLE));
    }

    @Test
    @Order(7)
    public void changeContextToTwitter() {
        System.out.println("""
            
            ****************************************
            Iniciando prueba: changeContextToTwitter
            ****************************************
            """);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.findElement(AppiumBy.xpath(WDIOWEBVIEWTOGGLE)).click();
        driver.findElement(AppiumBy.accessibilityId(WDIOWEBVIEWETWITTER)).click();
        waitSeconds(30);
        driver.context("WEBVIEW_chrome");
        assertTrue(driver.getCurrentUrl().equals("https://x.com/i/flow/login?redirect_after_login=%2Fwebdriverio"));
    }

    private void dragAndDrop(int x1, int y1, int x2, int y2) {
        final PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), x1, y1));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), x2, y2));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(swipe));
    }

    @Test
    @Order(8)
    public void dragAndDropPuzzle() {
        System.out.println("""
            
            ****************************************
            Iniciando prueba: dragAndDropPuzzle
            ****************************************
            """);
        driver.findElement(AppiumBy.accessibilityId(WDIODRAG)).click();
        waitSeconds(1);
        for (int[] coord : WDIODRAGCOORDS) {
            dragAndDrop(coord[0], coord[1], coord[2], coord[3]);
        }
        String puzzleMessage = driver.findElement(AppiumBy.xpath(WDIODRAGMESSAGE)).getText();
        assertTrue(puzzleMessage.equals(WDIODRAGEXPECTEDMESSAGE));
    }

}
