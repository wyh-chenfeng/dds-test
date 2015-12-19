
package test.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class TestHashMap {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.put(null, null);
		
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		
		while (iterator.hasNext()) {
			Entry<String, String> entry = iterator.next();
			
			System.out.println(entry.getKey() + " :: " + entry.getValue());
		}
		
	}

}
