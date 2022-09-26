package less3;

import org.junit.jupiter.api.BeforeAll;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AbstractTest {
    static Properties prop = new Properties();
    private static InputStream configfile;
    private static String apiKey;
    private static String base_url;

    @BeforeAll
    static void InitTest() throws IOException{configfile = new FileInputStream("src/main/resources/my.properties");
        prop.load(configfile);
        apiKey = prop.getProperty("apiKey");
        base_url = prop.getProperty("base_url");
    }
    public static String getApiKey(){return apiKey;}
    public static String getBase_url(){return base_url;}
}