package combat;

import java.io.OutputStream;
import java.io.PrintStream;

public class Logger {
	private PrintStream output;

	public Logger() {
		this(null);
	}

	public Logger(PrintStream o) {
		output = o;
	}

	public void log(int type, String message) {
		switch (type) {
		case 0:
			output.println(message);
			break;
		case 1:
			output.println("[MESSAGE]: " + message);
			break;
		case 2:
			output.println("[WARNING]: " + message);
			break;
		case 3:
			output.println("[ERROR]: " + message);
			break;
		case 4:
			output.println("[SYSTEM]: " + message);
			break;
		}
	}

	public OutputStream getOutput() {
		return output;
	}

	public void setOutput(PrintStream p) {
		output = p;
	}
}