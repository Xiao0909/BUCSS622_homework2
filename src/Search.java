import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Search { private static final String DATA_FOLDER_PATH = "./Files/combined.json";
    private Map<String, String> searchList = new HashMap<>();
    private Map<String, String> searchTimes = new HashMap<>();

    public static void main(String[] args) {
        Search search = new Search();

        try {
            while (true) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Input keyword (Enter \"exit\" to exit): ");
                String keyword = scanner.nextLine().trim(); // Read input from the user

                if (keyword.equalsIgnoreCase("exit")) { // If the user enters "exit," then exit the program
                    break;
                }

                // Record search keywords in the search history
                search.searchKeyword(keyword);

                BufferedReader reader = new BufferedReader(new FileReader(DATA_FOLDER_PATH));
                String line;

                while ((line = reader.readLine()) != null) { // Read lines from the file
                    if (line.toLowerCase().contains(keyword.toLowerCase())) { // Case-insensitive keyword search
                        // Avoid cutting commas within double quotes
                        String[] items = line.trim().split(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)", -1);

                        // Print the name of the project, its category, and funding amount
                        System.out.println(items[23] + " " + items[2] + " " + items[3]);
                    }
                }

                // Print the search terms and their frequencies immediately after each search
                search.printSearchFrequency();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchKeyword(String keyword) {
        keyword = keyword.toLowerCase();
        searchList.put(String.valueOf(System.currentTimeMillis()), keyword);
        if (searchTimes.containsKey(keyword)) {
            int times = Integer.parseInt(searchTimes.get(keyword)) + 1;
            searchTimes.put(keyword, String.valueOf(times));
        } else {
            searchTimes.put(keyword, "1");
        }
    }

    private void printSearchFrequency() {
        System.out.println("Search Term Frequencies:");

        for (Map.Entry<String, String> entry : searchTimes.entrySet()) {
            String searchTerm = entry.getKey();
            String frequency = entry.getValue();
            System.out.println(searchTerm + " , " + frequency + " times");
        }
    }
}
