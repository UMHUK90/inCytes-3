package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.name;

public class ClinicianL31A {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Login login = main.new Login();
        login.open();
        login.isVisible().setAll("andrew.grabovskiyandrew.grabovskiyandre.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.g@gmail.com", "").wLogin();
        $(name("email")).shouldHave(value("andrew.grabovskiyandrew.grabovskiyandre.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.grabovskiyandrew.g@gmail.com"));
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}