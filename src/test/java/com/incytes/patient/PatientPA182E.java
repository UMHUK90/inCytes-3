package com.incytes.patient;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.switchTo;

public class PatientPA182E {
    public void InvitationOnEmail(String language){
        clinician.Main main = new clinician.Main(language);
        clinician.Main.Login login = main.new Login();
        login.open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        clinician.Main.Login.DashBoard dashBoard = login.new DashBoard();
        clinician.Main.Login.DashBoard.NewCase newCase = dashBoard.new NewCase();
        while(true) {if(!newCase.eForm().exists()) {dashBoard.eNewCase().click(); sleep(50);} else break;}
        clinician.Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        newCase.setAll("qwertyuiop17091709+" + count + "@yandex.by", "2017/04/23", "", "", "", "", "Common Protocol").writeAll().checkAll();
        newCase.clickCreateCase();
        file.writeText(Integer.toString(count+1), false);
    }
    @Test
    public void c_test() {
        InvitationOnEmail("De");
        Main main = new Main("De");
        Main.newTab();
        switchTo().window(1);
        Main.GetInvitationWithYandex invitation = main.new GetInvitationWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        invitation.clickInvitation();
        switchTo().window(2);
        Main.Registration reg = main.new Registration();
        reg.setAll("", Main.password, Main.password, "", "", "", "", "").wRegistration().cRegistration().clickLogin();
        reg.eTextOfCheckBox1().shouldHave(Condition.text("Allgemeine Geschäftsbedingungen"));
        reg.eTextOfCheckBox2().shouldHave(Condition.text("Allgemeine Geschäftsbedingungen des medizinischen Fachpersonals"));
    }
}