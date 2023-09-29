package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Flot {
    public static void main(String[] args) throws JAXBException, IOException {
        final Ship ship = new Ship(true, 8000, "Vezuchyi",
                new Engine("YMZ", 1500), new String[] {"in the sea", "cargo wheat"});

        JAXBContext context = JAXBContext.newInstance(Ship.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;

        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(ship, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }

        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Ship result = (Ship) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
