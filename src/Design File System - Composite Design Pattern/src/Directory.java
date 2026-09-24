import java.util.List;

public class Directory implements FileSystem {
    List<FileSystem> fileSystemList;

    public Directory(List<FileSystem> fileSystemList) {
        this.fileSystemList = fileSystemList;
    }

    @Override
    public void ls() {
        for(FileSystem fileSystemObj : fileSystemList){
            fileSystemObj.ls();
        }
    }
}
