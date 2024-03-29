package generator;

public class GeneratorId {
    private static int phoneId;

    public static int genPhoneId() {
        return ++phoneId;
    }
}
