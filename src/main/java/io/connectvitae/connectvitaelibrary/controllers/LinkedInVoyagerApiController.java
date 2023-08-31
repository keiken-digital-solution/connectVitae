package io.connectvitae.connectvitaelibrary.controllers;

import io.connectvitae.connectvitaelibrary.config.LinkedInProperties;
import io.connectvitae.connectvitaelibrary.models.Profile;
import io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.services.VoyagerApiFetcherService;
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
  private final VoyagerApiFetcherService voyagerApiFetcherService;

  private boolean isAuthenticated = false;


  @GetMapping("/{profileId}")
  public CompletableFuture<Profile> getProfile(@PathVariable String profileId) {
    authenticate();
    return voyagerApiFetcherService.getProfileView(profileId);
  }

  private void authenticate() {
    if (!isAuthenticated) {
      var accounts = linkedInProperties.getAccounts();
      voyagerApiFetcherService.authenticate(accounts.get(0).username(), accounts.get(0).password());
      isAuthenticated = true;
    }
  }
}
