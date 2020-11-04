package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class ClinicianD172A {
    @Test
    public void method(){
        Configuration.timeout = 5000;
        ClinicianR17A pa182g = new ClinicianR17A();
        Main main = new Main();
        Main.MultipleMethods methods = main.new MultipleMethods();
        methods.openWithoutException(pa182g::OpenTwoTabs);
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        Main.Login login = main.new Login().open();
        login.setAll("qwertyuiop17091709+" + (count-1) + "@yandex.by", Main.password + "!").wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        SelenideElement[] elements = { dashBoard.eNewCase(), dashBoard.eCircles(), dashBoard.ePatients(), dashBoard.eAccount(), dashBoard.eProfile()};
        int cCount = 0;
        for (SelenideElement element: elements) {
            try{
                element.click();
            }
            catch (Throwable e){
                cCount++;
                System.out.println(cCount);
                System.out.println(elements.length);
                throw new ElementNotVisibleException("Element is clickable");
            }
        }
        dashBoard.eSupport().shouldBe(Condition.visible).click();
        dashBoard.eProfile().shouldBe(Condition.visible);
        $("h4").shouldHave(Condition.text("Welcome to inCytes! Please subscribe yourself or your team members.")).shouldBe(Condition.visible);
        dashBoard.eBuySubscription().shouldBe(Condition.visible);
        dashBoard.eListOfAlerts().first().shouldNotBe(Condition.exist);
        dashBoard.eListOfTasks().first().shouldNotBe(Condition.exist);
        dashBoard.eClosedCasesCount().shouldHave(Condition.exactText("0"));
        dashBoard.eOpenCasesCount().shouldHave(Condition.exactText("0"));
        dashBoard.eTotalPatientsCount().shouldHave(Condition.exactText("0"));
        dashBoard.eTotalCasesCount().shouldHave(Condition.exactText("0"));
        dashBoard.eGraph().shouldBe(Condition.visible);
        dashBoard.eLogout().shouldBe(Condition.visible).click();
    }
}
