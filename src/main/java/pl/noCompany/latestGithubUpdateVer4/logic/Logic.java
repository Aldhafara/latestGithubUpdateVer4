package pl.noCompany.latestGithubUpdateVer4.logic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;
import pl.noCompany.latestGithubUpdateVer4.model.DateAndTimeFormatter;
import pl.noCompany.latestGithubUpdateVer4.model.Repository;
import pl.noCompany.latestGithubUpdateVer4.model.RepositoryFX;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Logic {

    static int responseCode = 0;
    private URLConnection urlConnection;
    private List<Repository> finalRepositoryList;


    private URLConnection getUrlConnection() {
        return urlConnection;
    }

    private void setUrlConnection(URLConnection urlConnection) {
        this.urlConnection = urlConnection;
    }

    public List<Repository> getNewList(String login) {

        List<Repository> list = new ArrayList<>();
        List<Repository> list2;

        Repository zeroElement = new Repository();
        zeroElement.setName(null);
        zeroElement.setTime(null);
        list.add(zeroElement);

        connection(login, list);
        list2 = downloadContentFromSite(getUrlConnection());
        if (list2.isEmpty() && responseCode==200)
        {
            zeroElement.setName(String.format("Uzytkownik %s ma puste repozytorium.", login));
        }
        list = sortAndJoin(list,list2);

        finalRepositoryList = list;

        return list;
    }


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

        return  LocalDateTime.parse(line, DateAndTimeFormatter.getFormatter()).plusHours(2);
    }

    void connection(String name, List<Repository> list) {
        try {
            URL urlAdress = new URL(String.format("https://api.github.com/users/%s/repos", name));

            this.setUrlConnection(urlAdress.openConnection());

            HttpURLConnection huc = (HttpURLConnection) getUrlConnection();
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
            list.get(0).setName("ERROR Sprawdź połączenie z siecią");
            e.printStackTrace();
        }
    }

    private static List<Repository> downloadContentFromSite(URLConnection url) {

        List<Repository> repositoryList = new ArrayList<>();

        JSONArray json;

        try {
            json = new JSONArray(getText(url));
            for(int i=0;i<json.length();i++)
            {
                Repository repository = new Repository();

                //get the JSON Object
                JSONObject obj = json.getJSONObject(i);

                repository.setName(obj.getString("name"));
                repository.setTime(dateFormatter(obj.getString("updated_at")));
                repositoryList.add(repository);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return repositoryList;
    }

    private static String getText(URLConnection connection) throws Exception {

        BufferedReader in = new BufferedReader( new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();
    }

    private static void printAllFromList(List list) {
        for (Object o : list) {
            System.out.println(o.toString());
        }
    }

    public ObservableList<RepositoryFX> createFXList(){
        ObservableList<RepositoryFX> repositoryFXList = FXCollections.observableArrayList();;
        finalRepositoryList.remove(0);

        finalRepositoryList.stream().map(r -> new RepositoryFX(r.getName(), r.getTime().format(DateAndTimeFormatter.getFormatter()))).forEach(repositoryFXList::add);
        return repositoryFXList;
    }
}
