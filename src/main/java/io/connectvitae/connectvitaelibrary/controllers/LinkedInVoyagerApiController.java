package io.connectvitae.connectvitaelibrary.controllers;

import io.connectvitae.connectvitaelibrary.linkedIn.config.LinkedInProperties;

import io.connectvitae.connectvitaelibrary.linkedIn.voyagerApiProvider.services.VoyagerApiService;
import io.connectvitae.connectvitaelibrary.models.Profile;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@RestController
@RequestMapping("/linkedIn")

public class LinkedInVoyagerApiController {
    private final LinkedInProperties linkedInProperties;
    private final VoyagerApiService voyagerApiService;

  private boolean isAuthenticated = false;


    @GetMapping("/{profileId}")
    public CompletableFuture<Profile> getProfile(@PathVariable String profileId) {
        authenticate();
        return voyagerApiService.getProfileView(profileId);
    }

    private void authenticate() {
        if (!isAuthenticated) {
            var accounts = linkedInProperties.getAccounts();
            voyagerApiService.authenticate(accounts.get(0).username(), accounts.get(0).password());
            isAuthenticated = true;
        }
    }
  }
