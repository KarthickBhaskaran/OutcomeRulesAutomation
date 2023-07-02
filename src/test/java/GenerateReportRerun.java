import java.io.*;

public class GenerateReportRerun {

    public static void main(String args[]) throws IOException {
        File log = new File("test-output/HtmlReport/ExtentHtml.html");
        System.out.println(log.getCanonicalPath());
        File log1 = new File("test-output/HtmlReport/RerunReport_TravelBox.html");
        FileReader fr = new FileReader(log);
        String s;
        String totalStr = "";

        try (BufferedReader br = new BufferedReader(fr)) {
            while ((s = br.readLine()) != null) {
                if (s.contains("Screenshot goes here")) {

                    s = s.replace("<div class=\"node-step\">Screenshot goes here", "");
                    s = s.replace("</div>", "");
                    s = s.trim();
                    s = "<div class=\"node-step\"><a target=\"_blank\" href=\"../screenshots/" + s + ".png\">Click Here for Screenshot </a><br></div>";

                }

                //test-output/screenshots/TravelBox_1568028134563.png
                totalStr += s;
            }
            // log.delete();
            FileWriter fw = new FileWriter(log1);
            fw.write(totalStr);
            fw.close();
            System.out.println("Report Generated for Failed rerun scenarios!!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
