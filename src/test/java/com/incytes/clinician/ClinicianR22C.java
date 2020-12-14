package com.incytes.clinician;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.switchTo;

public class ClinicianR22C {
    @Test
    public void method(){
        Main main = new Main("Fr");
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        Main.Login login = main.new Login();
        login.open();
        login.setAll("andrew.grabovskiy@gmail.com", "261090inCytes").wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.isThisLanguage("Fr");
        dashBoard.clickProfile();
        Main.Login.DashBoard.Profile profile = dashBoard.new Profile();
        profile.clickInvite();
        profile.writeEmail("qwertyuiop17091709+" + count + "@yandex.by");
        profile.checkingInvitationForm().clickSendInvitation().isInvitationSent();
        Main.newTab();
        switchTo().window(1);
        Main.GetInvitationWithYandex yandex = main.new GetInvitationWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        yandex.clickInvitation();
        switchTo().window(2);
        Main.Registration reg = main.new Registration();
        //Next step
        reg.clickTerms();
        switchTo().window(3);
        Main.Registration.TermsAndConditions terms = reg.new TermsAndConditions();
        terms.eHeading().shouldHave(Condition.exactText("Termes et conditions"));
        terms.firstText().shouldHave(text("™"));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}