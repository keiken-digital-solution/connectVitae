package io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.services;

import io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.utils.DateParser;
import io.connectvitae.connectvitaelibrary.models.*;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrapeService {
    private final DateParser dateParser;

    public User scrapeUser(String HTML) {
        Document doc = Jsoup.parse(HTML, "UTF-8");

        Element fullNameElement = doc.selectFirst(".pv-top-card h1");
        Elements aboutProfileCard = doc.select(
                "main [data-view-name=\"profile-card\"]:nth-of-type(2) [aria-hidden]"
        );

        String firstName = null;
        String lastName = null;
        if (fullNameElement != null) {
            String fullName = fullNameElement.text();
            firstName = fullName.split("\\s", 2)[0];
            lastName = fullName.split("\\s", 2)[1];
        }

        String about = null;
        if (aboutProfileCard.size() == 2 && aboutProfileCard.first().text().equals("Infos")) {
            about = aboutProfileCard.last().text();
        }

        return User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .bio(about)
                .build();
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
