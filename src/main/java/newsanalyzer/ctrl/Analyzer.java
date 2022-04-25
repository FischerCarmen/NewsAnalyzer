package newsanalyzer.ctrl;

import newsapi.NewsApi;
import newsapi.beans.NewsApiException;
import newsapi.beans.Article;
import newsapi.beans.NewsReponse;

import java.util.*;

public class Analyzer {

    private List<Article> articlesList;
    private NewsReponse response;
    NewsApi api;

    public Analyzer(NewsApi api) throws NewsApiException {
        response = api.getNews();
        this.api = api;
        if (response != null) {
            articlesList = response.getArticles();
        } else {
            throw new NewsApiException("Error in Controller");
        }
    }

    public long getArticleCnt(){
        return articlesList.stream().count();
    }

    public String getTopPublisher(){
        List<String> publisher = new ArrayList<String>();
        articlesList.stream().forEach(article -> publisher.add(article.getSource().getName()));

        Set<String> unique = new HashSet<String>(publisher);
        String topPublisherString = null;
        int topPublisherInt = 0;

        for (String key : unique) {

            if(topPublisherInt < Collections.frequency(publisher, key)){
                topPublisherString = key;
                topPublisherInt = Collections.frequency(publisher, key);
            }
        }
        return topPublisherString;
    }


    public String getShortestAuthorName() throws NewsApiException {
        Article shortestAuthor = articlesList.stream()
                .filter(au -> au.getAuthor() != null)
                .sorted(Comparator.comparingInt(value -> value.getAuthor().length()))
                .findFirst()
                .orElse(new Article());
        return shortestAuthor.getAuthor();
    }

    //leider keine Ahnung was unter "Sortieren Sie den Titel nach dem längsten Titel nach dem Alphabet." zu verstehen ist

}
