package com.incytes.clinician;

import org.testng.annotations.Test;

public class ClinicianRP41B {
    @Test
    public void method(){
        Main main = new Main("En");
        Main.Login login = main.new Login();
        login.open();
        login.forgotPassword();
        login.forgotPasswordIsCorrect();
        login.writeEmail_forgotPassword("пролдмроуцорорыввфорымаыроцроаыйвр цуав  уаыв уыав  укпва  уыва мфпнгыпм гиgdkjhasdhbhsdgfsasdf  fdsa@gmail.com");
        login.clickSubmit_forgotPassword();
        login.haveInvalid_forgotPassword();
    }
}
