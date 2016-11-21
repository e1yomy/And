package com.jamg.example.serviciosweb.webservice;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.net.Proxy;

/**
 * Created by JAMG on 09/11/16.
 */

public class Arithmetic {
    private static final String NAMESPACE = "http://felixvalencia.site88.net/webservice/";
    private static final String URL = "http://felixvalencia.site88.net/webservice/server.php";

    public static double sum(double a, double b) {
        final String METHOD_NAME_1 = "suma";
        final String SOAP_ACTION_1 = "http://felixvalencia.site88.net/webservice/server.php/suma";

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_1);

        PropertyInfo prop1 = new PropertyInfo();
        prop1.setType(Double.class);
        prop1.setName("a");
        prop1.setValue(a);
        request.addProperty(prop1);

        PropertyInfo prop2 = new PropertyInfo();
        prop2.setType(Double.class);
        prop2.setName("b");
        prop2.setValue(b);
        request.addProperty(prop2);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.implicitTypes = true;

        envelope.bodyOut = request;
        new MarshalDouble().register(envelope);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(Proxy.NO_PROXY, URL, 6000);
        androidHttpTransport.debug = true;
        androidHttpTransport.setXmlVersionTag("<!--?xml version=\"1.0\" enconding=\"UTF-8\" ?-->");

        Double response = 0.0;

        try {
            androidHttpTransport.call(SOAP_ACTION_1, envelope);
            response = (Double) envelope.getResponse();
        } catch (SoapFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return response;
    }

    public static double sub(double a, double b) {
        final String METHOD_NAME_1 = "resta";
        final String SOAP_ACTION_1 = "http://felixvalencia.site88.net/webservice/server.php/resta";

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_1);

        PropertyInfo prop1 = new PropertyInfo();
        prop1.setType(Double.class);
        prop1.setName("a");
        prop1.setValue(a);
        request.addProperty(prop1);

        PropertyInfo prop2 = new PropertyInfo();
        prop2.setType(Double.class);
        prop2.setName("b");
        prop2.setValue(b);
        request.addProperty(prop2);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.implicitTypes = true;

        envelope.bodyOut = request;
        new MarshalDouble().register(envelope);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(Proxy.NO_PROXY, URL, 6000);
        androidHttpTransport.debug = true;
        androidHttpTransport.setXmlVersionTag("<!--?xml version=\"1.0\" enconding=\"UTF-8\" ?-->");

        Double response = 0.0;

        try {
            androidHttpTransport.call(SOAP_ACTION_1, envelope);
            response = (Double) envelope.getResponse();
        } catch (SoapFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return response;
    }

    public static double mul(double a, double b) {
        final String METHOD_NAME_1 = "multiplicacion";
        final String SOAP_ACTION_1 = "http://felixvalencia.site88.net/webservice/server.php/multiplicacion";

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_1);

        PropertyInfo prop1 = new PropertyInfo();
        prop1.setType(Double.class);
        prop1.setName("a");
        prop1.setValue(a);
        request.addProperty(prop1);

        PropertyInfo prop2 = new PropertyInfo();
        prop2.setType(Double.class);
        prop2.setName("b");
        prop2.setValue(b);
        request.addProperty(prop2);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.implicitTypes = true;

        envelope.bodyOut = request;
        new MarshalDouble().register(envelope);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(Proxy.NO_PROXY, URL, 6000);
        androidHttpTransport.debug = true;
        androidHttpTransport.setXmlVersionTag("<!--?xml version=\"1.0\" enconding=\"UTF-8\" ?-->");

        Double response = 0.0;

        try {
            androidHttpTransport.call(SOAP_ACTION_1, envelope);
            response = (Double) envelope.getResponse();
        } catch (SoapFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return response;
    }

    public static double div(double a, double b) {
        final String METHOD_NAME_1 = "division";
        final String SOAP_ACTION_1 = "http://felixvalencia.site88.net/webservice/server.php/division";

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME_1);

        PropertyInfo prop1 = new PropertyInfo();
        prop1.setType(Double.class);
        prop1.setName("a");
        prop1.setValue(a);
        request.addProperty(prop1);

        PropertyInfo prop2 = new PropertyInfo();
        prop2.setType(Double.class);
        prop2.setName("b");
        prop2.setValue(b);
        request.addProperty(prop2);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        envelope.dotNet = true;
        envelope.encodingStyle = SoapSerializationEnvelope.XSD;
        envelope.implicitTypes = true;

        envelope.bodyOut = request;
        new MarshalDouble().register(envelope);

        HttpTransportSE androidHttpTransport = new HttpTransportSE(Proxy.NO_PROXY, URL, 6000);
        androidHttpTransport.debug = true;
        androidHttpTransport.setXmlVersionTag("<!--?xml version=\"1.0\" enconding=\"UTF-8\" ?-->");

        Double response = 0.0;

        try {
            androidHttpTransport.call(SOAP_ACTION_1, envelope);
            response = (Double) envelope.getResponse();
        } catch (SoapFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return response;
    }
}
