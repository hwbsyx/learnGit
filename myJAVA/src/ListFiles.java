import java.io.File;


public class ListFiles {
    public static void main(String[] args) {
        File file = new File("D:\\BaiduNetdiskDownload");
        listfiles(file,"|--");
    }

    public static void listfiles(File file,String str){
        File[] files = file.listFiles();
        for (File fil:files){
            if (fil.isFile()){
                System.out.println(str + fil.getName());
            }else if (fil.isDirectory()){
                System.out.println(str + fil.getName());
                listfiles(fil,"|  " + str);
            }
        }
    }
}

