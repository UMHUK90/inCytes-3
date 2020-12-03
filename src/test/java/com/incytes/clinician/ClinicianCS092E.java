package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianCS092E {
    @Test
    public void method(){
        Main main = new Main();
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickCases();
        Main.Login.DashBoard.Cases cases = dashBoard.new Cases();
        cases.eItems().first().waitUntil(Condition.visible, 5000);
        int count = cases.eItems().size();
        cases.eSearch().setValue("Grabovskiy");
        while(true) if(cases.eItems().size() < count) break; else Selenide.sleep(50);
        $(byText("AndrewPatient Grabovskiy")).shouldBe(Condition.visible);
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
