package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianR11D {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.haveAnAccount();
        reg.setAll("Scoring Group Test #6 Description - Single Questions Only. Scoring Group Test #6 Description - Single Questions Only. Scoring Group Test #6 Description - Single Questions Only. Scoring Group Test #6 Description - Single Questions Only. Scoring Group TestA","","","","").wRegistration().cRegistration();
        reg.clickNext();
        reg.haveFourRequired();
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}