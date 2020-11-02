package com.incytes.clinician;

import Past.PatientPA182G;
import com.codeborne.selenide.*;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$$;

public class ClinicianD170С {
    @Test
    public void method(){
        Configuration.timeout = 15000;
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
        Selenide.closeWebDriver();
        patient.Main main1 = new patient.Main("En", "qa");
        patient.Main.Login login1 = main1.new Login().open();
        login1.setAll("qwertyuiop17091709+"+(count-1)+"@yandex.by", Main.password).wLogin().signIn();
        patient.Main.Login.Home home = login1.new Home();
        patient.Main.Login.Home.Survey survey = home.new Survey();
        survey.eRadioButtons().first().click();
        for (SelenideElement element:survey.eCheckBoxes()) element.click();
        survey.clickSubmit();
        home.clickCompleteNow_Survey();
        survey.eRadioButtons().first().click();
        for (SelenideElement element:survey.eCheckBoxes()) element.click();
        survey.clickSubmit();
        home.eGrids_MyResults().first().hover();
        home.eAllResults_MyResults().first().shouldHave(Condition.text("Your results"));
        home.eAboutGraph_MyResults().exists();
        home.eGrids_MyResults().shouldHave(CollectionCondition.size(2));
        Selenide.closeWebDriver();
        login.open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        dashBoard.eListOfAlerts().first().shouldHave(Condition.text("qwertyuiop17091709+"+(count-1)+"@yandex.by")).shouldHave(Condition.text("Outlier Detected"));
        dashBoard.eListOfAlerts().get(1).shouldHave(Condition.text("qwertyuiop17091709+"+(count-1)+"@yandex.by")).shouldHave(Condition.text("Outlier Detected"));
        dashBoard.clickCases();
        cases.eItems().get(1).find(byText("qwertyuiop17091709+"+(count-1)+"@yandex.by")).parent().click();
        $$(byText("Outlier Detected")).shouldHave(CollectionCondition.size(4));
    }
}
