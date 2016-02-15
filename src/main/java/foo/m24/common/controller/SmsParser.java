package foo.m24.common.controller;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsParser {
    boolean isValid = false;

    public SmsComponent parse(String sms) {
        String pattern = "\\b\\w?\\d{7}\\w?\\b";

        String keyword = sms.substring(0,4);
        String name = "";
        String nric = "";
        String amount = "";

        if( !keyword.equalsIgnoreCase("test") ) {
            return null;
        }

        Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = r.matcher(sms);
        if (m.find( )) {
            nric = sms.substring(m.start(), m.end()).trim();
            name = sms.substring(5, m.start()).trim();
            amount = sms.substring(m.end(), sms.length()).trim();

            BigDecimal bdAmount = null;
            try {
                bdAmount = new BigDecimal(amount);
            } catch (NumberFormatException e) {
                return null;
            }

            SmsComponent smsComponent = new SmsComponent();
            smsComponent.setKeyword(keyword);
            smsComponent.setName(name);
            smsComponent.setNric(nric);
            smsComponent.setAmount(bdAmount);

            isValid = true;
            return smsComponent;
        } else {
            return null;
        }
    }

    //getter setter

    public boolean isValid() {
        return isValid;
    }
}
