package Project.server;
//nm874 11/13/23
public class TextStyling {
    public static void main(String[] args) {
        ChatHandler chatHandler = new ChatHandler();

        // Example texts
        String boldText = "**bold**";
        String italicText = "_italics_";
        String coloredText = "{color:#ff0000}red text{color}";
        String underlineText = "__underline__";
        String mixedText = "**bold** _italics_ {color:#ff0000}colored{color} __underline__";

        // Process and print
        System.out.println("Processed Bold: " + chatHandler.processAllStyles(boldText));
        System.out.println("Processed Italics: " + chatHandler.processAllStyles(italicText));
        System.out.println("Processed Colored: " + chatHandler.processAllStyles(coloredText));
        System.out.println("Processed Underline: " + chatHandler.processAllStyles(underlineText));
        System.out.println("Processed Mixed Styles: " + chatHandler.processAllStyles(mixedText));
    }
}
