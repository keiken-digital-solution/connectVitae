package io.connectvitae.connectvitaelibrary.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.connectvitae.connectvitaelibrary.linkedIn.config.LinkedInProperties;
import io.connectvitae.connectvitaelibrary.linkedIn.mappers.ProfileMapper;
import io.connectvitae.connectvitaelibrary.linkedIn.services.LinkedInService;
import io.connectvitae.connectvitaelibrary.models.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/linkedIn")
public class LinkedInController {
    private final LinkedInService linkedInService;
    private final ProfileMapper profileMapper;
    private final LinkedInProperties linkedInProperties;

    private boolean isAuthenticated = false;

    @GetMapping("/{profileId}")
    public ResponseEntity<Profile> getProfile(@PathVariable String profileId) throws JsonProcessingException {
        authenticate();
//        var skills = linkedInService.fetchSkills(profileId);
//        var positions = linkedInService.fetchPositions(profileId);
//        var educations = linkedInService.fetchEducations(profileId);
//        var miniProfile = linkedInService.fetchProfile(profileId);
        var p = linkedInService.getProfileView(profileId);
        return ResponseEntity.ok().body(profileMapper.apply(p));
    }

    private void authenticate() {
        if (!isAuthenticated) {
            var accounts = linkedInProperties.getAccounts();
            linkedInService.authenticate(accounts.get(0).username(), accounts.get(0).password());
            isAuthenticated = true;
        }
    }
}
