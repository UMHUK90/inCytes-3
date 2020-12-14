package com.incytes.clinician;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.incytes.patient.PatientPA182G;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianD170A_Unfinished {
    @Test
    public void method(){
        PatientPA182G pa182g = new PatientPA182G();
        Main main = new Main();
        Main.MultipleMethods methods = main.new MultipleMethods();
        methods.openWithoutException(pa182g::c_test);
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickCases();
        Main.Login.DashBoard.Cases cases = dashBoard.new Cases();
        cases.clickAddCase();
        Main.Login.DashBoard.Cases.NewCase newCase = cases.new NewCase();
        newCase.clickUseExistingPatient();
        newCase.setAll("qwertyuiop17091709+"+(count-1)+"@yandex.by", "", "", "", "", "", "Common").writeAll();
        newCase.clickCreateCase();
        newCase.eCountry().shouldNotBe(Condition.visible);
        dashBoard.clickPatients();
        Main.Login.DashBoard.Patients patients = dashBoard.new Patients();
        patients.eListOfPatients().first().shouldHave(Condition.text("qwertyuiop17091709+"+(count-1)+"@yandex.by"));
        dashBoard.clickCases();
        cases.eItems().first().shouldHave(Condition.text("qwertyuiop17091709+"+(count-1)+"@yandex.by"));
        Selenide.close();
        com.incytes.patient.Main main1 = new com.incytes.patient.Main("En", "qa");
        com.incytes.patient.Main.Login login1 = main1.new Login().open();
        login1.setAll("qwertyuiop17091709+"+(count-1)+"@yandex.by", Main.password).wLogin().signIn();
        com.incytes.patient.Main.Login.Home home = login1.new Home();
        com.incytes.patient.Main.Login.Home.Survey survey = home.new Survey();
        survey.eRadioButtons().first().click();
        survey.eCheckBoxes().last().click();
        survey.clickSubmit();
        home.eAboutGraph_MyResults().exists();
        home.eGrids_MyResults().shouldHave(CollectionCondition.size(1));
        Selenide.close();
        login.open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        //4-2 Пункт
        //Selenide.sleep(50000000);
        //Selenide.sleep(5000000);
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}
