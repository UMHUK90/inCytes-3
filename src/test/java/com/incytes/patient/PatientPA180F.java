package com.incytes.patient;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.value;

public class PatientPA180F {

    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open("https://qa-patient-enrollment.incytesdata-dev.com?data=AQICAHiS8bUkRZ0s5S33cYjODi7mojFV42+YC31lL/q0DzjmlgFmLTmcm91RufYYbxABrDvlAAAAhzCBhAYJKoZIhvcNAQcGoHcwdQIBADBwBgkqhkiG9w0BBwEwHgYJYIZIAWUDBAEuMBEEDOKHsoYi5inhcY0BNgIBEIBD8Hpd2NbuRwMjZuYy1eNEzuTUdR5UgasywKVL61V2e2t+x6C8P4DlXCb3GWmn0g6Eq9nobBMGBDu5vAC40dceoer+jA==");
        reg.ePassword().setValue("4Gjnghtop4Gjnghtop4Gjnghtop4Gjnghtop4Gjnghtop4Gjnghtop4Gjnghtop4Gjnghtop4Gjnghtop4Gjnghtop4Gjnghtop");
        reg.clickLogin();
        reg.ePassword().shouldHave(value("4Gjnghtop4Gjnghtop4Gjnghtop4Gjnghtop4Gjnghtop4Gjnghtop4Gjnghtop4Gjnghtop4Gjnghtop4Gjnghtop4Gjnghtop"));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}