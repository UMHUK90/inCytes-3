package com.incytes.clinician;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ClinicianR12C {
    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open();
        reg.haveAnAccount();
        reg.setAll("","æÆŒœʣʤʥʦʧʨꜷæœᵫꭡɮʩʪʫꜳꜵꜷꜹꜻꜽꝏᵺỻꜩᴂᴔꭣ₧ƕꭢﬆᵫỻﬄꭁꜼꜶꬱ","","","").wRegistration().cRegistration();
        reg.clickNext();
        reg.haveFourRequired();
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}