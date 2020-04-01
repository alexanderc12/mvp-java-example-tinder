package persistence;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class FileIdService {
	
	private static final String ID_TAG = "id";
	private static final String ROOT_TAG = "database";
	private static final String DB_ID_PATH = "./db/id.xml";

	public static void writeIdFile(long id) throws ParserConfigurationException, TransformerFactoryConfigurationError,
	TransformerException {
        Document document = DocumentBuilderFactory.newInstance().
        		newDocumentBuilder().newDocument();
   
        Element rootTag = document.createElement(ROOT_TAG);
        document.appendChild(rootTag);
        
        Element idTag = document.createElement(ID_TAG);
        idTag.setTextContent(String.valueOf(id));
        rootTag.appendChild(idTag);
        
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File(DB_ID_PATH));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, result);
	}

	public static long readIdFile() throws SAXException, IOException, ParserConfigurationException {
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(DB_ID_PATH));
		document.getDocumentElement().normalize();

		Element idTag = (Element) document.getElementsByTagName(ID_TAG).item(0);
		
		return Long.valueOf(idTag.getTextContent());
	}
}