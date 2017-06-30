import com.example.cxg.boothpinter.utils.WebServiceUtils;

import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void testHttpPost() {
        String properties = "9000000048";
        List<Object> list = WebServiceUtils.callWebServiceFor005(WebServiceUtils.URL_005, WebServiceUtils.METHOD_NAME_005, properties);
        if (list.size() != 0) {
            System.out.println(list.get(0));
            System.out.println(list.get(1));
        }
    }

    @Test
    public void init2(){
        Map<String,String> map = WebServiceUtils.callWebServiceFor004(WebServiceUtils.URL_004, WebServiceUtils.METHOD_NAME_004);
        System.out.println(map);
    }
}