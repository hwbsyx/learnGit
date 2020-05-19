import java.io.File;

public class DeleteFile {
    public static void main(String[] args) {
        File file = new File("D:\\testfiles");
        deleteFile(file);
    }
    public static void deleteFile(File file){
        File[] files = file.listFiles();
        for (File fil:files){
            if (fil.isFile()){
                fil.delete();
            }else{
                if (fil.isDirectory()){
                    deleteFile(fil);
                }
            }
        }
        file.delete();
    }
}
