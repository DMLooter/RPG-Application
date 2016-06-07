package combat;

public class Combat {
	private static boolean initialized = false;
	public static Logger logger;

	public static void Initialize() {
		Initialize(new Logger());
	}

	public static void Initialize(Logger l) {
		initialized = true;
		logger = l;
	}
}