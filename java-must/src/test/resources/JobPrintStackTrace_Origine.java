import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SomeClass {

    private final static Logger LOG = LoggerFactory
            .getLogger(SomeClass.class.getName());

    private void doSomething() {
        try {
            someObject.something();
        } catch (Exception e) {
            e.printStackTrace();
			return;
        }
    }
}