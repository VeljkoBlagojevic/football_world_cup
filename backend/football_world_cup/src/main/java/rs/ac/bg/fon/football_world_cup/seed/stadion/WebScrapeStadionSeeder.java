package rs.ac.bg.fon.football_world_cup.seed.stadion;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import rs.ac.bg.fon.football_world_cup.domain.Stadion;
import rs.ac.bg.fon.football_world_cup.repository.StadionRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class WebScrapeStadionSeeder extends AbstractStadionSeeder {

    public WebScrapeStadionSeeder(StadionRepository stadionRepository) {
        super(stadionRepository);
    }

    @Override
    protected List<Stadion> getStadioni() {
        try {
            // Fetch the HTML content from the website
            Document document = Jsoup.connect("https://www.marca.com/en/world-cup/stadiums.html").get();

            // Select all stadium articles
            Elements stadiumArticles = document.select("div.especial-premundial").first().children();

            return stadiumArticles.stream()
                    .skip(2)
                    .map(stadiumElement -> {

                        String name = stadiumElement.select("h3").text();

                        String viewersString = stadiumElement.select("span.bloque-viewers").text();
                        viewersString = viewersString.replaceAll("[^0-9]", ""); // remove non-numeric characters
                        int capacity = Integer.parseInt(viewersString); // parse the string into an integer

                        String imageUrl = stadiumElement.select("div.foto-estadio img").attr("src");

                        return Stadion.builder()
                                .naziv(name)
                                .kapacitet(capacity)
                                .slika(imageUrl)
                                .build();
                    }).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
