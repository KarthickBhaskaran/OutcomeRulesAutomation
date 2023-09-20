package hda.outcome.rules.tests.PageObjects;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.io.Files;
import hda.outcome.rules.tests.Hooks.CucumberHooks;
import hda.outcome.rules.tests.cotrollers.BasePage;
import hda.outcome.rules.tests.testContext.TestExecutionContext;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class CartPage extends BasePage {

    CucumberHooks hooksPage;

    // Login Web elements
    By logintextbox = By.id("i0116");
    By loginbtn = By.id("idSIButton9");
    By pwdtextbox = By.id("i0118");
    By loginButton = By.id("idSIButton9");
    By continueToMSDynamics = By.id("hiddenformSubmitBtn");

    // CRM Page Web elements
    By TwilloPanel = By.tagName("h2");


    // Case Creation Web elements
    By Encounterbtn = By.xpath("//img[@title='Encounter']");
    By Newcasebtn = By.xpath("//img[@title='New Case']");
    By Patientlookup = By.xpath("//input[@data-id='hd_patient.fieldControl-LookupResultsDropdown_hd_patient_textInputBox_with_filter_new']");
    By Newpatientbtn = By.xpath("//html/body/section/div/div/div/div/div/div/div/div[2]/div[3]/div[2]/div/div/div/div/div/div/div[2]/div[2]/div[3]/div[2]/div/div/div/div[1]/div/div");

    // Test harness Web elements
    By testHarness = By.xpath("//select[@data-id='hd_testharness.fieldControl-checkbox-select']");

    //flyoutcomponent web elements

    By flyOutbutton = By.xpath("//button[@data-id='header_overflowButton']");
    By statedrop = By.xpath("//select[@data-id='header_hd_state.fieldControl-option-set-select']");
    By callSource = By.xpath("//select[@data-id='header_hd_callsource.fieldControl-option-set-select']");
    By saveEncounter = By.xpath("//img[@title='Save']");
    By emergencyFlyout = By.xpath("//div[contains(@id, 'MscrmControls.Containers.ProcessBreadCrumb-stageIndicatorInnerHolder')]");
    By reasnForCall = By.xpath("//textarea[@data-id='header_process_hd_reasonforcall.fieldControl-text-box-text']");
    By nextPage = By.xpath("//label[@id='MscrmControls.Containers.ProcessStageControl-Next Stage']");
    By curAddSameAsResiden = By.xpath("//select[@data-id='header_process_hd_currentlocationsameasresidential.fieldControl-option-set-select']");
    //By demographicscheck = By.xpath("//*[contains(@id,'isdemographicschecked.fieldControl-checkbox-select')]");
    By demographicscheck = By.xpath("//select[@data-id='header_process_hd_isdemographicschecked.fieldControl-checkbox-select']");
    // Outcome modal Web elements
    By manualtiragebtn = By.id("btnTest");
    By triagelevel = By.id("cmbTHTriageLevel");
    By hintcondition = By.id("cmbTHHintCondition");
    By Specialist = By.id("cmbTHSpecialist");
    By consultationlevel = By.id("cmbTHTeleconsultEnabled");
    By getinitialdisposion = By.id("btnTHInitialDispositionResult");
    By closemodal = By.id("btnCloseModal");

    // Disposition and Outcome Web elements

    By confirmDispositionbtn = By.id("btnConfirmDisposition");
    By confirmCareOptionbtn = By.id("btnConfirmOutcome");
    By initialDisposition = By.id("pnlConfirmDispositionContainer");
    By finalDisposition = By.id("pnlFinalDispostion");
    By initialOutcome = By.id("pnlConfirmOutcomeContainer");
    By finalOutcome = By.id("pnlFinalOutcomeContainer");
    By callerscript = By.id("outcomedetailscontainer");

    // Capture Case ID and close case
    By caseidheader = By.xpath("//div[@data-id='headerContainer']");
    By saveclosebtn = By.xpath("//button[@aria-label='Save & Close']");

    By currentlocation = By.id("caddress");

    Boolean checkelementenabled = null;
    By Advancedlookup = By.xpath("//*[contains(@id,'advlookup')]");
    //By PatientSearchBox = By.xpath("//*[contains(@class,'ms-SearchBox-field')]");
    By PatientSearchBox = By.xpath("//input[starts-with(@id,'SearchBox')]");


    public CartPage(TestExecutionContext testExecutionContext) {
        super(testExecutionContext);
        driverFacade = testExecutionContext.getDriverFacade();
        TEC = testExecutionContext;
        driverFacade.setCurrentScenario(TEC.getScenario());
        driverFacade.setCurrentPage(this);

    }


    public void logintocrm() {

        //#driverFacade.HardWait(1000);
        //driverFacade.waitFor().WebElementToBePresentOptional(skipaddfligt).clickWebElementOptional();
        String URL = driverFacade.GetURL(driverFacade.Environment);
        driverFacade.navigateToUrl(URL);
        // Login
        driverFacade.HardWait(4000);
        //driverFacade.findElement(logintextbox).sendKeys("test-sp1-tl.02@healthdirect.dev");
        driverFacade.findElement(logintextbox).setText(driverFacade.getUserName());
        driverFacade.findElement(loginbtn).clickWebElement();
        driverFacade.HardWait(6000);
        //driverFacade.findElement(pwdtextbox).sendKeys ("Fridayqatest12356");
        driverFacade.findElement(pwdtextbox).setText(driverFacade.getPassword());
        driverFacade.HardWait(6000);
        driverFacade.findElement(loginButton).clickWebElement();
        //driverFacade.HardWait(10000);
        //driverFacade.findElement(continueToMSDynamics).clickWebElement();
        driverFacade.HardWait(35000);
        //driverFacade.findElement(TwilloPanel).clickWebElement();
        driverFacade.writeInReport("CRM Login Successful");
        driverFacade.writeInReport("***********************");
        driverFacade.take_screenShot();

    }

    public void performoutcometest() {


        // Establishing connection to OutcomeRules sheet
        Connection connection=null;
        Recordset recordset;
        Recordset recordset2;
        Recordset recordset3;

        Fillo fillo = new Fillo();
        try{
            Path root = Paths.get(".").normalize().toAbsolutePath();
            String pathtoTestDataFile = root.toString() + "\\src\\test\\resources\\HDA_Final_OutcomeRulestable_24Feb.xlsx";
            System.out.println(pathtoTestDataFile);
            connection = (Connection) fillo.getConnection(pathtoTestDataFile);

        } catch (FilloException e){
            e.printStackTrace();
        }

        int testcount =0;
        int dispositiondetails = 0;
        int outcomedetails = 0;
        String[] TCid = new String[350];
        String[] CONTRACT_GROUP_ID = new String[350];
        String[] FINAL_DISPOSITION_ID = new String[350];
        String[] OUTCOME_ID = new String[350];

        String[]  Disposition_Description = new String[10];
        String[]  Outcome_Description = new String[10];
        String[] CDSS_triage_level = new String[10];
        String[] CDSS_Channel = new String[10];
        String[] CDSS_Hint = new String[10];
        String[] CDSS_RecommendedSpecialist  = new String[10];
        // Date and Time
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String date1;
        driverFacade.writeInReport("****************************************");

        try{
            recordset=connection.executeQuery("Select * from OutcomeRulesHDA WHERE Runner_Flag='Yes'");
            testcount=recordset.getCount();
            driverFacade.writeInReport("Total records for tests   : " + testcount);
            driverFacade.writeInReport("****************************************");
            for (int i=1;i<=testcount;i++){
                recordset.moveNext();
                TCid[i] = recordset.getField("TCRef");
                TCid[i] = TCid[i].trim();
                if (!TCid[i].equals("")){
                    CONTRACT_GROUP_ID[i] = recordset.getField("CONTRACT_GROUP_ID");
                    FINAL_DISPOSITION_ID[i] = recordset.getField("FINAL_DISPOSITION_ID");
                    OUTCOME_ID[i] = recordset.getField("OUTCOME_ID");

                    driverFacade.writeInReportBlue("TCRef                        : " + TCid[i]);
                    driverFacade.writeInReport("CONTRACT_GROUP_ID            : " + CONTRACT_GROUP_ID[i]);
                    //driverFacade.writeInReport("Disposition_Description      : " + CONTRACT_GROUP_ID[i]);


                    try {
                        recordset2 = connection.executeQuery("Select * from HDADispositiondesc WHERE Disposition_ID ='" + FINAL_DISPOSITION_ID[i] + "'");
                        dispositiondetails = recordset2.getCount();
                        for (int j=1;j<=dispositiondetails;j++){
                            recordset2.moveNext();
                            Disposition_Description[j] = recordset2.getField("Disposition_Description");
                            CDSS_triage_level[j] = recordset2.getField("CDSS_triage_level");
                            CDSS_Channel[j] = recordset2.getField("CDSS_Channel");
                            CDSS_Hint[j] = recordset2.getField("CDSS_Hint");
                            CDSS_RecommendedSpecialist[j] = recordset2.getField("CDSS_RecommendedSpecialist");
                            driverFacade.writeInReport("Disposition_Description      : " + Disposition_Description[j]);
                            if (CDSS_Channel[j].contains("Not")){
                                CDSS_Channel[j] = "Audio teleconsultation";}
                            else if (CDSS_Channel[j].contains("N/A")){
                                CDSS_Channel[j] = "personal_visit";}

                            if (CDSS_Hint[j].contains("ANY")){
                                CDSS_Hint[j] = "Consultation_2";}
                            else if(CDSS_Hint[j].contains("NO")){
                                CDSS_Hint[j] = "N/A";
                            }

                            if (CDSS_RecommendedSpecialist[j].contains("NOT")){
                                CDSS_RecommendedSpecialist[j] = "General Practitioner";}
                            else if(CDSS_RecommendedSpecialist[j].contains("Poisons")){
                                CDSS_RecommendedSpecialist[j] = "Poisons Control Centre";
                            }
                            else if(CDSS_RecommendedSpecialist[j].contains("Psychiatrist")){
                                CDSS_RecommendedSpecialist[j] = "Psychiatrist";
                            }
                            else if(CDSS_RecommendedSpecialist[j].contains("Gynecologist")){
                                CDSS_RecommendedSpecialist[j] = "Gynecologist";
                            }
                            else if(CDSS_RecommendedSpecialist[j].contains("N/A")){
                                CDSS_RecommendedSpecialist[j] = "General Practitioner";
                            }


                            // Case Creation
                            /*

                            driverFacade.findElement(Encounterbtn).clickWebElement();
                            driverFacade.HardWait(5000);
                            driverFacade.findElement(Newcasebtn).clickWebElement();
                            driverFacade.HardWait(8000);
                            */


                            String URL = "https://traininghd.crm6.dynamics.com/main.aspx?appid=785424ae-9f24-ed11-9db1-00224818162a&pagetype=entityrecord&etn=incident";
;                            driverFacade.navigateToUrl(URL);

                            driverFacade.findElement(Patientlookup).clickWebElement();
                            driverFacade.findElement(Patientlookup).setText("Kar New");
                            driverFacade.HardWait(4000);
                            checkelementenabled = driverFacade.checkelementoptional(Advancedlookup);
                            if (checkelementenabled.equals(true)) {
                                driverFacade.findElement(Advancedlookup).clickWebElement(); }
                            else {
                                System.out.println("Cannot Find Advanced look up button");}

                            driverFacade.HardWait(4000);
                            driverFacade.findElement(PatientSearchBox).setText("Kar New");
                            driverFacade.HardWait(2000);
                            driverFacade.findElement(Newpatientbtn).clickWebElement();
                            driverFacade.HardWait(2000);
                            driverFacade.findElement(By.xpath("//button[@title='Done']")).clickWebElement();

                            // Test Harness enabling
                            driverFacade.scrollDown();
                            //driverFacade.findElement(testHarness).sendKeys("PageDown");
                            //driverFacade.HardWait(2000);

                            driverFacade.findElement(testHarness).setText("Yes");


                            // Flyout Component
                            driverFacade.findElement(flyOutbutton).clickWebElement();
                            driverFacade.HardWait(2000);

                            if (CONTRACT_GROUP_ID[i].contains("HIAS_SA")){
                                driverFacade.findElement(statedrop).setText("SA");}
                            else if (CONTRACT_GROUP_ID[i].contains("HIAS_NSW")){
                                driverFacade.findElement(statedrop).setText("NSW");}
                            else if (CONTRACT_GROUP_ID[i].contains("HIAS_NSWAST")){
                                driverFacade.findElement(statedrop).setText("NSW");}
                            else if (CONTRACT_GROUP_ID[i].contains("HIAS_TAS")){
                                driverFacade.findElement(statedrop).setText("TAS");}
                            else if (CONTRACT_GROUP_ID[i].contains("HIAS_WA")){
                                driverFacade.findElement(statedrop).setText("WA");}
                            else if (CONTRACT_GROUP_ID[i].contains("HIAS_NT")){
                                driverFacade.findElement(statedrop).setText("NT");}

                            driverFacade.HardWait(2000);
                            if (CONTRACT_GROUP_ID[i].contains("HIAS_NSWAST")){
                                driverFacade.findElement(callSource).setText("HIAS_Amb Sec Triage");}
                            else {
                                driverFacade.findElement(callSource).setText("HIAS_Gen Public");
                            }

                            driverFacade.HardWait(3000);
                            driverFacade.findElement(saveEncounter).clickWebElement();
                            driverFacade.HardWait(20000);

                            driverFacade.writeInReport("Patient Info saved in case");
                            //driverFacade.findElement(Patientlookup).scrollToElement();
                            driverFacade.HardWait(1000);
                            driverFacade.take_screenShot();
                            driverFacade.findElement(emergencyFlyout).clickWebElement();
                            driverFacade.HardWait(2000);
                            driverFacade.findElement(reasnForCall).sendKeys("CTRL+A");
                            driverFacade.findElement(reasnForCall).setText("Test");
                            driverFacade.HardWait(4000);
                            driverFacade.findElement(nextPage).clickWebElement();
                            driverFacade.HardWait(4000);
                            //driverFacade.findElement(demographicscheck).clickWebElement();
                            driverFacade.findElement(demographicscheck).setText("Yes");
                            driverFacade.HardWait(3000);
                            driverFacade.findElement(curAddSameAsResiden).setText("No");
                            driverFacade.HardWait(1000);
                            driverFacade.findElement(nextPage).clickWebElement();
                            driverFacade.HardWait(5000);

                            By framepath = By.xpath("//iframe[@id='WebResource_triageWR']");
                            // Outcome modal handling
                            driverFacade.SwitchTo(framepath);
                            driverFacade.writeInReport("****************************************");
                            driverFacade.writeInReport("Triage Level                 : " + CDSS_triage_level[j]);
                            driverFacade.writeInReport("CDSS Channel                 : " + CDSS_Channel[j]);
                            driverFacade.writeInReport("Hint                         : " + CDSS_Hint[j]);
                            driverFacade.writeInReport("Specialist                   : " + CDSS_RecommendedSpecialist[j]);
                            driverFacade.writeInReport("****************************************");
                            driverFacade.findElement(manualtiragebtn).clickWebElement();
                            driverFacade.HardWait(1000);
                            if (!CDSS_triage_level[j].equals("N/A")) {
                                if (CDSS_triage_level[j].equals("consultation")) {
                                    //driverFacade.findElement(triagelevel).sendKeys(CDSS_triage_level[j], Keys.ARROW_DOWN);
                                    driverFacade.findElement(triagelevel).setText(CDSS_triage_level[j]);
                                    driverFacade.findElement(triagelevel).setText(CDSS_triage_level[j]);
                                    //driverFacade.findElement(triagelevel).sendKeys("DownArrow");
                                } else if (CDSS_triage_level[j].equals("consultation_24")) {
                                    driverFacade.findElement(triagelevel).setText(CDSS_triage_level[j]);
                                } else if (CDSS_triage_level[j].equals("emergency_ambulance")) {
                                    //driverFacade.findElement(triagelevel).sendKeys(CDSS_triage_level[j], Keys.ARROW_UP);
                                    //driverFacade.findElement(triagelevel).setText(CDSS_triage_level[j]).sendKeys("UpArrow");
                                    //driverFacade.findElement(triagelevel).sendKeys("UpArrow");

                                } else if (CDSS_triage_level[j].equals("emergency")) {
                                    driverFacade.findElement(triagelevel).setText(CDSS_triage_level[j]);
                                } else if (CDSS_triage_level[j].equals("N/A")) {
                                    CDSS_triage_level[j] = "consultation";
                                    //driverFacade.findElement(triagelevel).sendKeys(CDSS_triage_level[j], Keys.ARROW_DOWN);
                                    driverFacade.findElement(triagelevel).setText(CDSS_triage_level[j]);
                                    driverFacade.findElement(triagelevel).sendKeys("DownArrow");
                                }


                                driverFacade.HardWait(1000);
                            }

                            if (!CDSS_Hint[j].equals("N/A")){
                                driverFacade.findElement(hintcondition).setText(CDSS_Hint[j]);}
                            driverFacade.HardWait(1000);
                            if (!CDSS_RecommendedSpecialist[j].equals("N/A")){
                                driverFacade.findElement(Specialist).setText(CDSS_RecommendedSpecialist[j]);}
                            driverFacade.HardWait(1000);
                            if (!CDSS_Channel[j].equals("N/A")){
                                driverFacade.findElement(consultationlevel).setText(CDSS_Channel[j]);}
                            driverFacade.HardWait(1000);
                            driverFacade.findElement(getinitialdisposion).clickWebElement();
                            driverFacade.HardWait(1000);
                            driverFacade.writeInReport("Outcome Modal with Initial Disposition");
                            driverFacade.HardWait(2000);
                            driverFacade.take_screenShot();
                            driverFacade.findElement(getinitialdisposion).sendKeys("PageDown");
                            driverFacade.HardWait(2000);
                            driverFacade.findElement(closemodal).clickWebElement();
                            driverFacade.findElement(manualtiragebtn).sendKeys("PageUp");




                            // Confirming Disposition and Outcome
                            String ID = driverFacade.findElement(initialDisposition).getWebElementText();
                            driverFacade.writeInReport("CRM Triage screen with Initial Disposition");
                            driverFacade.findElement(initialDisposition).scrollToElement();
                            driverFacade.take_screenShot();
                            String arrID[] = ID.split("Disposition");
                            arrID[1] = arrID[1].trim();
                            //System.out.println(arrID[1]);
                            //System.out.println("**************************");

                            //driverFacade.writeInReport("TCRef                        : " + TCid[i]);
                            driverFacade.writeInReport("Initial Disposition          : " + arrID[1]);

                            driverFacade.HardWait(3000);

                            if (CONTRACT_GROUP_ID[i].contains("HIAS_SA")){
                                driverFacade.findElement(currentlocation).setText("5000");
                                driverFacade.HardWait(2000);
                                driverFacade.findElement(By.xpath("//*[contains(text(),'ADELAIDE, SA 5000')]")).clickWebElement();}
                            else if (CONTRACT_GROUP_ID[i].contains("HIAS_NSW")){
                                driverFacade.findElement(currentlocation).setText("2000");
                                driverFacade.HardWait(2000);
                                driverFacade.findElement(By.xpath("//*[contains(text(),'SYDNEY, NSW 2000')]")).clickWebElement();}
                            else if (CONTRACT_GROUP_ID[i].contains("HIAS_NSWAST")){
                                driverFacade.findElement(currentlocation).setText("2000");
                                driverFacade.HardWait(2000);
                                driverFacade.findElement(By.xpath("//*[contains(text(),'SYDNEY, NSW 2000')]")).clickWebElement();}
                            else if (CONTRACT_GROUP_ID[i].contains("HIAS_TAS")){
                                driverFacade.findElement(currentlocation).setText("7000");
                                driverFacade.HardWait(3000);
                                driverFacade.findElement(By.xpath("//*[contains(text(),'HOBART, TAS 7000')]")).clickWebElement();}
                            else if (CONTRACT_GROUP_ID[i].contains("HIAS_WA")){
                                driverFacade.findElement(currentlocation).setText("6000");
                                driverFacade.HardWait(2000);
                                driverFacade.findElement(By.xpath("//*[contains(text(),'PERTH, WA 6000')]")).clickWebElement();}
                            else if (CONTRACT_GROUP_ID[i].contains("HIAS_NT")){
                                driverFacade.findElement(currentlocation).setText("0800");
                                driverFacade.HardWait(2000);
                                driverFacade.findElement(By.xpath("//*[contains(text(),'DARWIN CITY, NT 0800')]")).clickWebElement();
                            }

                            //driverFacade.findElement(currentlocation).sendKeys("3000");

                            driverFacade.findElement(confirmDispositionbtn).clickWebElement();
                            driverFacade.HardWait(10000);
                            driverFacade.writeInReport("CRM Triage screen with Disposition confirmed");
                            driverFacade.take_screenShot();
                            String FD = driverFacade.findElement(finalDisposition).getWebElementText();
                            String arrFD[] = FD.split("Disposition");
                            //System.out.println(arrFD[1]);
                            //System.out.println(driverFacade.findElement(finalDisposition).getWebElementText());
                            //System.out.println("**************************");
                            driverFacade.HardWait(10000);

                            String IO = driverFacade.findElement(initialOutcome).getWebElementText();
                            driverFacade.findElement(initialOutcome).scrollToElement();
                            driverFacade.writeInReport("CRM Triage screen Initial Ouctome");
                            driverFacade.take_screenShot();
                            driverFacade.writeInReport("****************************************");
                            String arrIO[] = IO.split("Care Option");
                            String arr2IO[] = new String[3];
                            String arr3IO[] = new String[3];
                            String arr4IO[] = new String[3];
                            int outcomelength = arrIO.length;
                            if (outcomelength == 2){
                                arr2IO = arrIO[1].split("Check");
                                arr2IO[0] = arr2IO[0].trim();
                                //System.out.println(arr2IO[0]);
                                //driverFacade.writeInReport("TCRef                        : " + TCid[i]);
                                driverFacade.writeInReport("Initial Outcome 1            : " + arr2IO[0]);
                            }
                            else if (outcomelength == 3){
                                arr2IO = arrIO[1].split("Check");
                                arr2IO[0] = arr2IO[0].trim();
                                arr3IO = arrIO[2].split("Check");
                                arr3IO[0] = arr3IO[0].trim();
                                //System.out.println(arr2IO[0]);
                                //System.out.println(arr3IO[0]);
                                driverFacade.writeInReport("Initial Outcome 1            : " + arr2IO[0]);
                                driverFacade.writeInReport("Initial Outcome 2            : " + arr3IO[0]);
                            }
                            else if (outcomelength == 4){
                                arr2IO = arrIO[1].split("Check");
                                arr2IO[0] = arr2IO[0].trim();
                                arr3IO = arrIO[2].split("Check");
                                arr3IO[0] = arr3IO[0].trim();
                                arr4IO = arrIO[3].split("Check");
                                arr4IO[0] = arr4IO[0].trim();
                                //System.out.println(arr2IO[0]);
                                //System.out.println(arr3IO[0]);
                                //System.out.println(arr4IO[0]);
                                driverFacade.writeInReport("Initial Outcome 1            : " + arr2IO[0]);
                                driverFacade.writeInReport("Initial Outcome 2            : " + arr3IO[0]);
                                driverFacade.writeInReport("Initial Outcome 3            : " + arr4IO[0]);
                            }
                            driverFacade.writeInReport("****************************************");
                            String confirmedIO = "";

                            try {
                                recordset3 = connection.executeQuery("Select * from HDAOutcomedesc WHERE OUTCOME_ID ='" + OUTCOME_ID[i] + "'");
                                outcomedetails = recordset3.getCount();
                                for (int k = 1; k <= outcomedetails; k++) {
                                    recordset3.moveNext();
                                    Outcome_Description[k] = recordset3.getField("OUTCOME_TXT");
                                    //driverFacade.writeInReport("Initial Outcome 1            : " + arr2IO[0]);
                                    driverFacade.writeInReport("Expected Outcome             : " + Outcome_Description[k]);
                                    if (outcomelength == 4){
                                        if (Outcome_Description[k].equals(arr2IO[0])){
                                            confirmedIO = arr2IO[0];
                                            String IOradio = "//input[@type='radio' and @name='multipleOutcome' and @title='"+ arr2IO[0] +"']";
                                            By IOradiobtn = By.xpath(IOradio);
                                            driverFacade.findElement(IOradiobtn).clickWebElement();
                                            driverFacade.HardWait(4000);
                                        }
                                        else if (Outcome_Description[k].equals(arr3IO[0])){
                                            confirmedIO = arr3IO[0];
                                            String IOradio = "//input[@type='radio' and @name='multipleOutcome' and @title='"+ arr3IO[0] +"']";
                                            By IOradiobtn = By.xpath(IOradio);
                                            driverFacade.findElement(IOradiobtn).clickWebElement();
                                            driverFacade.HardWait(4000);
                                        }
                                        else if (Outcome_Description[k].equals(arr4IO[0])){
                                            confirmedIO = arr4IO[0];
                                            String IOradio = "//input[@type='radio' and @name='multipleOutcome' and @title='"+ arr4IO[0] +"']";
                                            By IOradiobtn = By.xpath(IOradio);
                                            driverFacade.findElement(IOradiobtn).clickWebElement();
                                            driverFacade.HardWait(4000);
                                        }
                                    }
                                    else if (outcomelength == 3){
                                        if (Outcome_Description[k].equals(arr2IO[0])){
                                            confirmedIO = arr2IO[0];
                                            String IOradio = "//input[@type='radio' and @name='multipleOutcome' and @title='"+ arr2IO[0] +"']";
                                            By IOradiobtn = By.xpath(IOradio);
                                            driverFacade.findElement(IOradiobtn).clickWebElement();
                                            driverFacade.HardWait(4000);
                                        }
                                        else if (Outcome_Description[k].equals(arr3IO[0])){
                                            confirmedIO = arr3IO[0];
                                            String IOradio = "//input[@type='radio' and @name='multipleOutcome' and @title='"+ arr3IO[0] +"']";
                                            By IOradiobtn = By.xpath(IOradio);
                                            driverFacade.findElement(IOradiobtn).clickWebElement();
                                            driverFacade.HardWait(4000);
                                        }

                                    }
                                    else if (outcomelength ==2){
                                        if (Outcome_Description[k].equals(arr2IO[0])){
                                            confirmedIO = arr2IO[0];

                                        }
                                    }


                                }
                            } catch (FilloException e){
                                e.printStackTrace();
                            }



                            /*
                            String arrIO[] = IO.split("Option");
                            String arr2IO[] = new String[3];
                            if (arrIO.length>1) {
                            arr2IO = arrIO[1].split("Check");
                                arr2IO[0] = arr2IO[0].trim();
                                System.out.println(arr2IO[0]);}
                            else{
                                arrIO[1] = arrIO[1].trim();
                                arr2IO[0] = arrIO[1];
                            } */

                            //System.out.println(driverFacade.findElement(initialOutcome).getWebElementText());
                            //System.out.println("**************************");

                            


                            driverFacade.findElement(confirmCareOptionbtn).clickWebElement();
                            driverFacade.HardWait(4000);
                            String FO = driverFacade.findElement(finalOutcome).getWebElementText();
                            String arrFO[] = FO.split("Recommendation");

                            //driverFacade.writeInReport("Initial Outcome 1            : " + arr2IO[0]);
                            driverFacade.writeInReport("Final Outcome                : " + arrFO[1]);
                            driverFacade.take_screenShot();
                            //System.out.println(arrFO[1]);
                            //System.out.println(driverFacade.findElement(finalOutcome).getWebElementText());
                            System.out.println("**************************");
                            System.out.println(driverFacade.findElement(callerscript).getWebElementText());

                            //driverFacade.switchTo().defaultContent();
                            driverFacade.SwitchToDefaultContext();



                            driverFacade.HardWait(3000);

                            // Capture Case ID and close case
                            String CI = driverFacade.findElement(caseidheader).getWebElementText();
                            String arrCI[] = CI.split("/");
                            //System.out.println(arrCI[2]);
                            String arr2CI[] = arrCI[2].split("- Sav");
                            //driverFacade.writeInReport("Final Outcome                : " + arrFO[1];
                            driverFacade.writeInReport("Case ID                      : " + arr2CI[0]);
                            driverFacade.take_screenShot();
                            //System.out.println(arr2CI[0]);
                            //System.out.println(driverFacade.findElement(caseidheader).getWebElementText());
                            driverFacade.findElement(saveclosebtn).clickWebElement();
                            driverFacade.HardWait(5000);

                            if (!arrCI[2].equals("")){
                                date1 = dateFormat.format(date);
                                connection.executeUpdate("UPDATE OutcomeRulesHDA SET Case_ID='"+ arr2CI[0] + "' WHERE TCRef='" + TCid[i] + "'");
                                connection.executeUpdate("UPDATE OutcomeRulesHDA SET Exec_Date='"+ date1 + "' WHERE TCRef='" + TCid[i] + "'");
                            }


                            if ((Disposition_Description[j]).equals(arrID[1])){
                                connection.executeUpdate("UPDATE OutcomeRulesHDA SET Disposition_Validation='Pass' WHERE TCRef='" + TCid[i] + "'");
                                //System.out.println(Disposition_Description[j]);
                                driverFacade.writeInReportGreen(" Disposition validation Successful");
                            }
                            else {
                                connection.executeUpdate("UPDATE OutcomeRulesHDA SET Disposition_Validation='Fail' WHERE TCRef='" + TCid[i] + "'");
                                //System.out.println(Disposition_Description[j]);
                                driverFacade.writeInReportRed(" Disposition validation Failed");

                            }

                            try {
                                recordset3 = connection.executeQuery("Select * from HDAOutcomedesc WHERE OUTCOME_ID ='" + OUTCOME_ID[i] + "'");
                                outcomedetails = recordset3.getCount();
                                for (int k = 1; k <= outcomedetails; k++) {
                                    recordset3.moveNext();
                                    Outcome_Description[k] = recordset3.getField("OUTCOME_TXT");

                                    if ((Outcome_Description[k]).equals(confirmedIO)){
                                        connection.executeUpdate("UPDATE OutcomeRulesHDA SET Outcome_Validation='Pass' WHERE TCRef='" + TCid[i] + "'");
                                       // System.out.println(Outcome_Description[k]);
                                        driverFacade.writeInReportGreen(" Outcome validation Successful");
                                    }
                                    else {
                                        connection.executeUpdate("UPDATE OutcomeRulesHDA SET Outcome_Validation='Fail' WHERE TCRef='" + TCid[i] + "'");
                                        //System.out.println(Outcome_Description[k]);
                                        driverFacade.writeInReportRed(" Outcome validation Failed");

                                    }
                                    driverFacade.writeInReport(" *****************************************************************************");

                                }
                            } catch (FilloException e){
                                e.printStackTrace();
                            }















                        }

                    } catch (FilloException e){
                        e.printStackTrace();
                    }


                }
            }


        } catch (FilloException e){
            e.printStackTrace();
        }



        driverFacade.exitpage();
        

    }


    @Override
    public void VerifyPage(String businessError) {

    }

    public void loadmanualcasepage() {
        // Case Creation COMMENTED
        /*
        By Encounterbtn = By.xpath("//img[@title='Encounter']");
        driverFacade.findElement(Encounterbtn).clickWebElement();
        driverFacade.HardWait(6000);
        By Newcasebtn = By.xpath("//img[@title='New Case']");
        driverFacade.findElement(Newcasebtn).clickWebElement();
        driverFacade.HardWait(6000);
        //driverFacade.manage().window().maximize();
        driverFacade.HardWait(8000);
        */
        // By callType = By.xpath("//select[@data-id='hd_calltype.fieldControl-option-set-select']");
        //driverFacade.findElement(callType).setText("Quick Call");
    }

    public void entercallerdetails() throws Exception {

        By Patientlookup = By.xpath("//input[@data-id='hd_patient.fieldControl-LookupResultsDropdown_hd_patient_textInputBox_with_filter_new']");
        driverFacade.findElement(Patientlookup).clickWebElement();
        By NewPatientBtn = By.xpath("//*[contains(@id,'addNewBtn')]");


        driverFacade.findElement(NewPatientBtn).clickWebElement();
        //By Newpatientbtn = By.id("id-556f051d-a167-4cfe-9b71-70fa891d64aa-4-hd_patient270bd3db-d9af-4782-9025-509e298dec0a-hd_patient.fieldControl-LookupResultsDropdown_hd_patient_0_addNewBtnContainer_0");
        //driverFacade.findElement(Newpatientbtn).clickWebElement();

        driverFacade.HardWait(5000);
        // driverFacade.manage().window().maximize();
        //New contact fields

        By mobileNo = By.xpath("//input[@data-id='mobilephone.fieldControl-phone-text-input']");
        driverFacade.findElement(mobileNo).clickWebElement();
        driverFacade.findElement(mobileNo).setText(driverFacade.GetPhonenum());
        driverFacade.HardWait(1000);
        By firstName = By.xpath("//input[@data-id='firstname.fieldControl-text-box-text']");
        driverFacade.findElement(firstName).sendKeys("CTRL+A");
        driverFacade.findElement(firstName).setText("CRME2E");
        //driverFacade.findElement(firstName).
        driverFacade.HardWait(1000);
        By lastName = By.xpath("//input[@data-id='lastname.fieldControl-text-box-text']");
        driverFacade.findElement(lastName).sendKeys("CTRL+A");
        driverFacade.findElement(lastName).setText("Test");
        driverFacade.HardWait(2000);
        //By DOB = By.xpath( "//INPUT[@DATA-ID='BIRTHDATE.FIELDCONTROL-DATE-TIME-INPUT']");
        By DOB = By.xpath("//*[contains(@id,'DatePicker')]");
        //By DOB = By.xpath( "//input[@data-id='birthdate.fieldControl-date-time-input']");
        //driverFacade.findElement(DOB).setText("03/08/1987");
        //driverFacade.findElement(DOB).sendKeys("11/10/2000");
        //driverFacade.HardWait(2000);
        //driverFacade.findElement(DOB).clickWebElement().setText("03/08/1987");


        //driverFacade.JSExecutorDate("DatePicker42-label","03/08/1987");
        //driverFacade.findElement(DOB).sendKeys("CTRL+A").clickAndSetText("03/08/1987").sendKeys("TAB");
        //driverFacade.findElement(DOB).sendKeys("CTRL+A").setText("03/08/1987");
        driverFacade.HardWait(1000);
        //driverFacade.findElement(DOB).clickWebElement().sendKeys("CTRL+A").setText("03/08/1987");
        //driverFacade.findElement(DOB).setText("03/08/1987")

        //driverFacade.findElement(DOB).sendKeys(driverFacade.GetDOB());
        driverFacade.HardWait(3000);
        //driverFacade.findElement(DOB).sendKeys("CTRL+A").setText("11/10/2000");
        By Sex = By.xpath("//select[@data-id='hd_sex.fieldControl-option-set-select']");
        driverFacade.findElement(Sex).setText("Male");
        driverFacade.HardWait(2000);
        //Select sel = new Select(Sex);
        //sel.selectByValue("Male").

         By Indigenous = By.xpath("//select[@data-id='hd_atsistatus.fieldControl-option-set-select']");
         driverFacade.findElement(Indigenous).setText("No");
        //Select indigenous = new Select(Indigenous);
        //indigenous.selectByIndex(1);
        driverFacade.HardWait(1000);
        By Address = By.xpath( "//input[@id='caddress']");
        driverFacade.findElement(Address).setText(driverFacade.GetPostcode());


        driverFacade.HardWait(3000);


        By addSugg = By.xpath("//div[@id='addressSuggestions']//li[1]");
        driverFacade.findElement(addSugg).clickWebElement();

        driverFacade.HardWait(1000);
        driverFacade.take_screenShot();

        driverFacade.HardWait(2000);
        driverFacade.findElement(DOB).clickWebElement().sendKeys("CTRL+A").setText("03/08/1987").sendKeys("TAB");
        driverFacade.HardWait(2000);

        By saveAndClose = By.xpath("//button[@data-id='quickCreateSaveAndCloseBtn']");
        driverFacade.findElement(saveAndClose).clickWebElement();

        driverFacade.HardWait(8000);

        By duplicaterecord = By.id("dialogButtonText_id-125fc733-aabe-4bd2-807e-fd7b6da72779-5");
        driverFacade.findElementoptional(duplicaterecord).clickWebElementOptional();
        driverFacade.HardWait(10000);

        driverFacade.take_screenShot();
    }

    public void ientercallsource() {
        //flyoutcomponent

        By flyOutbutton = By.xpath("//button[@data-id='header_overflowButton']");
        driverFacade.findElement(flyOutbutton).clickWebElement();

        driverFacade.HardWait(2000);

        By statedrop = By.xpath("//select[@data-id='header_hd_state.fieldControl-option-set-select']");
        driverFacade.findElement(statedrop).setText("WA");

        driverFacade.HardWait(2000);

        By callSource = By.xpath("//select[@data-id='header_hd_callsource.fieldControl-option-set-select']");
        driverFacade.findElement(callSource).setText("HIAS_Gen Public");

        driverFacade.HardWait(2000);

        By emergencyFlyout = By.xpath("//div[contains(@id, 'MscrmControls.Containers.ProcessBreadCrumb-stageIndicatorInnerHolder')]");
        driverFacade.findElement(emergencyFlyout).clickWebElement();

        driverFacade.HardWait(2000);

        By reasnForCall = By.xpath("//textarea[@data-id='header_process_hd_reasonforcall.fieldControl-text-box-text']");
        driverFacade.findElement(reasnForCall).sendKeys("CTRL+A");
        driverFacade.findElement(reasnForCall).setText("Test");
        driverFacade.take_screenShot();

        By curAddSameAsResiden = By.xpath("//select[@data-id='hd_currentlocationsameasresidential.fieldControl-option-set-select']");
        //select[@data-id='hd_currentlocationsameasresidential.fieldControl-option-set-select']

        //By curAddSameAsResiden = By.xpath("//*[contains(@data-id,'hd_currentlocationsameasresidential.fieldControl-option-set-select')]");
        //By curAddSameAsResiden = By.xpath("//select[@data-id='header_process_hd_currentlocationsameasresidential.fieldControl-option-set-select']");
        driverFacade.findElement(curAddSameAsResiden).setText("Yes");
        driverFacade.HardWait(4000);
        By ProfileCheck = By.id("Toggle0");

        driverFacade.findElement(ProfileCheck).clickWebElement();
        driverFacade.HardWait(4000);
    }

    public void savecase() {
        //driverFacade.HardWait(8000);

        //By saveEncounter = By.xpath("//img[@title='Save']");
        //driverFacade.findElement(saveEncounter).clickWebElement();

        driverFacade.HardWait(15000);
        By saveandcontinue = By.xpath("//button[@aria-label='Save and continue']");
        driverFacade.findElementoptional(saveandcontinue).clickWebElementOptional();

        driverFacade.findElement(emergencyFlyout).clickWebElement();

        driverFacade.HardWait(5000);

        By nextPage = By.xpath("//label[@id='MscrmControls.Containers.ProcessStageControl-Next Stage']");
        driverFacade.findElement(nextPage).clickWebElement();

        driverFacade.HardWait(8000);

        //By curAddSameAsResiden = By.xpath("//select[@data-id='header_process_hd_currentlocationsameasresidential.fieldControl-option-set-select']");
        //driverFacade.findElement(curAddSameAsResiden).setText("Yes");

        //driverFacade.HardWait(1000);
        //driverFacade.take_screenShot();

        //driverFacade.findElement(nextPage).clickWebElement();

        //driverFacade.HardWait(5000);

    }

    public void entertriage() {

        //driverFacade.switchTo().frame("WebResource_triageWR");
        By framepath = By.xpath("//iframe[@id='WebResource_triageWR']");
        // Outcome modal handling
        driverFacade.SwitchTo(framepath);

        By symptom = By.xpath("//input[@id='SearchInput']");
        driverFacade.findElement(symptom).clickWebElement();
        //driverFacade.findElement(symptom).sendKeys("cat"," ","scratch");
        driverFacade.findElement(symptom).setText("cat");
        driverFacade.findElement(symptom).setText(" ").setText("scratch");
        //driverFacade.findElement(symptom).setText(" ");
        ///driverFacade.findElement(symptom).setText("scratch ");
        //driverFacade.HardWait(4000);
        //driverFacade.findElement(symptom).sendKeys("scratch");
        //driverFacade.findElement(symptom).sendKeys(Keys.ARROW_DOWN);

        driverFacade.HardWait(6000);

        By symptomSugg = By.xpath("//ul[@id='lstAllSymptoms']");
        //ul[@id='lstAllSymptoms']/li[contains(text(), 'cat scratch']
        driverFacade.findElement(symptomSugg).clickWebElement();

        driverFacade.HardWait(2000);

        driverFacade.findElement(symptom).sendKeys("PageDown");


        driverFacade.HardWait(3000);

        By saveTriage = By.xpath("//button[@class='saveTriage-btn']");
        driverFacade.take_screenShot();
        driverFacade.findElement(saveTriage).clickWebElement();
        driverFacade.HardWait(10000);

        driverFacade.findElement(By.tagName("Body")).sendKeys("PageUp");
        driverFacade.findElement(By.tagName("Body")).sendKeys("PageUp");

        driverFacade.HardWait(2000);
/*
        By interviewQ = By.xpath("//input[@id='rdbtn_No_p_389']");
        driverFacade.findElement(interviewQ).clickWebElement();
        driverFacade.HardWait(1000);
        driverFacade.take_screenShot();

        By interviewNext = By.xpath("//button[@id='btnNext']");
        driverFacade.findElement(interviewNext).clickWebElement();
        driverFacade.HardWait(10000);
*/
        //driverFacade.switchTo().frame("WebResource_triageWR");
        By confirmDisposition = By.xpath("//button[@id='btnConfirmDisposition']");
        driverFacade.findElement(confirmDisposition).clickWebElement();

        driverFacade.HardWait(15000);
        driverFacade.take_screenShot();

        driverFacade.findElement(By.tagName("Body")).sendKeys("PageDown");

        driverFacade.HardWait(5000);

        By confirmCare = By.xpath("//button[@id='btnConfirmOutcome']");
        driverFacade.findElement(confirmCare).clickWebElement();
        driverFacade.take_screenShot();

        driverFacade.HardWait(18000);
        driverFacade.scrollDown();
        By interviewNext = By.xpath("//button[@id='btnNext']");
        driverFacade.findElement(interviewNext).clickWebElement();
        driverFacade.HardWait(3000);

        driverFacade.SwitchToDefaultContext();
        driverFacade.HardWait(3000);


        //  By nextPage = By.xpath("//button[@id='btnConfirmOutcome']");
    }

    public void servicesearch(){
        //driverFacade.switchTo().frame("WebResource_ServiceProviderSearch");
        By framepath = By.xpath("//iframe[@id='WebResource_ServiceProviderSearch']");
        // Outcome modal handling
        driverFacade.SwitchTo(framepath);
        //click search
        By search = By.xpath("//button[text()='Search']");
        driverFacade.findElement(search).clickWebElement();
        driverFacade.HardWait(15000);
        driverFacade.SwitchToDefaultContext();
        By OKBtn = By.xpath("//button[@aria-label='OK']");
        driverFacade.findElementoptional(OKBtn).clickWebElementOptional();

        //Wait for page to load before clicking homeVisit tab
        By homeVisitTab = By.cssSelector("#btnHomeVisits");
        driverFacade.SwitchTo(framepath);
        // Perform actions on the enabled WebElement
        driverFacade.findElement(homeVisitTab).clickWebElement();
        driverFacade.HardWait(10000);
        //driverFacade.SwitchToDefaultContext();
        //driverFacade.findElementoptional(OKBtn).clickWebElementOptional();

        //Wait for page to load before clicking phoneAndOnline tab
        By phoneAndOnlineTab = By.cssSelector("#btnPhoneAndOnline");
        //driverFacade.SwitchTo(framepath);
        driverFacade.findElement(phoneAndOnlineTab).clickWebElement();
        driverFacade.HardWait(10000);
        //driverFacade.SwitchToDefaultContext();
        //driverFacade.findElementoptional(OKBtn).clickWebElementOptional();

        //click ServiceNearBy tab
        By serviceNearByTab = By.cssSelector("#btnServiceNearby");
        //driverFacade.SwitchTo(framepath);
        driverFacade.findElement(serviceNearByTab).clickWebElement();
        driverFacade.HardWait(10000);
        driverFacade.SwitchToDefaultContext();
        //driverFacade.findElementoptional(OKBtn).clickWebElementOptional();
        //click MoreInfo link

        //click Back to Search Results
        By backToSearchResult = By.cssSelector("#closeInfo");


        //switch from service provider iframe to default content
        //driverFacade.switchTo().defaultContent();
        //driverFacade.SwitchToDefaultContext();
        driverFacade.HardWait(3000);
       // By moreInfoLink = By.xpath("(//a[text()='More Info'])[1]");
        //driverFacade.findElement(moreInfoLink).clickWebElement();
        //driverFacade.HardWait(3000);
        //driverFacade.findElement(backToSearchResult).clickWebElement();
    }

    public void completecase() {

        By ServiceTab = By.xpath("//div[@data-id='MscrmControls.Containers.ProcessBreadCrumb-stageIndicatorHolder961a743f-0784-4b7f-ac71-63aaa812630f']");
        driverFacade.findElement(ServiceTab).clickWebElement();
        driverFacade.findElement(nextPage).clickWebElement();
        By OKBtn = By.xpath("//button[@aria-label='OK']");
        driverFacade.findElementoptional(OKBtn).clickWebElementOptional();
        driverFacade.findElement(nextPage).clickWebElement();
        driverFacade.findElementoptional(OKBtn).clickWebElementOptional();
        //driverFacade.findElement(nextPage).clickWebElement();
        //driverFacade.HardWait(5000);

        By origIncline = By.xpath("//select[@data-id='header_process_hd_originalinclination.fieldControl-option-set-select']");
        driverFacade.findElement(origIncline).setText("Did not know");

        driverFacade.HardWait(1000);

        By finishBut = By.xpath("//label[@id='MscrmControls.Containers.ProcessStageControl-Finish']");
        driverFacade.findElement(finishBut).clickWebElement();

        driverFacade.HardWait(10000);

        //By resolveEncounter = By.xpath("//img[@title='Resolve Case']");
        //driverFacade.findElement(resolveEncounter).clickWebElement();

        //driverFacade.HardWait(10000);
        driverFacade.take_screenShot();

        String CI = driverFacade.findElement(caseidheader).getWebElementText();
        String arrCI[] = CI.split("/");
        //System.out.println(arrCI[2]);
        String arr2CI[] = arrCI[2].split("- Sav");
        //driverFacade.writeInReport("Final Outcome                : " + arrFO[1];
        driverFacade.writeInReport("Case ID                      : " + arr2CI[0]);


        By saveaClose = By.xpath("//div[@role='presentation']/ul[@role='menubar']/li/button[@data-id='incidentresolution|NoRelationship|Form|Mscrm.Form.incidentresolution.SaveAndClose']");
        //driverFacade.findElement(saveaClose).clickWebElement();
    }
}

