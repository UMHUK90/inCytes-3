package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.incytes.clinician.irrelevant.ClinicianCS094W_Fail;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianCS095A_Unfinished {
    @Test
    public void method(){
        ClinicianCS094W_Fail cSO94W = new ClinicianCS094W_Fail();
        Main main = new Main();
        Main.MultipleMethods methods = main.new MultipleMethods();
        methods.openWithoutException(cSO94W::method);
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickCases();
        Main.Login.DashBoard.Cases cases = dashBoard.new Cases();
        cases.clickMoreOptions(cases.eItems().first());
        cases.eClose().shouldBe(Condition.visible);
        cases.eEditCase().shouldBe(Condition.visible);
        cases.clickCloseCase();
        $(byText("Are you sure that you want to close. The patient will no longer receive surveys. The case will be completed"));
        cases.clickCloseCase_Closing();
        cases.eCloseCase_CLosing().shouldNotBe(Condition.visible);
        Main.eBottomMessage().shouldHave(Condition.text("Case has been closed"));
        cases.eItems().first().shouldHave(Condition.text("Completed"));
        //reminders switched off
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}
