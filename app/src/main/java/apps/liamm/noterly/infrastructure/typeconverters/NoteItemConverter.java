package apps.liamm.noterly.infrastructure.typeconverters;

import androidx.room.TypeConverter;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import apps.liamm.noterly.data.entities.noteitems.BaseItem;
import apps.liamm.noterly.infrastructure.xml.NoteItemXmlBuilder;

public class NoteItemConverter {

    @TypeConverter
    public static String toXmlString(List<BaseItem> items)
            throws ParserConfigurationException {
        return new NoteItemXmlBuilder().toXmlString(items);
    }

    @TypeConverter
    public static List<BaseItem> toBaseItems(String itemsXml)
            throws ParserConfigurationException, IOException, SAXException {
        return new NoteItemXmlBuilder().parseXmlString(itemsXml);
    }
}
