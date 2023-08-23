package io.connectvitae.connectvitaelibrary.linkedIn.services;

import io.connectvitae.connectvitaelibrary.models.*;

import java.util.List;

public interface DataExtractorServiceInterface {
    public Profile getProfile(String profileId);

    public User getUser(String profileId);

    public List<Experience> getExperiences(String profileId);

    public List<Education> getEducations(String profileId);

    public List<Skill> getSkills(String profileId);

    public List<Certification> getCertifications(String profileId);
}
