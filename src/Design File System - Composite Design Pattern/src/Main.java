import java.util.List;

public class Main {
    public static void main(String[] args){
        // Leaf nodes
        FileSystem resume = new File("resume.pdf");
        FileSystem coverLetter = new File("cover_letter.docx");
        FileSystem photo = new File("holiday.jpg");
        FileSystem song = new File("song.mp3");
        FileSystem notes = new File("notes.txt");

        // Composite nodes (directories can contain files and other directories)
        FileSystem documents = new Directory(List.of(resume, coverLetter));
        FileSystem media = new Directory(List.of(photo, song));
        FileSystem root = new Directory(List.of(documents, media, notes));

        System.out.println("---- ls on a single file ----");
        notes.ls();

        System.out.println("\n---- ls on documents directory ----");
        documents.ls();

        System.out.println("\n---- ls on root directory (recursive) ----");
        root.ls();
    }
}
