package hw2;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class OSM {
	
	static String extractStringFromVal(String haystack, String needle) {
        String[] array = haystack.split("\\s+");
        for (String a : array) {
            String[] b = a.split("=");
            if (b.length != 2) {
                continue;
            }
            String key = b[0];
            String val = b[1];
            if(needle.compareToIgnoreCase(key) == 0) {
            	return val.replaceAll("\\/", "").replaceAll("\"", "").replaceAll(">", "");
                
            }
        }

        return null;
    }

    
    
}
