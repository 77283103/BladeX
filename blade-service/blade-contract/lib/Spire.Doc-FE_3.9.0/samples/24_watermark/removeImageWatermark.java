import com.spire.doc.*;

public class removeImageWatermark {
    public static void main(String[] args) {
        //Create Word document
        Document document = new Document();

        //Load the file from disk
        document.loadFromFile("data/removeImageWatermark.docx");

        //Set the watermark as null to remove the text and image watermark
        document.setWatermark(null);

        String output = "output/removeImageWatermark.docx";

        //Save to file
        document.saveToFile(output, FileFormat.Docx_2013);
    }
}
