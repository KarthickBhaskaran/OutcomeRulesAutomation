package hda.outcome.rules.tests.utilities;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.google.common.io.Files;
import cucumber.api.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class TestUtilities {

    public static Connection connection;
    public static Recordset recordset;

    public void TakeScreenshot(WebDriver webDriver, String name) {
        try {
            // This takes a screenshot from the driver at save it to the specified location
            File sourcePath = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            File destinationPath = new File(".//test-output//screenshots//" + name + ".png");
            // Copy taken screenshot from source location to destination location
            Files.copy(sourcePath, destinationPath);

            // This attach the specified screenshot to the test
            // Reporter.addScreenCaptureFromPath(destinationPath.toString());
        } catch (IOException e) {
        }
    }

    public void TakeScreenshotAsByte(WebDriver webDriver, Scenario scenario) {
        byte[] srcBytes = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        scenario.embed(srcBytes, "image/png");

    }

    public void WriteData(String tcnum, String sField, String table, String value) {
        //String fieldData = "";
        try {
            connection.executeUpdate("UPDATE " + table + " SET " + sField + "='" + value + "' WHERE ReferenceID='" + tcnum + "'");

        } catch (FilloException e) {
            e.printStackTrace();
        }

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

    public void EstablishConnectionWithResultTable() {
        Fillo fillo = new Fillo();
        String pathToTestDataFile = "";
        try {
            Path root = Paths.get(".").normalize().toAbsolutePath();
            pathToTestDataFile = root.toString() + "/test-output/TestResult.xlsx";

            connection = fillo.getConnection(pathToTestDataFile);
        } catch (FilloException e) {
            e.printStackTrace();
        }
    }

    public void EstablishConnectionWithDataTable() {

        Fillo fillo = new Fillo();
        String Env = ReadConfigProperty("Env");
        String pathToTestDataFile = "";
        try {
            Path root = Paths.get(".").normalize().toAbsolutePath();
            if (Env.equals("QA")) {
                pathToTestDataFile = root.toString() + "/src/test/resources/QA_TestData.xlsx";
            } else if (Env.equals("UAT")) {
                pathToTestDataFile = root.toString() + "/src/test/resources/UAT_TestData.xlsx";
            } else if (Env.equals("PreProd")) {
                pathToTestDataFile = root.toString() + "/src/test/resources/PreProd_TestData.xlsx";
            }
            // String pathToTestDataFile = root.toString() +
            // "\\src\\test\\resources\\TestData.xlsx";
            connection = fillo.getConnection(pathToTestDataFile);
        } catch (FilloException e) {
            e.printStackTrace();
        }
    }

    public String ReadData(String refID, String sField, String table) {
        String fieldData = "";
        try {
            recordset = connection.executeQuery("Select * from " + table + " where ReferenceID='" + refID + "'");
            if (recordset.next()) {
                fieldData = recordset.getField(sField);
            }

        } catch (FilloException e) {
            e.printStackTrace();
        }
        recordset.close();
        return fieldData;
    }

    /*
     * public String ReadData(String refID, String sField){
     * String fieldData = "";
     * try {
     * recordset =
     * connection.executeQuery("Select * from PassengerDetails where ReferenceID='"
     * + refID + "'");
     * if(recordset.next()){
     * fieldData = recordset.getField(sField);
     * }
     *
     * } catch (FilloException e) {
     * e.printStackTrace();
     * }
     * recordset.close();
     * return fieldData;
     * }
     */
}
