package io.connectvitae.connectvitaelibrary.controllers;

import io.connectvitae.connectvitaelibrary.linkedIn.config.LinkedInProperties;
import io.connectvitae.connectvitaelibrary.linkedIn.services.LinkedInService;
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
public class LinkedInController {
  private final LinkedInProperties linkedInProperties;
  private final LinkedInService linkedInService;

  private boolean isAuthenticated = false;

  @GetMapping("/{profileId}")
  public CompletableFuture<Profile> getProfile(@PathVariable String profileId) {
    authenticate();
    return linkedInService.getProfileView(profileId);
  }

  private void authenticate() {
    if (!isAuthenticated) {
      var accounts = linkedInProperties.getAccounts();
      linkedInService.authenticate(accounts.get(0).username(), accounts.get(0).password());
      isAuthenticated = true;
    }
  }
}
