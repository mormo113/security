import java.io.File;

public class SomeClass {

	private void catchVide(){
		try {
			someObject.something();
		} catch(Exception e) {
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