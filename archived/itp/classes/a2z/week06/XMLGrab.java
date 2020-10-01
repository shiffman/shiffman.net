/* Daniel Shiffman               */
/* Programming from A to Z       */
/* Spring 2006                   */
/* http://www.shiffman.net       */
/* daniel.shiffman@nyu.edu       */

// Generic XML Parser

import java.io.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import org.xml.sax.*;
import java.net.*;

public class XMLGrab {
    
  public static void main (String argv []){
    try {
      DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
      
      //URL url = new URL("http://www.shiffman.net/itp/classes/a2z/week06/xml/test.xml");
      //URL url = new URL("http://xml.weather.yahoo.com/forecastrss?p=10003");
      URL url = new URL("http://www.walking-productions.com/slop/index.xml");
      
      InputStream is = url.openStream();
      Document doc = docBuilder.parse(is);
      
      doc.getDocumentElement().normalize();
      String rootTag = doc.getDocumentElement().getNodeName();
      System.out.println ("Root element of the doc is " + rootTag);
      
      NodeList nodes = doc.getChildNodes();
      goXML(nodes,rootTag);
      
    } catch (SAXParseException e) {
      System.out.println("Error: line " + e.getLineNumber() + ": uri " + e.getSystemId());
      System.out.println("Error: " + e.getMessage());
    } catch (SAXException e) {
      System.out.println("SAS Error: " + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace ();
    }
  }
  
  private static void goXML(NodeList nodes, String tag) {
    for (int i = 0; i < nodes.getLength(); i++) {
      Node child = nodes.item(i);
      if (child.getNodeType() == Node.ELEMENT_NODE) {
        Element element = (Element) child;
        String currentTag = element.getNodeName();
        if (element.hasChildNodes()) {
          NodeList children = element.getChildNodes();
          goXML(children, currentTag);
        } 
      } else {
        System.out.println(tag + ": " + child.getNodeValue().trim());
      }
    }
    
  }
}