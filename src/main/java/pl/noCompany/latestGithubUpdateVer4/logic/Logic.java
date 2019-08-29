package pl.noCompany.latestGithubUpdateVer4.logic;

import pl.noCompany.latestGithubUpdateVer4.model.Repository;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Logic {
    static int responseCode = 0;

    static List<Repository> sortAndJoin(List<Repository> firstList, List<Repository> secondList) {

        Collections.sort(secondList);
        Collections.reverse(secondList);

        List<Repository> joinedList = new ArrayList<>();
        joinedList.addAll(firstList);
        joinedList.addAll(secondList);
        return joinedList;
    }

    static LocalDateTime dateFormatter(String line) {
        line = line.replace('T',' ');
        line = line.substring(0,line.length()-1);

        final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

        return  LocalDateTime.parse(line, formatter).plusHours(2);
    }

    public static void connection(String name, List<Repository> list) {
        try {
            URL urlAdress = new URL(String.format("https://api.github.com/users/%s/repos", name));


            HttpURLConnection huc = (HttpURLConnection) urlAdress.openConnection();
            huc.setRequestMethod("GET");
            huc.connect();
            responseCode = huc.getResponseCode();

            if(responseCode == 404){
                list.get(0).setName(String.format("Uzytkownik %s nie istnieje.", name));
            }
            else if((responseCode >= 400) && (responseCode < 600)){
                list.get(0).setName(String.format("ERROR %s", responseCode));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
