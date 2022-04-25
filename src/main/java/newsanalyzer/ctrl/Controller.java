package newsanalyzer.ctrl;

import newsapi.NewsApi;
import newsapi.beans.NewsApiException;
import newsapi.beans.Article;

import java.util.List;

public class Controller {
	public static final String APIKEY = "91575b8a494a4a2a942befe9c9095a55";

	public void process(NewsApi api) throws NewsApiException {
		System.out.println("Start process");
		//load the news based on the parameters
		System.out.println(toString(api));
		//methods for analysis
		Analyzer analyser = new Analyzer(api);
		System.out.println("Most featured publisher: "+analyser.getTopPublisher());
		System.out.println("Number of Articles: "+analyser.getArticleCnt());
		System.out.println("Shortest Author is: "+analyser.getShortestAuthorName());

		System.out.println("End process");
	}
	

	public Object getData() {
		return null;
	}

	public String toString(NewsApi api)throws NewsApiException{
		StringBuilder stringBuilder = new StringBuilder();
		if(api.getNews() != null){
			List<Article> articles = api.getNews().getArticles();
			for(Article a: articles){
				stringBuilder.append("Title: ").append(a.getTitle()).append(System.lineSeparator())
						.append("Author: ").append(a.getAuthor()).append(System.lineSeparator())
						.append("Description: ").append(a.getDescription()).append(System.lineSeparator())
						.append("Published at: ").append(a.getPublishedAt()).append(System.lineSeparator())
						.append("Content: ").append(a.getContent()).append(System.lineSeparator())
						.append("Publisher: ").append(a.getSource()).append(System.lineSeparator());
			}
			return new String(stringBuilder);
		} else {
			throw new NewsApiException("Error in Controller");
		}
	}

}
