import java.io.File;
//删除某个目录下文件名中带特定字符的文件
public class GetFile {
    public static void main(String[] args) {
        File file = new File("D:\\aaaaa");
        GetFile(file);
    }
    public static void GetFile(File file){
        File[] files = file.listFiles();
        for(File lf:files){
            if (lf.isFile()) {
                if (lf.getName().endsWith("~")) {
                    lf.delete();
                }
            }else {
                    if (lf.isDirectory()){
                        GetFile(lf);
                    }
            }
        }
    }
}

