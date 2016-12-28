import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SomeClass {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(SomeClass.class);

	private void catchVide(){
		try {
			someObject.something();
		} catch(Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	private void throwWrappedRuntimeException(){
		try {
			someObject.something();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void throwOriginalException(){
		try {
			someObject.something();
		} catch(Exception e) {
			throw e;
		}
	}
}