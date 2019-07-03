package ru.job4j.jdbc.xmlxslt;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

public class StoreXML {
    private File target;

    public StoreXML(File target) {
        this.target = target;
    }

    public void save(List<XmlUsage.Entry> list) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(XmlUsage.Entries.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(
                new XmlUsage.Entries(list), this.target
        );
    }
}
