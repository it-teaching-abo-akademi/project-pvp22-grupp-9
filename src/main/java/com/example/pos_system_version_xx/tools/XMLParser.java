package com.example.pos_system_version_xx.tools;

import com.example.pos_system_version_xx.models.Product;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;

public class XMLParser {

    /**
     * <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
     *     <product id="1" optLockVersion="1">
     *         <barCode>12345</barCode>
     *         <name>Bananas</name>
     *         <keyword>s</keyword>
     *     </product>
     *
     * <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
     *     <products>
     *         <product id="1" optLockVersion="1">
     *             <barCode>12345</barCode>
     *             <name>Bananas</name>
     *             <keyword>s</keyword>
     *         </product>
     *         <product id="2" optLockversion="1">
     *             <barCode>12125</barCode>
     *             <name>Broccoli</name>
     *             <keyword>vegemables</keyword>
     *         </product>
     *     </products>
     *
     *     <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
     *      *     <products>
     *      *         <product id="1" optLockVersion="1">
     *      *             <barCode>12345</barCode>
     *      *             <name>Bananas</name>
     *      *             <keyword>s</keyword>
     *      *         </product>
     *      *         <product id="2" optLockversion="1">
     *      *             <barCode>12125</barCode>
     *      *             <name>Broccoli</name>
     *      *             <keyword>vegemables</keyword>
     *      *         </product>
     *      *     </products>
     */

    public ArrayList<Product> parseProducts(String xml) {
        ArrayList<Product> products = new ArrayList<Product>();

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xml)));
            document.getDocumentElement().normalize();
            System.out.println("Element present at the root of the tree: " + document.getDocumentElement().getNodeName());

            NodeList elements = document.getElementsByTagName("product");
            for (int i = 0; i < elements.getLength(); i++) {
                String barcode = elements.item(i).getChildNodes().item(0).getTextContent();
                String name = elements.item(i).getChildNodes().item(1).getTextContent();

                products.add(new Product(name, barcode));
            }
        } catch (Exception e) {
            products.clear(); // should return nothing in case of error
            return products;
        }

        return products;
    }
}
