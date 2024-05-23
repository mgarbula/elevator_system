package elevator;

import java.util.Iterator;
import java.util.Queue;

public class CompareQueues {

    public static boolean compareQueues(Queue<Integer> expected, Queue<Integer> actual) {
        if (expected.size() != actual.size()) {
            return false;
        }

        Iterator<Integer> exIterator = expected.iterator();
        Iterator<Integer> acIterator = actual.iterator();

        while (exIterator.hasNext()) {
            if (!exIterator.next().equals(acIterator.next())) {
                return false;
            }
        }
        return true;
    }

}
