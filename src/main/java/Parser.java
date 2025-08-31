public class Parser {

    public static String[] split(String input) throws KingsleyException {
        if (input == null) {
            throw new KingsleyException("Empty Command :(");
        }

        String[] parts = input.split("\\s+", 2 );
        String commandWord = parts[0].toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";
        return new String[] { commandWord, arguments };
    }
}
