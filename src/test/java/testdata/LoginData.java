package testdata;

public class LoginData {
    private static final String CORRECT_EMAIL = "svetlana.celuykina@mail.ru";
    private static final String INCORRECT_EMAIL = "qwerty";
    private static final String EMPTY_EMAIL = "";

    public static String getCorrectEmail() {
        return CORRECT_EMAIL;
    }

    public static String getIncorrectEmail() {
        return INCORRECT_EMAIL;
    }

    public static String getEmptyEmail() {
        return EMPTY_EMAIL;
    }
}
