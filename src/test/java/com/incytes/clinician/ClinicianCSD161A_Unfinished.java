package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianCSD161A_Unfinished {
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
        caseDetail.clickArchiveCase();
        $(byText("Are you sure that you want to archive? The patient will no longer receive surveys and the case will be hidden from view. The case will be completed.")).shouldBe(Condition.visible);
        caseDetail.eArchive_Archiving().shouldBe(Condition.visible);
        caseDetail.clickArchive_Archiving();
        caseDetail.eArchive_Archiving().shouldNotBe(Condition.visible);
        Main.eBottomMessage().shouldHave(Condition.text("Case has been archived"));
        cases.eItems().first().shouldNot(Condition.have(Condition.text("qwertyuiop17091709+" + (count-1) + "@yandex.by")));
        //reminders switched off
        Selenide.sleep(500000000);
    }
}
