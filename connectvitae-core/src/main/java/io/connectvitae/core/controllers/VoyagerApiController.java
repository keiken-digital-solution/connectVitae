package io.connectvitae.core.controllers;


import io.connectvitae.core.config.LinkedInProperties;
import io.connectvitae.voyagerApiProvider.models.views.ProfileView;
import io.connectvitae.voyagerApiProvider.services.VoyagerApiFetcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/linkedIn")
//TODO: Handle UnsupportedOperationException
//TODO: Implement separate information getters
public class VoyagerApiController {
  private final LinkedInProperties linkedInProperties;
  private final VoyagerApiFetcherService voyagerApiFetcherService;

  private boolean isAuthenticated = false;


  @GetMapping("/{profileId}")
  public ProfileView getProfile(@PathVariable String profileId) {
    authenticate();
    return voyagerApiFetcherService.fetchGeneralProfile(profileId);
  }

  private void authenticate() {
    if (!isAuthenticated) {
      var accounts = linkedInProperties.getAccounts();
      voyagerApiFetcherService.authenticate(accounts.get(0).username(), accounts.get(0).password());
      isAuthenticated = true;
    }
  }
}
