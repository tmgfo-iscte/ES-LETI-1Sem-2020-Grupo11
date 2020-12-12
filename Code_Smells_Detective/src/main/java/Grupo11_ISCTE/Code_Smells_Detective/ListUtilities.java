package Grupo11_ISCTE.Code_Smells_Detective;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** Represents the list of utilities.
* This class offers static methods to make unions and intersections of the contents of Lists (e.g. ArrayLists).
* @author Marcelo Pereira
* @version 1.0
* @since 1.0
*/

public class ListUtilities {
	/**
	 * This method makes the union of the objects of two lists.
	 * @param <T> is any kind of object
	 * @param list1 is the first list
	 * @param list2 is the second list
	 * @return a list with the union of the objects of the two lists.
	 */
	public static <T> List<T> union(List<T> list1, List<T> list2) {
        Set<T> set = new HashSet<T>();

        set.addAll(list1);
        set.addAll(list2);

        return new ArrayList<T>(set);
    }

	/**
	 * This method makes the intersection between the objects of two lists.
	 * @param <T> is any kind of object.
	 * @param list1 is the first list.
	 * @param list2 is the second list.
	 * @return a list with the intersection between the objects of the two lists.
	 */
    public static <T> List<T> intersection(List<T> list1, List<T> list2) {
        List<T> list = new ArrayList<T>();

        for (T t : list1) {
            if(list2.contains(t)) {
                list.add(t);
            }
        }

        return list;
    }
	
	
}
