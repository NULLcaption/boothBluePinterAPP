package com.example.cxg.boothpinter.utils;


import com.example.cxg.boothpinter.pojo.Zswm003;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * description: webservice服务工具类
 * author: xg.chen
 * date: 2017/6/26 14:07
 * version: 1.0
 */
public class WebServiceUtils {

    //命名空间
    public static String NAMESPACE = "urn:sap-com:document:sap:soap:functions:mc-style";
    //请求方法名
    public static String METHOD_NAME_004 = "ZwmRfcIts004";//获取单位
    public static String METHOD_NAME_005 = "ZwmRfcIts005";//根据单据号码获取相关信息

    //请求路径
    public static String SOAP_ACTION_004 = NAMESPACE + "/" + METHOD_NAME_004;
    public static String SOAP_ACTION_005 = NAMESPACE + "/" + METHOD_NAME_005;

    //请求的webservice路径
    public static final String URL_004 = "http://192.168.0.16:8000/sap/bc/srt/rfc/sap/zwmits4/700/zwmits4/binding?sap-client=700&sap-user=rfc&sap-password=poiuyt";
    public static final String URL_005 = "http://192.168.0.16:8000/sap/bc/srt/rfc/sap/zwmits5/600/zwmits5/binding?sap-client=600&sap-user=abaprfc&sap-password=xpp2@12";

    /**
     * 根据单据号码获取相关信息
     *
     * @param url        请求URL
     * @param methodName 请求的参数名
     * @param properties        请求参数
     * @return list
     */
    public static List<Object> callWebServiceFor005(String url, String methodName, HashMap<String, String> properties) {
        List<Object> resultList = new ArrayList<>();
        // set up
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        // SoapObject添加参数
        if (properties != null) {
            for (Iterator<Map.Entry<String, String>> it = properties.entrySet().iterator(); it.hasNext(); ) {
                Map.Entry<String, String> entry = it.next();
                request.addProperty(entry.getKey(), entry.getValue());
            }
        }

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10); // put all required data into a soap
        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(url);
        httpTransport.debug = true;
        try {
            httpTransport.call(SOAP_ACTION_005, envelope);
            if (envelope.bodyIn instanceof SoapObject) { // SoapObject = SUCCESS
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                //解析后的返回list
                resultList = parseSoapObject(soapObject);
                return resultList;
            } else if (envelope.bodyIn instanceof SoapFault) {
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                try {
                    throw new Exception(soapFault.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;
    }

    /**
     * Soap Object解析返回值
     * @param result 返回值
     * @return List
     */
    public static List<Object> parseSoapObject(SoapObject result) {
        List<Object> list = new ArrayList<>();

        String Zlfa1 = result.getProperty("Zlfa1").toString();
        String Zlips = result.getProperty("Zlips").toString();

        list.add(Zlfa1);
        list.add(Zlips);

        return list;
    }


    /**
     * 请求ZwmRfcIts004接口获取单位列表
     *
     * @param url        请求URL
     * @param methodName 请求的参数名
     * @return map
     */
    public static Map<String, String> callWebServiceFor004(String url, final String methodName) {
        //返回的结果集
        Map<String, String> map = new HashMap<>();
        // set up
        SoapObject request = new SoapObject(NAMESPACE, methodName);
        // SoapObject添加参数
        Zswm003 zswm003 = new Zswm003();
        request.addProperty("EtZswm003", zswm003);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER10); // put all required data into a soap
        envelope.dotNet = false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE httpTransport = new HttpTransportSE(url);
        httpTransport.debug = true;
        try {
            httpTransport.call(SOAP_ACTION_004, envelope);
            if (envelope.bodyIn instanceof SoapObject) {
                SoapObject soapObject = (SoapObject) envelope.bodyIn;
                //解析后的返回list
                map = parseSoapObject004(soapObject);
                return map;
            } else if (envelope.bodyIn instanceof SoapFault) {
                SoapFault soapFault = (SoapFault) envelope.bodyIn;
                try {
                    throw new Exception(soapFault.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Soap Object解析返回值
     *
     * @param result 获取到的值
     * @return map
     */
    public static Map<String, String> parseSoapObject004(SoapObject result) {
        Map<String, String> map = new HashMap<>();
        SoapObject provinceSoapObject1 = (SoapObject) result.getProperty("EtZswm003");
        if (provinceSoapObject1 == null) {
            return null;
        }
        for (int i = 0; i < provinceSoapObject1.getPropertyCount(); i++) {
            SoapObject soapObject = (SoapObject) provinceSoapObject1.getProperty(i);
            String Msehi = soapObject.getProperty("Msehi").toString();
            String Mseh3 = soapObject.getProperty("Mseh3").toString();
            map.put(Msehi, Mseh3);
        }
        return map;
    }

}
