public class File implements FileSystem{
    String filename;

    public File(String filename){
        this.filename = filename;
    }

    @Override
    public void ls() {
        System.out.println("Filename " + filename);
    }
}
