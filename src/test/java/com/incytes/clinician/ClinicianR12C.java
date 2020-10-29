package com.incytes.clinician;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

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
        close();
    }
}