package be.technobel.emolibspring.helper;

public class CommonService {
    public static String convertStringToSlug(String string) {
        if (string.isEmpty()) return "";
        return string.toLowerCase().replaceAll("[^a-zA-Z0-9]", "-");
    }
}
