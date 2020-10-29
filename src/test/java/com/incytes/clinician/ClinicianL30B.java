package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$$;

public class ClinicianL30B {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Login login = main.new Login();
        login.open();
        login.signIn();
        $$(".MuiFormHelperText-contained").shouldHave(size(2));
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
