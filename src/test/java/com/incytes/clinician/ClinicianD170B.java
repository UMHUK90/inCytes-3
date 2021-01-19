package com.incytes.clinician;

import com.codeborne.selenide.*;
import com.incytes.patient.PatientPA182G;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ClinicianD170B {
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
        patients.eListOfPatients().first().shouldHave(text("qwertyuiop17091709+"+(count-1)+"@yandex.by"));
        dashBoard.clickCases();
        cases.eItems().first().shouldHave(text("qwertyuiop17091709+"+(count-1)+"@yandex.by"));
        Selenide.closeWebDriver();
        com.incytes.patient.Main main1 = new com.incytes.patient.Main("Ru", "qa");
        com.incytes.patient.Main.Login login1 = main1.new Login().open();
        login1.setAll("qwertyuiop17091709+"+(count-1)+"@yandex.by", Main.password).wLogin().signIn();
        com.incytes.patient.Main.Registration registration = main1.new Registration();
        registration.setAll("", "", "", "First Name", "Last Name", "2010/01/05", "Belarus", "+73597545");
        $("h3").shouldHave(text("Добро пожаловать! Ваш аккаунт почти готов."));
        registration.wwRegistration().cwRegistration().clickGetStarted();
        com.incytes.patient.Main.Login.Home home = login1.new Home();
        com.incytes.patient.Main.Login.Home.Survey survey = home.new Survey();
        survey.eRadioButtons().first().click();
        for (SelenideElement element:survey.eCheckBoxes()) element.click();
        // Grouped Type Question
        $("input[value='8']", 0).click();
        $("input[value='7']", 1).click();
        $("input[value='6']", 2).click();
        $("input[value='7']", 3).click();
        // Date Question
        $("input[name='answers.date._15']").setValue("2011/01/20");
        // Number Type Question
        $("input[type='text']", 1).setValue("20");
        // Text Type Question
        $("textarea").setValue("Some text");
        survey.clickSubmit();
        home.eGrids_MyResults().first().hover();
        home.eAllResults_MyResults().first().shouldHave(text("Your results"));
        home.eAboutGraph_MyResults().exists();
        home.eGrids_MyResults().shouldHave(CollectionCondition.size(1));
        Selenide.closeWebDriver();
        Main main2 = new Main("Ru");
        Main.Login login2 = main2.new Login().open();
        login2.open();
        login2.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        //dashBoard.eListOfAlerts().first().shouldHave(text("qwertyuiop17091709+"+(count-1)+"@yandex.by"));
        dashBoard.clickCases();
        cases.eItems().get(1).find(byText(registration.firstName+" "+registration.lastName)).parent().click();
        //$(byText("Outlier Detected")).shouldBe(Condition.exist);
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}