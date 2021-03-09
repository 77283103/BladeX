public class removeVariables {
    public static void main(String[] args) throws Exception {
        //Create Word document.
        Document document = new Document();

        //Load the file from disk.
        document.loadFromFile("data/Template_Docx_6.docx");

        //Remove the variable by name.
        document.getVariables().remove("A1");
        document.isUpdateFields (true);

        String result = "output/result-removeVariables.docx";
        //Save to file.
        document.saveToFile(result, FileFormat.Docx_2013);
    }
}
