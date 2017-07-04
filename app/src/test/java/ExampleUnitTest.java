import com.example.cxg.boothpinter.pojo.Zslfa1;
import com.example.cxg.boothpinter.pojo.Zslips;
import com.example.cxg.boothpinter.pojo.Ztwm004;
import com.example.cxg.boothpinter.utils.WebServiceUtils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {

    @Test
    public void testHttpPost() {
        String properties = "9000042549";
        List<Object> list = WebServiceUtils.callWebServiceFor005(WebServiceUtils.URL_005, WebServiceUtils.METHOD_NAME_005, properties);
        Ztwm004 ztwm004 = new Ztwm004();
        List<Zslips> zslipsList = new ArrayList<>();
        if (list.size() != 0) {
            Zslfa1 zlfa1 = (Zslfa1) list.get(0);
            ztwm004.setLifnr(zlfa1.getLifnr());
            ztwm004.setEName1(zlfa1.getName1());
            for (int i =1;i< list.size(); i++){
                Zslips zslips = (Zslips) list.get(i);
                /*System.out.println(zslips);*/
                zslipsList.add(zslips);
            }
            ztwm004.setZslipsList(zslipsList);
            System.out.println(ztwm004);
            System.out.println(ztwm004.getZslipsList());
        }
    }

    @Test
    public void init2() {
        Map<String, String> map = WebServiceUtils.callWebServiceFor004(WebServiceUtils.URL_004, WebServiceUtils.METHOD_NAME_004);
        System.out.println(map);
    }
}