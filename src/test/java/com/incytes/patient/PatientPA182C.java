package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.incytes.clinician.Main;


import static com.codeborne.selenide.Selenide.*;

public class PatientPA182C {
    public void InvitationOnEmail(String language){
        Main main = new Main(language);
        Main.Login login = main.new Login();
        login.open();
        login.setAll("andrew.grabovskiy+6@gmail.com", com.incytes.patient.Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        Main.Login.DashBoard.NewCase newCase = dashBoard.new NewCase();
        while(true) {if(!newCase.eForm().exists()) {dashBoard.eNewCase().click(); sleep(50);} else break;}
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        newCase.setAll("qwertyuiop17091709+" + count + "@yandex.by", "2017/04/23", "", "", "", "", "Common Protocol").writeAll().checkAll();
        newCase.clickCreateCase();
        file.writeText(Integer.toString(count+1), false);
    }
    @Test
    public void c_test() {
        InvitationOnEmail("Fr");
        com.incytes.patient.Main main = new com.incytes.patient.Main("Fr");
        com.incytes.patient.Main.newTab();
        switchTo().window(1);
        com.incytes.patient.Main.GetInvitationWithYandex invitation = main.new GetInvitationWithYandex("qwertyuiop17091709@yandex.ru", "cilaCILA17097938", "+375298746833");
        invitation.clickInvitation();
        switchTo().window(2);
        com.incytes.patient.Main.Registration reg = main.new Registration();
        reg.setAll("", com.incytes.patient.Main.password, com.incytes.patient.Main.password, "", "", "", "", "").wRegistration().cRegistration().clickLogin();
        reg.eTextOfCheckBox1().shouldHave(Condition.text("Termes et Conditions"));
        reg.eTextOfCheckBox2().shouldHave(Condition.text("Termes et Conditions pour les professionnels de la santé"));
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}