import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

public class PasswordValidatorApp {

    static final Pattern LENGTH = Pattern.compile(".{8,}");
    static final Pattern SPECIAL = Pattern.compile(".*[^a-zA-Z0-9].*");
    static final Pattern UPPER = Pattern.compile(".*[A-Z].*[A-Z].*");
    static final Pattern LOWER = Pattern.compile(".*[a-z].*[a-z].*[a-z].*");
    static final Pattern DIGIT = Pattern.compile(".*\\d.*");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        System.out.println("\n=== Password Validator ===");
        System.out.println("Type 'exit' to finish.\n");

        while (true) {
            System.out.print("Enter password to validate: ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) break;

            executor.execute(() -> validate(input));
        }

        executor.shutdown();
        scanner.close();
    }

    static void validate(String pass) {
        boolean valid = true;
        valid &= LENGTH.matcher(pass).matches();
        valid &= SPECIAL.matcher(pass).matches();
        valid &= UPPER.matcher(pass).matches();
        valid &= LOWER.matcher(pass).matches();
        valid &= DIGIT.matcher(pass).matches();

        String result = valid ? "✔ VALID" : "✖ INVALID";
        System.out.printf(" [%s] \"%s\"\n", result, pass);
    }
}
