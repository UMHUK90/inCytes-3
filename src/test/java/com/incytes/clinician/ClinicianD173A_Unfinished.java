package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.ElementNotVisibleException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.switchTo;

public class ClinicianD173A_Unfinished {
    @Test
    public void method(){
        Configuration.timeout = 5000;
        Main main = new Main();
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        Main.Login login = main.new Login().open();
        login.setAll("qwertyuiop17091709+543@yandex.by", Main.password + "!").wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickProfile();
        Main.Login.DashBoard.Profile profile = dashBoard.new Profile();
        profile.clickInvite();
        profile.writeEmail("qwertyuiop17091709+" + count +"@yandex.by");
        profile.clickSendInvitation();
        file.writeText(String.valueOf(count+1), false);
        com.incytes.patient.Main.newTab();
        switchTo().window(1);
        Main.GetInvitationWithYandex invitation = main.new GetInvitationWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        invitation.clickInvitation();
        switchTo().window(2);
        Main.Registration reg = main.new Registration();
        reg.setAll("sdgs", "dstgsdbs", "", Main.password, Main.password).wRegistration().clickNext();
        dashBoard.eGraph().shouldBe(Condition.visible);
        Selenide.close();
        login.open().setAll("qwertyuiop17091709+543@yandex.by", Main.password + "!").wLogin().signIn();
        dashBoard.clickProfile();
        profile.eListOfTeamMembers().last().shouldHave(Condition.text("qwertyuiop17091709+" + count +"@yandex.by"));
        profile.clickOptions_TeamMember(profile.eListOfTeamMembers().last());
        profile.clickRemove_TeamMember();
        Main.eBottomMessage().shouldHave(Condition.text("Removed successfully"));
        profile.eListOfTeamMembers().last().shouldNotHave(Condition.text("qwertyuiop17091709+" + count +"@yandex.by"));
        Selenide.close();
        login.open().setAll("qwertyuiop17091709+" + count +"@yandex.by", Main.password).wLogin().signIn();
        SelenideElement[] elements = { dashBoard.eCircles(), dashBoard.ePatients(), dashBoard.eAccount(), dashBoard.eProfile(), dashBoard.eProfile()};
        int cCount = 0;
        for (SelenideElement element: elements) {
            try{
                element.click();
            }
            catch (Throwable e){
                cCount++;
            }
        }
        if(cCount > 0) throw new ElementNotVisibleException("Element is clickable");
        dashBoard.eLogout().shouldBe(Condition.visible).click();
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}