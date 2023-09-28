package helper;

import java.io.IOException;

/** This is the MiscInterface.java functional interface. */
@FunctionalInterface
public interface MiscInterface {
    void doSomething(String s) throws IOException;
}

