package com.incytes.patient;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

public class PatientPA143M_Fail {
    @Test
    public void c_test(){
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+alphapat1@gmail.com", Main.password).wLogin().signIn();
        Main.Login.Home home = login.new Home();
        home.openInNewTab();
        home.clickInformation();
        home.eDate_Information().waitUntil(visible, 5000);
        char[] date = home.eDate_Information().getText().toCharArray();
        if(date.length != 10 || date[2] != '/' || date[5] != '/') {System.out.println(date[2] + "-" + date[5]); throw new ElementNotVisibleException("Error in Date");}
        home.eClinicianName_Information().shouldHave(text("Andrew Grabovskiy"));
        home.ePhone_Information().shouldHave(text("")); // Fill in
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}