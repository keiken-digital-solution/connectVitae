package io.connectvitae.connectvitaelibrary.providers.voyagerApiProvider.services;

import io.connectvitae.connectvitaelibrary.mappers.VoyagerApiMappingService;
import io.connectvitae.connectvitaelibrary.services.ExtractorServiceInterface;
import io.connectvitae.connectvitaelibrary.models.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class VoyagerApiExtractorService implements ExtractorServiceInterface {

    private final VoyagerApiFetcherService fetcherService;
    private final VoyagerApiMappingService mappingService;
    public Profile getProfile(String profileId) {
        return Profile.builder()
                .user(getUser(profileId))
                .educations(getEducations(profileId))
                .certifications(getCertifications(profileId))
                .skills(getSkills(profileId))
                .experiences(getExperiences(profileId))
                .build();
    }

    public User getUser(String profileId){
        return mappingService.mapUser(fetcherService.fetchUser(profileId));
    }

    public List<Experience> getExperiences(String profileId) {
        List<Experience> experiences = new ArrayList<>();
        fetcherService.fetchExperiences(profileId)
                .getElements()
                .stream()
                .forEach(experience ->
                    experiences.add(mappingService.mapExperience(experience))
                );
        return experiences;
    }

    public List<Education> getEducations(String profileId) {
        List<Education> educations = new ArrayList<>();
        fetcherService.fetchEducations(profileId)
                .getElements()
                .stream()
                .forEach(education ->
                        educations.add(mappingService.mapEducation(education))
                );
        return educations;
    }

    public List<Skill> getSkills(String profileId) {
        List<Skill> skills = new ArrayList<>();
        fetcherService.fetchSkills(profileId)
                .getElements()
                .stream()
                .forEach(skill ->
                        skills.add(mappingService.mapSkill(skill))
                );
        return skills;
    }

    public List<Certification> getCertifications(String profileId) {
        List<Certification> certifications = new ArrayList<>();
        fetcherService.fetchCertifications(profileId)
                .getElements()
                .stream()
                .forEach(certification ->
                        certifications.add(mappingService.mapCertification(certification))
                );
        return certifications;
    }
}
