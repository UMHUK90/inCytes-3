package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianCSD162A_Fail_Unfinished {
    @Test
    public void method(){
        ClinicianCS094N cSO94W = new ClinicianCS094N();
        Main main = new Main();
        Main.MultipleMethods methods = main.new MultipleMethods();
        methods.openWithoutException(cSO94W::method);
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickCases();
        Main.Login.DashBoard.Cases cases = dashBoard.new Cases();
        cases.eItems().first().shouldHave(Condition.text("qwertyuiop17091709+" + (count-1) + "@yandex.by"));
        cases.toCaseDetails(cases.eItems().first());
        Main.Login.DashBoard.Cases.CaseDetail caseDetail = cases.new CaseDetail();
        caseDetail.eTitle().shouldHave(Condition.text("Case Detail")).shouldBe(Condition.visible);
        caseDetail.clickCLoseCase();
        $(byText("Are you sure that you want to close? The patient will no longer receive surveys. The case will be completed.")).shouldBe(Condition.visible);
        caseDetail.eCloseCase_CLosing().shouldBe(Condition.visible);
        caseDetail.clickCloseCase_Closing();
        caseDetail.eCloseCase_CLosing().shouldNotBe(Condition.visible);
        Main.eBottomMessage().shouldHave(Condition.text("Case has been closed"));
        //all surveys marked as complete - Nothing is marked
        //reminders switched off
        dashBoard.clickCases();
        cases.eItems().first().shouldHave(Condition.text("Completed"));
        Selenide.sleep(500000000);
    }
}
