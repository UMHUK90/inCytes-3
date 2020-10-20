package com.incytes.patient;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

public class PatientPA181M_Divergence {

    @Test
    public void c_test() {
        PatientPA181J pa181j = new PatientPA181J();
        Main main = new Main();
        Main.MultipleMethods methods = main.new MultipleMethods();
        methods.openWithoutClosing(pa181j::c_test);
        Main.Registration reg = main.new Registration();
        reg.eBlueMarker(3);
        reg.eGetStarted().shouldNotBe(Condition.disabled);
        reg.clickGetStarted();
    }
}