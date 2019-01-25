package ex3;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WriteURLInFile {
    private File file;

    public WriteURLInFile(String address, File file) throws IOException {
        this.file = file;
        saveInFile(getStringFromURL(address));
    }

    private String getStringFromURL(String address) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (connection.getContentType().contains("text/html")) {
                BufferedReader buf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String temp = "";
                while ((temp = buf.readLine()) != null) {
                    sb.append(temp);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private void saveInFile(String address) throws IOException {
        if (address == null) {
            return;
        }
        String regex = new String("href=\"http(s)?:\\/{2}\\S*\"");
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(address);
        String temp = "";
        try (PrintWriter pWriter = new PrintWriter(new FileWriter(file))) {
            while (matcher.find()) {
                temp = matcher.group();
                temp = temp.substring(6,temp.length()-1);
                pWriter.write(temp);
                pWriter.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
