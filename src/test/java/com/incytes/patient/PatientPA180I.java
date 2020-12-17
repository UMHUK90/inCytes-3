package com.incytes.patient;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;

public class PatientPA180I {

    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open("https://alpha-patient-enrollment.incytesdata-dev.com?data=AQICAHiS8bUkRZ0s5S33cYjODi7mojFV42+YC31lL/q0DzjmlgFmLTmcm91RufYYbxABrDvlAAAAhzCBhAYJKoZIhvcNAQcGoHcwdQIBADBwBgkqhkiG9w0BBwEwHgYJYIZIAWUDBAEuMBEEDOKHsoYi5inhcY0BNgIBEIBD8Hpd2NbuRwMjZuYy1eNEzuTUdR5UgasywKVL61V2e2t+x6C8P4DlXCb3GWmn0g6Eq9nobBMGBDu5vAC40dceoer+jA==");
        reg.setAll("qwertyuiop17091709+151@yandex.by", "Patient1", "Patient1", "", "", "", "", "").wRegistration().cRegistration();
        reg.clickLogin();
        Main.eBottomMessage().shouldHave(text("Адрес электронной почты уже зарегестрирован в нашей системе."));
    }
    @AfterMethod
    public static void close(){
        Selenide.closeWebDriver();
    }
}