package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA189H {
    @Test
    public void c_test() {
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.setAll("andrew.grabovskiy+alphapat1@gmail.com", "FSDGSDK123").wLogin().isVisible();
        login.signIn();
        Main.muiError(1,0).shouldHave(Condition.text("8 character minimum. Must contain at least 1 upper case, 1 lower case, 1 number."));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}