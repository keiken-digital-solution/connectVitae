package io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.service;

import io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.utils.DateParser;
import io.connectvitae.connectvitaelibrary.models.Certification;
import io.connectvitae.connectvitaelibrary.models.Experience;
import io.connectvitae.connectvitaelibrary.models.Skill;
import io.connectvitae.connectvitaelibrary.models.Training;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ScrapeService {
    private final DateParser dateParser;
    @PostConstruct
    public void postConstruct() throws IOException, URISyntaxException {
        String filePath = "linkedin/selenium/certificationsExample.html";
        ClassPathResource classPathResource = new ClassPathResource(filePath);
        String resourceCode = Files.readString(
                Path.of(classPathResource
                        .getURL()
                        .toURI()));

        getElements(resourceCode).stream()
                .forEach(element -> {
                    System.out.println(scrapeCertification(element));
                    System.out.println("----------------------------------------------------");
                });
    }


    public Experience scrapePosition(Element element) {

        String datesAsAText = extractText(element, 0,"t-14", "t-normal", "t-black--light");
        Date[] dates = dateParser.extractDates(datesAsAText);

        return Experience.builder()
                .company(extractText(element, "t-14","t-normal"))
                .roleName(extractText(element, "t-bold"))
                .mission(extractText(element, "t-14","t-normal","t-black"))
                .location(extractText(element,1,  "t-14", "t-normal", "t-black--light"))
                .startDate(dates[0])
                .endDate(dates[1])
                .build();
    }

    public Training scrapeEducation(Element element) {
        String dateAsAText = extractText(element,0, "t-14", "t-normal", "t-black--light");
        Date[] dates = dateParser.extractDates(dateAsAText);

        return Training.builder()
                .school(extractText(element, "t-bold"))
                .degree(extractText(element, "t-14", "t-normal"))
                .startDate(dates[0])
                .endDate(dates[1])
                .build();
    }

    public Certification scrapeCertification(Element element) {

        String certifiedDateAsText = extractText(element, 0,"t-14", "t-normal" , "t-black--light");
        Date certifiedDate = certifiedDateAsText != "" ?
                dateParser.parseDate(certifiedDateAsText.split(" : ")[1])
                : null;

        return Certification.builder()
                .certificationName(extractText(element, "t-bold"))
                .certifiedDate(certifiedDate)
                .certificationProvider(extractText(element, "t-14", "t-normal"))
                .build();
    }

    public Skill scrapeSkill(Element element) {

        return Skill.builder()
                .skillName(extractText(element, "t-bold"))
                .build();
    }

    public Elements getElements(String innerHTML) {
        Document doc = Jsoup.parse(innerHTML, "UTF-8");
        return doc.select("li.pvs-list__paged-list-item");
    }

    private String extractText(Element element, String... selectors) {
        StringBuilder concatenatedSelector = new StringBuilder();
        for (String selector : selectors) {
            concatenatedSelector.append(".").append(selector);
        }

        Element selectedElement = element.selectFirst(concatenatedSelector.toString());
        return selectedElement != null
                ? selectedElement.child(0).text()
                : "";
    }

    private String extractText(Element element, int index, String... selectors) {
        StringBuilder concatenatedSelector = new StringBuilder();
        for (String selector : selectors) {
            concatenatedSelector.append(".").append(selector);
        }
        Elements selectedElements = element.select(concatenatedSelector.toString());
        if (index >= selectedElements.size()) {
            return "";
        }
        return selectedElements.get(index).child(0).text();
    }
}
