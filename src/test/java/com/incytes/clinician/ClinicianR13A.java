package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class ClinicianR13A {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.haveAnAccount();
        reg.setAll("","","ScoringGroupTestScoringGroupTestScoringGroupTestScoringGroupTestScoringGroupTestScoringGroupTestScoringGroupTestScoringGroupTestScoringGroupTestScoringGroupTestScoringGroupTestScoringGroupTestScoringGroupTestScoringGroupTestScoringGroupTestScori@gmail.com","","").wRegistration().cRegistration();
        reg.clickNext();
        reg.haveFourRequired();
    }
}
