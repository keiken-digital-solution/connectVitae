package io.connectvitae.connectvitaelibrary.controllers;

import io.connectvitae.connectvitaelibrary.linkedIn.config.LinkedInProperties;
import io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.services.DataExtractorService;
import io.connectvitae.connectvitaelibrary.linkedIn.seleniumProvider.services.SeleniumService;
import io.connectvitae.connectvitaelibrary.models.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/selenium")
public class LinkedInScraperController {
    private final DataExtractorService dataExtractorService;
    private final LinkedInProperties linkedInProperties;
    private final SeleniumService seleniumService;
    private boolean isAuthenticated = false;

    @GetMapping("/profile/{profileId}")
    public Profile getProfile(@PathVariable String profileId) {
        authenticate();
        return dataExtractorService.getProfile(profileId);
    }

    private void authenticate() {
        if (!isAuthenticated) {
            var accounts = linkedInProperties.getAccounts();
            seleniumService.authenticate(accounts.get(0).username(), accounts.get(0).password());
            isAuthenticated = true;
        }
    }
}
