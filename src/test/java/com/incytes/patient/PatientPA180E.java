package com.incytes.patient;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class PatientPA180E {

    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open("https://alpha-patient-enrollment.incytesdata-dev.com?data=AQICAHiS8bUkRZ0s5S33cYjODi7mojFV42+YC31lL/q0DzjmlgFmLTmcm91RufYYbxABrDvlAAAAhzCBhAYJKoZIhvcNAQcGoHcwdQIBADBwBgkqhkiG9w0BBwEwHgYJYIZIAWUDBAEuMBEEDOKHsoYi5inhcY0BNgIBEIBD8Hpd2NbuRwMjZuYy1eNEzuTUdR5UgasywKVL61V2e2t+x6C8P4DlXCb3GWmn0g6Eq9nobBMGBDu5vAC40dceoer+jA==");
        reg.ePassword().setValue("Ratgdv7j");
        reg.eConfirmPassword().setValue("Ratgnv7j");
        reg.clickLogin();
        Main.muiError(2, 1).shouldHave(text("Passwords must match"));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}