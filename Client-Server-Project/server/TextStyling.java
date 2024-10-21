package Project.server;
//nm874 11/13/23
public class TextStyling {


    public String processAllStyles(String text) {
            // Process underline
        text = text.replaceAll("__(.*)__", "<u>$1</u>");
        // Process bold
        text = text.replaceAll("\\*\\*(.*)\\*\\*", "<b>$1</b>");

        // Process italics
        text = text.replaceAll("_(.*)_", "<i>$1</i>");

        // Process colored text
        text = text.replaceAll("\\{color:(#\\w{6})\\}(.*?)\\{color\\}", "<span style='color:$1;'>$2</span>");

        return text;
    }
}