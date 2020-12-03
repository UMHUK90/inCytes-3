package com.incytes.patient;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class PatientPA181J {

    @Test
    public void c_test() {
        Main main = new Main();
        Main.Registration reg = main.new Registration();
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        //Common
        reg.open("https://alpha-patient-enrollment.incytesdata-dev.com?data=AQICAHiS8bUkRZ0s5S33cYjODi7mojFV42+YC31lL/q0DzjmlgHAGFnRyIq3hSRjm9d8cCuvAAAAhzCBhAYJKoZIhvcNAQcGoHcwdQIBADBwBgkqhkiG9w0BBwEwHgYJYIZIAWUDBAEuMBEEDIizxcYYVdWVs7y9qwIBEIBDGRzGV0+uYu9MCdrML938SH/xwX+FankRLc/8YOoy1VLkCEiiyDXW3xJV2+anvleNT8LTtqfZ3X3Yf4oIG4O1CUV/MA==");
        reg.setAll("qwertyuiop17091709+" + count + "@yandex.by", Main.password, Main.password, "Petya", "Tyrefh", "2010/12/20", "Belarus", "234532324562521").wRegistration().clickLogin();
        file.writeText(String.valueOf(count+1), false);
        reg.wwRegistration().cwRegistration();
        Main.muiError(0);
        reg.eGetStarted().shouldBe(Condition.disabled);
        reg.clickCheckBox();
        reg.eBlueMarker().shouldBe();
    }
    @AfterMethod
    public static void close(){
        Selenide.close();
    }
}