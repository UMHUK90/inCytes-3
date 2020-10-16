package com.incytes.patient;

import org.testng.annotations.Test;

public class PatientPA180G {

    @Test
    public void c_test() {
        Main main = new Main("Ru");
        Main.Registration reg = main.new Registration();
        reg.open("https://alpha-patient-enrollment.incytesdata-dev.com?data=AQICAHiS8bUkRZ0s5S33cYjODi7mojFV42+YC31lL/q0DzjmlgFmLTmcm91RufYYbxABrDvlAAAAhzCBhAYJKoZIhvcNAQcGoHcwdQIBADBwBgkqhkiG9w0BBwEwHgYJYIZIAWUDBAEuMBEEDOKHsoYi5inhcY0BNgIBEIBD8Hpd2NbuRwMjZuYy1eNEzuTUdR5UgasywKVL61V2e2t+x6C8P4DlXCb3GWmn0g6Eq9nobBMGBDu5vAC40dceoer+jA==");
        reg.clickLogin();
        Main.muiError(3, 1);
    }
}