package com.incytes.patient;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class PatientPA181A {

    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        reg.open("https://alpha-patient-enrollment.incytesdata-dev.com?data=AQICAHiS8bUkRZ0s5S33cYjODi7mojFV42+YC31lL/q0DzjmlgFmLTmcm91RufYYbxABrDvlAAAAhzCBhAYJKoZIhvcNAQcGoHcwdQIBADBwBgkqhkiG9w0BBwEwHgYJYIZIAWUDBAEuMBEEDOKHsoYi5inhcY0BNgIBEIBD8Hpd2NbuRwMjZuYy1eNEzuTUdR5UgasywKVL61V2e2t+x6C8P4DlXCb3GWmn0g6Eq9nobBMGBDu5vAC40dceoer+jA==");
        reg.setAll("qwertyuiop17091709+" + count + "@yandex.by", Main.password, Main.password, "", "", "", "", "").wRegistration();
        reg.clickLogin();
        file.writeText(String.valueOf(count+1), false);
        $(".MuiTypography-h3").shouldHave(text("Добро пожаловать! Ваш аккаунт почти готов."));
        reg.cwRegistration();
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}