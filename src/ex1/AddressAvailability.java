package ex1;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class AddressAvailability {
    private Map<String,String> links = new HashMap<>();

    public AddressAvailability() {
    }

    public void printResultFromFile(File file) throws IOException {
        loadAddressFromFile(file);
        Set<String> keys = links.keySet();
        for (String key : keys) {
            System.out.println(key +" "+ links.get(key));
        }
    }

    private void loadAddressFromFile(File file) throws IOException{
        List<String> lines = getListOfLinesFromFile(file);
        for (String address : lines) {
            try {
                URL url = new URL(address);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int responseCode = connection.getResponseCode();
                if (responseCode >= 200 && responseCode < 300)
                    links.put(address, "YES");
                else
                    links.put(address, "NO - "+ connection.getResponseMessage());
            }
            catch (IOException e){
                links.put(address, "NO - not register.");
            }
        }
    }

    private List<String> getListOfLinesFromFile(File file) throws IOException{
        List<String> lines = new ArrayList<>();
        try (BufferedReader buf = new BufferedReader(new FileReader(file))){
            String line = "";
            while ((line = buf.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
