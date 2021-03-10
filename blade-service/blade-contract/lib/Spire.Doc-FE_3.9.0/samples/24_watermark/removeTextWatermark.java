import com.spire.doc.*;

public class removeTextWatermark {
    public static void main(String[] args) {
        //Create Word document
        Document document = new Document();

        //Load the file from disk
        document.loadFromFile("data/removeTextWatermark.docx");

        //Set the watermark as null to remove the text and image watermark
        document.setWatermark(null);

        String output = "output/removeTextWatermark.docx";

        //Save to file
        document.saveToFile(output, FileFormat.Docx_2013);
    }
}
