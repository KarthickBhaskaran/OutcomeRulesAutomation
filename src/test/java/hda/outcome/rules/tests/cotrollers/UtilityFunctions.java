package hda.outcome.rules.tests.cotrollers;

import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class UtilityFunctions {

    public String AddDatesToToday(int NumberOfDays) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, NumberOfDays);
        String newDate = sdf.format(calendar.getTime());
        return newDate;
    }

    public String splitAndGet(String stringToSplit, String delimiter, int index) {
        return stringToSplit.split(delimiter)[index];
    }

    public String getMonth(int month) {
        return new DateFormatSymbols().getMonths()[month - 1];
    }

    public void TerminateChromeDriver() {
        try {
            // Process process = Runtime.getRuntime().exec("taskkill /F /IM
            // chromedriver.exe");
            Process process = Runtime.getRuntime().exec("sudo pkill chromedriver");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void HardWait(long TimeInMilliseconds) {
        try {
            Thread.sleep(TimeInMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
