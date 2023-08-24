package io.connectvitae.connectvitaelibrary.controllers;

import io.connectvitae.connectvitaelibrary.linkedIn.config.LinkedInProperties;
import io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.services.SeleniumExtractorService;
import io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.services.SeleniumFetcherService;
import io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.models.SeleniumProfile;
import io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.models.SeleniumUser;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/selenium")
public class LinkedInScraperController {
    private final SeleniumExtractorService dataSeleniumExtractorService;
    private final LinkedInProperties linkedInProperties;
    private final SeleniumFetcherService seleniumFetcherService;
    private boolean isAuthenticated = false;

    @GetMapping("/profile/{profileId}")
    public SeleniumProfile getProfile(@PathVariable String profileId) {
        authenticate();
        return dataSeleniumExtractorService.getProfile(profileId);
    }

    @GetMapping("/profile/{profileId}/user")
    public SeleniumUser getUser(@PathVariable String profileId) {
        authenticate();
        return dataSeleniumExtractorService.getUser(profileId);
    }

    private void authenticate() {
        if (!isAuthenticated) {
            var accounts = linkedInProperties.getAccounts();
            seleniumFetcherService.authenticate(accounts.get(0).username(), accounts.get(0).password());
            isAuthenticated = true;
        }
    }
}
