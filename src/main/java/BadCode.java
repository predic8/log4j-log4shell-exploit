import java.io.IOException;

public class BadCode {
    public BadCode() throws IOException {
        System.err.println("Got ya!");

        String os = System.getProperty("os.name");

        if (os.startsWith("Windows")) {
            Runtime.getRuntime().exec("calc");
        } else if("Mac OS X".equals(os)) {
            String[] cmd = { "open", "-a", "Calculator" };
            Runtime.getRuntime().exec(cmd);
        } else {
            // Start Calculator on Linux
        }
    }
}
