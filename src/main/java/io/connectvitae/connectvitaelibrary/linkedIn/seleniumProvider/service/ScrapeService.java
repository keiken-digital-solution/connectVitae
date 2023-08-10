package io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.service;

import jakarta.annotation.PostConstruct;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ScrapeService {
    @PostConstruct
    public void postConstruct() throws IOException {
        String jsonFilePath = "positionsExample.txt";
        ClassPathResource resource = new ClassPathResource(jsonFilePath);
        String positionsHTML =
                StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        Document doc = Jsoup.parse(positionsHTML);
        Elements positions = doc.select("li.pvs-list__paged-list-item");
        for (Element position : positions) {
            scrapePosition(position);
            System.out.println("----------------------------------------------------");
        }
        System.out.println("-----------------------Done---------------------");
    }

    public void scrapePosition(Element position){

        String experienceId = position.attr("id");

        Element titleElement = position.selectFirst("#" + experienceId + " .t-bold");
        String title = titleElement != null ? titleElement.child(0).text() : "";

        Element companyNameElement = position.selectFirst("#" + experienceId + " .t-14.t-normal");
        String companyName = companyNameElement != null ? companyNameElement.child(0).text() : "";

        Elements metadataElements = position.select("#" + experienceId + " .t-14.t-normal.t-black--light");
        String duration = metadataElements.size() > 0 ? metadataElements.get(0).child(0).text() : "";
        String locationName = metadataElements.size() > 1 ? metadataElements.get(1).child(0).text() : "";

        Element descriptionElement = position.selectFirst("#" + experienceId + " .t-14.t-normal.t-black");
        String description = descriptionElement != null ? descriptionElement.child(0).text() : "";

        System.out.print(
                "- Title: " + title + "\n" +
                "- Company Name: " + companyName + "\n" +
                "- Duration: " + duration + "\n" +
                "- Location Name: " + locationName + "\n" +
                "- Description: " + description + "\n");
    };
}
