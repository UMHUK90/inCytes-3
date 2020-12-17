package com.incytes.patient;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.SelectorMode.Sizzle;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.name;

public class PatientPA180A {
    public static String siteurl = "https://alpha-admin.incytesdata-dev.com/",
                         password = "Seamgen2";

    public void ClickCircles() {
        $(byText("Circles")).click();
    }
    public void EnterAdminEmail() {
        $(name("email")).setValue("amcfay@seamgen.com");
    }
    public void EnterAdminPassword() {
        $(name("password")).setValue(password);
    }
    public void clickAdminSignInButton() {
        $("button span").shouldHave(text("SIGN IN")).click();
    }

    @Test
    public void c_test() {
        Configuration.selectorMode = Sizzle;
        open(siteurl + "auth/login");
        EnterAdminEmail();
        EnterAdminPassword();
        clickAdminSignInButton();
        ClickCircles();
        $("button.MuiButtonBase-root.MuiIconButton-root").click();
        $("div.MuiPaper-elevation8 li:nth(3)").shouldHave(text("Generate Link")).click();
        $(byAttribute("var", "body1")).shouldHave(text("Link has been copied to your clipboard")).waitUntil(visible, 10000);
        $(byAttribute("placeholder", "Search here")).click();
        $(byAttribute("placeholder", "Search here")).sendKeys(Keys.chord(Keys.CONTROL, "v")); //paste to user input from clipboard;
        $(byValue("https://qa-patient-enrollment.incytesdata-dev.com?data=AQICAHiS8bUkRZ0s5S33cYjODi7mojFV42+YC31lL/q0DzjmlgEijO+woeX8Mu3GpH17yI+DAAAAgTB/BgkqhkiG9w0BBwagcjBwAgEAMGsGCSqGSIb3DQEHATAeBglghkgBZQMEAS4wEQQMs48GBtCS8r4m65NNAgEQgD6MqR/hFZtWFZmUHwJeVrEDzJE4WDer2eO5Vt9kxvt/egDR3GS4NWjSGSZHLRku6Qcu5FVZR9A4yCt0yiWh4A=="));
        open("https://qa-patient-enrollment.incytesdata-dev.com?data=AQICAHiS8bUkRZ0s5S33cYjODi7mojFV42+YC31lL/q0DzjmlgEijO+woeX8Mu3GpH17yI+DAAAAgTB/BgkqhkiG9w0BBwagcjBwAgEAMGsGCSqGSIb3DQEHATAeBglghkgBZQMEAS4wEQQMs48GBtCS8r4m65NNAgEQgD6MqR/hFZtWFZmUHwJeVrEDzJE4WDer2eO5Vt9kxvt/egDR3GS4NWjSGSZHLRku6Qcu5FVZR9A4yCt0yiWh4A==");
        $("p").shouldHave(text("Ваш лечащий врач приглашает вас в inCytes"));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
