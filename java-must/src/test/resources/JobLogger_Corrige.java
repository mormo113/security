import org.apache.log4j.Logger;

public class SomeClass {

	private static Logger LOGGER = Logger.getLogger(SomeClass.class);

	private void doSomething(){
		if(LOGGER.isDebugEnabled()){
			LOGGER.debug("some info");
		}
		try {
			someObject.something();
		} catch(Exception e) {
			LOGGER.info("some info");
		}
	}
}