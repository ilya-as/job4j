package ru.job4j.jdbc.xmlxslt;

import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class Parser {
    private StoreSQL storeSQL;
    private File scheme;
    private File source;
    private File dest;

    public Parser() {
        this.storeSQL = new StoreSQL(new Config());
        this.scheme = new File(Parser.class.getClassLoader().getResource("transform.xsl").getPath());
        this.source = new File(scheme.getParent() + "/temp.xml");
        this.dest = new File(scheme.getParent() + "/output.xml");
    }

    public void start() throws JAXBException, IOException, SAXException, ParserConfigurationException, SQLException {
        storeSQL.generate(50);
        StoreXML storeXML = new StoreXML(source);
        storeXML.save(storeSQL.load());
        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(source, dest, scheme);
        ParserSAX parserSAX = new ParserSAX();
        parserSAX.parseFile(dest);
        int sum = parserSAX.getSum();
        System.out.println(sum);
    }

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, JAXBException, SQLException {
        Parser parser = new Parser();
        parser.start();
    }
}
