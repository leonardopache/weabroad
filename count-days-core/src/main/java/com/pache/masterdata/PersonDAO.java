/**
 * 
 */
package com.pache.masterdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lpache
 *
 */
public class PersonDAO {

	private static Integer id = 0;
	private static Map<Integer, Person> md = new HashMap<Integer, Person>();

	static {
//		id++;
//		md.put(id, new Person(Person.JU_START, "mail@mail.com", "Ju"));
		id++;
		md.put(id, new Person(Person.LEO_START, "leonardo@pache.eng.br", "Leo"));
	}

	public static List<Person> getAll() {
		return new ArrayList<Person>(md.values());
	}
}
