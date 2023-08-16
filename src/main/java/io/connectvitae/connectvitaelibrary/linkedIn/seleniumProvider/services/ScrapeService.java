package io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.services;

import io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.utils.DateParser;
import io.connectvitae.connectvitaelibrary.models.Certification;
import io.connectvitae.connectvitaelibrary.models.Experience;
import io.connectvitae.connectvitaelibrary.models.Skill;
import io.connectvitae.connectvitaelibrary.models.Education;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrapeService {
    private final DateParser dateParser;

    @PostConstruct
    public void postConstruct() throws IOException, URISyntaxException {
        String filePath = "linkedin/selenium/educationsExample.html";
        ClassPathResource classPathResource = new ClassPathResource(filePath);
        String resourceCode = Files.readString(
                Path.of(classPathResource
                        .getURL()
                        .toURI()));

        getElements(resourceCode).stream()
                .forEach(element -> {
                    System.out.println(scrapeEducation(element));
                    System.out.println("----------------------------------------------------");
                });
    }

    public Experience scrapeExperience(Element element) {

        String datesAsAText = extractText(element, 0, "t-14", "t-normal", "t-black--light");
        Date[] dates = dateParser.extractDates(datesAsAText);

        return Experience.builder()
                .company(extractText(element, "t-14", "t-normal"))
                .roleName(extractText(element, "t-bold"))
                .mission(extractText(element, "t-14", "t-normal", "t-black"))
                .location(extractText(element, 1, "t-14", "t-normal", "t-black--light"))
                .startDate(dates[0])
                .endDate(dates[1])
                .build();
    }

    public Education scrapeEducation(Element element) {
        String dateAsAText = extractText(element, 0, "t-14", "t-normal", "t-black--light");
        Date[] dates = dateParser.extractDates(dateAsAText);

        return Education.builder()
                .school(extractText(element, "t-bold"))
                .degree(extractText(element, "t-14", "t-normal"))
                .startDate(dates[0])
                .endDate(dates[1])
                .grade(extractGrade(extractText(element, 0, 0, "t-14", "t-normal", "t-black")))
                .build();
    }

    public Certification scrapeCertification(Element element) {

        String certifiedDateAsText = extractText(element, 0, "t-14", "t-normal", "t-black--light");
        Date certifiedDate = certifiedDateAsText != "" ?
                dateParser.parseDate(certifiedDateAsText.split(":")[1].trim())
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

    public List<Experience> scrapeExperiencesGroup(Element experiencesGroupElement) {
        List<Experience> experiences = new ArrayList<>();
        String companyName = extractText(experiencesGroupElement, "t-bold");
        Elements elements = Jsoup.parse(experiencesGroupElement
                        .selectFirst("li.pvs-list__paged-list-item")
                        .html())
                .select("li.pvs-list__paged-list-item");

        elements.stream()
                .forEach(experienceElement -> {
                    String dateAsAText = extractText(experienceElement, 0, "t-14", "t-normal", "t-black--light");
                    Date[] dates = dateParser.extractDates(dateAsAText);
                    Experience experience = Experience.builder()
                            .company(companyName)
                            .roleName(extractText(experienceElement, "t-bold"))
                            .startDate(dates[0])
                            .endDate(dates[1])
                            .location(extractText(experienceElement, 1, "t-14", "t-normal", "t-black--light"))
                            .mission(extractText(experienceElement, "t-14", "t-normal", "t-black"))
                            .build();
                    experiences.add(experience);
                });
        return experiences;
    }

    // --------------------------------------- Helpers --------------------------------------- \\

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

    private String extractText(Element element, int index1, int index2, String... selectors) {
        StringBuilder concatenatedSelector = new StringBuilder();
        for (String selector : selectors) {
            concatenatedSelector.append(".").append(selector);
        }
        Elements selectedElements = element.select(concatenatedSelector.toString());
        if (index1 >= selectedElements.size()) {
            return "";
        }
        return selectedElements.get(index1).child(0).child(index2).text();
    }

    public static String extractGrade(String text) {
        String[] parts = text.split(":");

        if (parts.length != 2) {
            return null;
        }

        String label = parts[0].trim();
        String value = parts[1].trim();

        if (label.equalsIgnoreCase("Niveau") || label.equalsIgnoreCase("Grade")) {
            return value;
        } else {
            return null;
        }
    }
}
