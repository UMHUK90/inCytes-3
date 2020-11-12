package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;

public class DeleteAllReports {
    @Test
    public void method(){
        String text = Main.randomText(5);
        Main main = new Main("En");
        Main.Login login = main.new Login().open();
        login.setAll("andrew.grabovskiy+6@gmail.com", Main.password).wLogin().signIn();
        Main.Login.DashBoard dashBoard = login.new DashBoard();
        dashBoard.clickReports();
        Main.Login.DashBoard.Reports reports = dashBoard.new Reports();
        Selenide.sleep(2000);
        while(reports.eListOfSavedReports().size() != 0){
            reports.eMoreOptions(reports.eListOfSavedReports().first()).waitUntil(visible, 5000);
            reports.clickMoreOptions(reports.eListOfSavedReports().first().waitUntil(enabled, 5000));
            Selenide.sleep(500);
            if(reports.eRemoveReport().is(visible)) {
                reports.clickRemoveReport();
                reports.clickRemove_Removing();
            }
        }
    }
}
