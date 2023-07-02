package hda.outcome.rules.tests.cotrollers;

import hda.outcome.rules.tests.General.BrowserTypes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public abstract class WebdriverHelper extends UtilityFunctions {

    protected String FetchDriverPath(BrowserTypes browserType) {
        Path root = Paths.get(".").normalize().toAbsolutePath();
        String rootPathInString = root.toString();
        //String driverPath = rootPathInString + "/" + ReadConfigProperty("ChromeDriverPath");
        String driverPath = ReadConfigProperty("ChromeDriverPath");
        if (browserType == BrowserTypes.FIREFOX) {
            return rootPathInString + "/" + ReadConfigProperty("FirefoxDriverPath");
        }
        return driverPath;
    }

    public String GetUserName() {
        String userName = ReadConfigProperty("userName");
        return userName;
    }

    public String GetPassword() {
        String userName = ReadConfigProperty("Password");
        return userName;
    }

    public String GetPhonenum() {
        String phone = ReadConfigProperty("Phone");
        return phone;
    }

    public String GetDOB() {
        String DOB = ReadConfigProperty("DOB");
        return DOB;
    }

    public String GetPostcode() {
        String Postcode = ReadConfigProperty("Postcode");
        return Postcode;
    }

    public String DMCGetPassword() {
        String userName = ReadConfigProperty("DMCPassword");
        return userName;
    }

    public String TravolutionaryGetUserName() {
        String userName = ReadConfigProperty("TravolutionaryUserName");
        return userName;
    }

    public String TravolutionaryGetPassword() {
        String userName = ReadConfigProperty("TravolutionaryPassword");
        return userName;
    }

    public String TripsGetUserName() {
        String userName = ReadConfigProperty("TripsuserName");
        return userName;
    }

    public String TripsGetPassword() {
        String userName = ReadConfigProperty("TripsPassword");
        return userName;
    }

    public String GetSecretquespwd() {
        String pwd = ReadConfigProperty("SecrectQuesPwd");
        return pwd;
    }

    public String GetURL(String Env) {
        String URL = "";
        if (Env.equals("QA")) {
            URL = "https://uathd.crm6.dynamics.com.mcas.ms/main.aspx?";

        } else if (Env.equals("UAT")) {
            URL = "https://uathd.crm6.dynamics.com.mcas.ms/main.aspx?appid=0919370e-f62c-ed11-9db1-002248181d77&pagetype=entityrecord&etn=incident";

        } else if (Env.equals("Training")) {
            //URL = "https://traininghd.crm6.dynamics.com.mcas.ms/main.aspx?";
            URL = "https://traininghd.crm6.dynamics.com/main.aspx?appid=785424ae-9f24-ed11-9db1-00224818162a&pagetype=entityrecord&etn=incident";

        }

        return URL;
    }

    public String GetURL() {
        String Env = ReadConfigProperty("Env");
        String URL = "";
        //String URL = ReadConfigProperty("appURL");
        if (Env.equals("QA")) {
            URL = "https://uathd.crm6.dynamics.com.mcas.ms/main.aspx?";

        } else if (Env.equals("UAT")) {
            URL = "https://uathd.crm6.dynamics.com.mcas.ms/main.aspx?appid=0919370e-f62c-ed11-9db1-002248181d77&pagetype=entityrecord&etn=incident";

        } else if (Env.equals("Training")) {

            //URL = "https://traininghd.crm6.dynamics.com.mcas.ms/main.aspx?";
            URL = "https://traininghd.crm6.dynamics.com/main.aspx?appid=785424ae-9f24-ed11-9db1-00224818162a&pagetype=entityrecord&etn=incident";
        }

        return URL;
    }

    public String GetSettingsURL() {
        String URL = ReadConfigProperty("settingsURL");
        return URL;
    }


    protected long FetchGlobalExplicitWaitTimeout() {
        return Long.parseLong(ReadConfigProperty("ExplicitWaitTimeoutInSeconds"));
    }

    protected long FetchGlobalImplicitWaitTimeout() {
        return Long.parseLong(ReadConfigProperty("ImplicitWaitTimeoutInSeconds"));
    }

    public String ReadConfigProperty(String propertyName) {
        Path root = Paths.get(".").normalize().toAbsolutePath();
        String pathToConfigFile = root.toString() + "/src/test/resources/config.properties";
        try (InputStream input = new FileInputStream(pathToConfigFile)) {
            Properties prop = new Properties();
            // load a properties file
            prop.load(input);
            // get the property value and print it out
            return prop.getProperty(propertyName);
        } catch (IOException ex) {
            ex.printStackTrace();
            return "ERROR";
        }
    }

    protected abstract void SetUserAgent();

}
