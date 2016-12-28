import org.apache.log4j.Logger;

public class SomeClass {

	private static Logger log = Logger.getLogger(SomeClass.class);

	private void doSomething(){
		if(log.isDebugEnabled()){
			log.debug("some info");
		}
		try {
			someObject.something();
		} catch(Exception e) {
			log.info("some info");
		}
	}
}