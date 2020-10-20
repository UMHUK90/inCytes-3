package com.incytes.patient;

import com.codeborne.selenide.Condition;
import org.testng.annotations.Test;

public class PatientPA181G {

    @Test
    public void c_test() {
        Main main = new Main();
        Main.Registration reg = main.new Registration();
        Main.FileTXT file = main.new FileTXT("D:\\Path\\count.txt");
        int count = Integer.parseInt(file.getText());
        //Common
        reg.open("https://alpha-patient-enrollment.incytesdata-dev.com?data=AQICAHiS8bUkRZ0s5S33cYjODi7mojFV42+YC31lL/q0DzjmlgHAGFnRyIq3hSRjm9d8cCuvAAAAhzCBhAYJKoZIhvcNAQcGoHcwdQIBADBwBgkqhkiG9w0BBwEwHgYJYIZIAWUDBAEuMBEEDIizxcYYVdWVs7y9qwIBEIBDGRzGV0+uYu9MCdrML938SH/xwX+FankRLc/8YOoy1VLkCEiiyDXW3xJV2+anvleNT8LTtqfZ3X3Yf4oIG4O1CUV/MA==");
        reg.setAll("qwertyuiop17091709+" + count + "@yandex.by", Main.password, Main.password, "", "", "1000/10/10", "", "").wRegistration().clickLogin();
        reg.wwRegistration();
        file.writeText(String.valueOf(count+1), false);
        Main.muiError(1, 0).shouldHave(Condition.text("Invalid date: Birth year must be greater than 1900"));
        reg.eBirthDate().setValue("1900/30/10");
        Main.muiError(1, 0).shouldHave(Condition.text("Invalid Date"));
        reg.eBirthDate().setValue("1900/10/99");
        Main.muiError(1, 0).shouldHave(Condition.text("Invalid Date"));
        reg.eBirthDate().setValue("2030/10/10");
        Main.muiError(1, 0).shouldHave(Condition.text("Invalid date: Must be born on or before"));
    }
}