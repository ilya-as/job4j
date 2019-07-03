package ru.job4j.jdbc.xmlxslt;

import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, JAXBException, IOException, SAXException, ParserConfigurationException {
        StoreSQL storeSQL = new StoreSQL(new Config());
        storeSQL.generate(50);
        File scheme = new File(Main.class.getClassLoader().getResource("transform.xsl").getPath());
        File source = new File(scheme.getParent() + "/temp.xml");
        File dest = new File(scheme.getParent() + "/output.xml");
        StoreXML storeXML = new StoreXML(source);
        storeXML.save(storeSQL.load());
        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(source,dest,scheme);
        ParserSAX parserSAX = new ParserSAX();
        parserSAX.parseFile(dest);
        int sum = parserSAX.getSum();
        System.out.println(sum);
    }
}
