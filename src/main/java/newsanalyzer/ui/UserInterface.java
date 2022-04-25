package newsanalyzer.ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import newsanalyzer.ctrl.Controller;
import newsapi.NewsApi;
import newsapi.beans.NewsApiException;
import newsapi.NewsApiBuilder;
import newsapi.enums.Category;
import newsapi.enums.Country;
import newsapi.enums.Endpoint;

public class UserInterface 
{

	private Controller ctrl = new Controller();

	public void getDataFromCtrl1(){
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(Controller.APIKEY)
				.setQ("")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCategory(Category.science)
				.createNewsApi();
		try {
			ctrl.process(newsApi);
		}catch (NewsApiException e) {
			System.out.println("Error: "+ e.getMessage());
		}
	}

	public void getDataFromCtrl2(){
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(Controller.APIKEY)
				.setQ("Bitcoin")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCategory(Category.business)
				.createNewsApi();
		try {
			ctrl.process(newsApi);
		}catch (NewsApiException e) {
			System.out.println("Error: "+ e.getMessage());
		}

	}

	public void getDataFromCtrl3(){
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(Controller.APIKEY)
				.setQ("ComputerScience")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCategory(Category.technology)
				.createNewsApi();
		try {
			ctrl.process(newsApi);
		}catch (NewsApiException e) {
			System.out.println("Error: "+ e.getMessage());
		}
	}
	
	public void getDataForCustomInput() {
		String input = readLine();
		int categoryInt = readDouble(1,6).intValue();
		Category category;
		switch (categoryInt){
			case 1: category = Category.business; break;
			case 2: category = Category.entertainment; break;
			case 3: category = Category.health; break;
			case 4: category = Category.science; break;
			case 5: category = Category.sports; break;
			case 6: category = Category.technology; break;
			default: category = null;
		}
		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(Controller.APIKEY)
				.setQ(input)
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(category)
				.createNewsApi();
		try {
			ctrl.process(newsApi);
		}catch (NewsApiException e) {
			System.out.println("Error: "+ e.getMessage());
		}
	}


	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Choice Animals in science", this::getDataFromCtrl1);
		menu.insert("b", "Choice Bitcoin in business", this::getDataFromCtrl2);
		menu.insert("c", "Choice Computer Science in technology", this::getDataFromCtrl3);
		menu.insert("d", "Choice Custom Input in business (1), entertainment (2), health (3), science (4), sports (5) or technology (6)",this::getDataForCustomInput);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			 choice.run();
		}
		System.out.println("Program finished");
	}


    protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
        } catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
        while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
            if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
                number = null;
            } else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
                number = null;
			}
		}
		return number;
	}
}
