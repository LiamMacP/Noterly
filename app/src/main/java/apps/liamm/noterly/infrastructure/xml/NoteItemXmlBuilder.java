package apps.liamm.noterly.infrastructure.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import apps.liamm.noterly.data.entities.noteitems.BaseItem;
import apps.liamm.noterly.data.entities.noteitems.BaseItem.NoteItemType;
import apps.liamm.noterly.data.entities.noteitems.TextItem;
import apps.liamm.noterly.infrastructure.helpers.StringUtils;

public class NoteItemXmlBuilder {

    private static final String NOTE_TAG = "Note";
    private static final String NOTE_ITEM_TAG = "NoteItem";
    private static final String NOTE_TYPE_ATTR_NAME = "Type";
    private static final String ITEM_TAG = "Item";

    private DocumentBuilder documentBuilder;

    public NoteItemXmlBuilder() throws ParserConfigurationException {
        documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    public String toXmlString(List<BaseItem> baseItems) {
        try {
            Document document = documentBuilder.newDocument();

            // Create root element
            Element rootElement = document.createElement(NOTE_TAG);
            document.appendChild(rootElement);

            // Loop each base item to add to document.
            for (BaseItem item :
                    baseItems) {

                switch (item.getType()) {
                    case TEXT:
                        rootElement.appendChild(createTextItemElement(document, (TextItem)item));
                        break;
                    case CHECKLIST:
                        break;
                    case PICTURE:
                        break;
                    default:
                }
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(document), new StreamResult(writer));
            return writer.getBuffer().toString();
        }
        catch (TransformerException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return StringUtils.EMPTY;
    }

    private Element createTextItemElement(Document document, TextItem item) {
        Element noteItemElement = document.createElement(NOTE_ITEM_TAG);
        noteItemElement.setAttribute(NOTE_TYPE_ATTR_NAME, String.valueOf(item.getType().ordinal()));

        Element itemElement = document.createElement(ITEM_TAG);
        itemElement.setTextContent(item.getContent());

        noteItemElement.appendChild(itemElement);

        return noteItemElement;
    }

    public List<BaseItem> parseXmlString(String noteItemXmlString) throws IOException, SAXException {
        List<BaseItem> baseItems = new ArrayList<>();

        Document doc = documentBuilder.parse(new InputSource(new StringReader(noteItemXmlString)));

        NodeList noteItemNodes = doc.getElementsByTagName(NOTE_ITEM_TAG);
        if (noteItemNodes != null) {
            for (int i = 0; i < noteItemNodes.getLength(); ++i) {
                Element node = (Element)noteItemNodes.item(i);

                String itemTypeString = node.getAttributes().getNamedItem(NOTE_TYPE_ATTR_NAME).getNodeValue();
                NoteItemType itemType = NoteItemType.values()[Integer.parseInt(itemTypeString)];

                switch (itemType) {
                    case TEXT:
                        baseItems.add(readTextItemElement(node));
                        break;
                    case CHECKLIST:
                        break;
                    case PICTURE:
                        break;
                    default:
                }
            }
        }
        return baseItems;
    }

    private TextItem readTextItemElement(Element textItemElement) {
        TextItem textItem = new TextItem();

        if (textItemElement == null) {
            return textItem;
        }

        NodeList nodes = textItemElement.getElementsByTagName(ITEM_TAG);

        if (nodes.getLength() == 0) {
            return textItem;
        }

        textItem.setContent(nodes.item(0).getTextContent());
        return textItem;
    }

}
