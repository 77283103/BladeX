public class removeEmptyLines {
    public static void main(String[] args) {
        //Create Word document.
        Document document = new Document();

        //Load the file from disk.
        document.loadFromFile("data/Template_Docx_3.docx");

        //Traverse every section on the word document and remove the null and empty paragraphs.
        for (Object sectionObj : document.getSections()) {
            Section section=(Section)sectionObj;
            for (int i = 0; i < section.getBody().getChildObjects().getCount(); i++) {
                if ((section.getBody().getChildObjects().get(i).getDocumentObjectType().equals(DocumentObjectType.Paragraph) )) {
                   String s= ((Paragraph)(section.getBody().getChildObjects().get(i))).getText().trim();
                    if (s.isEmpty()) {
                        section.getBody().getChildObjects().remove(section.getBody().getChildObjects().get(i));
                        i--;
                    }
                }
            }
        }

        String result = "output/removeEmptyLines.docx";

        //Save to file.
        document.saveToFile(result, FileFormat.Docx_2013);
    }
}
