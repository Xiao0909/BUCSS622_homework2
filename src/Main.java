import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        File path = new File ("./Files");
        System.out.println(path);
        PrintWriter printWriter = new PrintWriter("./Files/combined.json");
        String[] fileNames = path.list();
//        System.out.println(fileNames.length);
        System.out.println("Process Started!");


        for(String fileName: fileNames){
            System.out.println(fileName);
            File file = new File(path, fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));
            printWriter.println(fileName);

            String rl = br.readLine();
            while(rl != null){
                printWriter.println(rl);
                rl = br.readLine();
            }
            printWriter.flush();
        }
        System.out.println("Process Completed!");
//
    }
}