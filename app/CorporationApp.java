package corp.app;

public class CorporationApp {
    public static void main(String[] args) {
        String appName = "Corp DEMO";
        System.out.println(appName);
        CorporationControl corpCon = new CorporationControl();
        corpCon.controlLoop();
    }
}

