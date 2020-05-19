import java.io.*;

/*装饰设计模式
        需求：拓展Buffered功能。readLine加上行号，分号，双引号。*/
public class MyBuffered {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\abc.txt");
        FileReader fileReader = new FileReader(file);

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        BufferedLineNum bufferedLineNum = new BufferedLineNum(bufferedReader);

        BufferedSemicolon bufferedSemicolon = new BufferedSemicolon(bufferedLineNum);

        BufferedQuta bufferedQuta =new BufferedQuta(bufferedSemicolon);

        String line = null;
        while ((line = bufferedQuta.readLine())!=null){
            System.out.println(line);
        }



    }

}

class BufferedLineNum extends BufferedReader{
    int count = 1;
    BufferedReader bufferedReader;
    public BufferedLineNum(BufferedReader bufferedReader){
        super(bufferedReader);  //没有意义只是为了让程序不报错 因为BufferedReader没有无参构造方法
        this.bufferedReader=bufferedReader;
    }
    public String readLine() throws IOException {
        String line = bufferedReader.readLine();
        if (line == null){
            return null;
        }
        line = count + " " + line;
        count++;
        return line;
    }
}

class BufferedSemicolon extends BufferedReader{
    BufferedReader bufferedReader;
    public BufferedSemicolon(BufferedReader bufferedReader){
        super(bufferedReader);
        this.bufferedReader=bufferedReader;
    }
    public String readLine() throws IOException {
        String line = bufferedReader.readLine();
        if (line == null){
            return null;
        }
        line = line + ";";
        return line;
    }
}

class BufferedQuta extends BufferedReader{
    BufferedReader bufferedReader;
    public BufferedQuta(BufferedReader bufferedReader){
        super(bufferedReader);
        this.bufferedReader=bufferedReader;
    }
    public String readLine() throws IOException {
        String line = bufferedReader.readLine();
        if (line == null){
            return null;
        }
        line = "\"" + line + "\"";
        return line;
    }
}