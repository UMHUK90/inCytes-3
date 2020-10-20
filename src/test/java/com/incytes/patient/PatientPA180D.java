package com.incytes.patient;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class PatientPA180D {

    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open("https://alpha-patient-enrollment.incytesdata-dev.com?data=AQICAHiS8bUkRZ0s5S33cYjODi7mojFV42+YC31lL/q0DzjmlgFmLTmcm91RufYYbxABrDvlAAAAhzCBhAYJKoZIhvcNAQcGoHcwdQIBADBwBgkqhkiG9w0BBwEwHgYJYIZIAWUDBAEuMBEEDOKHsoYi5inhcY0BNgIBEIBD8Hpd2NbuRwMjZuYy1eNEzuTUdR5UgasywKVL61V2e2t+x6C8P4DlXCb3GWmn0g6Eq9nobBMGBDu5vAC40dceoer+jA==");
        reg.setAll("", "Patien 1", "Patien 1", "", "", "", "", "").wRegistration();
        reg.clickLogin();
        Main.muiError(2, 1).shouldHave(text("Spaces are not allowed"));
    }
}