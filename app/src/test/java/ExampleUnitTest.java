import com.example.cxg.boothpinter.utils.WebServiceUtils;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void testHttpPost() {
        HashMap<String, String> properties = new HashMap<>();
        properties.put("Znum", "9000042549");
        List<Object> list = WebServiceUtils.callWebServiceFor005(WebServiceUtils.URL_005, WebServiceUtils.METHOD_NAME_005, properties);
        if (list.size() != 0) {
            System.out.println(list.get(0));
            System.out.println(list.get(1));
        }
    }
}