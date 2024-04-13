package SharedResources;

import java.util.ArrayList;
import java.util.Collection;

public class SharedResult {

    private Collection<String> result;

    public SharedResult() {
        result = new ArrayList<String>();
    }

    public synchronized void addOutput(String output) {
        result.add(output);
    }

    public void printResult() {
        System.out.println("Number of tasks: " + result.size());
        result.forEach(System.out::println);
    }
}
