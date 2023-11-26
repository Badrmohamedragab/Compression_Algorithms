package File;

import java.io.*;

public class FileController {
    public String read(String fileName) throws Exception {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        }
        catch (IOException e)
        {
            System.err.println("Error reading file: " + e.getMessage());
        }
        sb= new StringBuilder(sb.toString().replaceAll(" ", ""));
        return sb.toString();

    }
    public void write(String data)
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))){
            bw.write(data);
        }catch (IOException e)
        {
            System.err.println("Error Writing file: " + e.getMessage());
        }
    }

}
