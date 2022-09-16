package sistema.data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class XmlPersister {
    private String path;
    private static XmlPersister theInstance;
    public static XmlPersister instance(){
        if (theInstance==null){
            theInstance=new XmlPersister("sucursales.xml");
        }
        return theInstance;
    }

    public XmlPersister(String path) {
        this.path=path;
    }

    public Data load() throws Exception{
        JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
        FileInputStream is = new FileInputStream(path);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Data result = (Data) unmarshaller.unmarshal(is);
        is.close();
        return result;
    }

    public void store(Data dato)throws Exception{
        JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
        FileOutputStream os = new FileOutputStream(path);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.marshal(dato, os);
        os.flush();
        os.close();
    }
}
