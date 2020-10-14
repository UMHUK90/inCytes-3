package com.incytes.patient;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PatientPA143C {
    @Test
    public void c_test() {
        Main main = new Main();
        Main.Login login = main.new Login();
        login.open();
        login.setAll("andrew.grabovskiy+patient1@gmail.com", "261090inCytes").wLogin().signIn();
        Main.Login.Portal portal = login.new Portal();
        portal.clickChangePassword();
        portal.setPassword("261090inCytes", "inCyte1", "inCyte1").wPassword().clickSavePassword();
        $(byText("8 character minimum. Must contain at least 1 upper case, 1 lower case, 1 number."));
        sleep(2000);
    }
}
