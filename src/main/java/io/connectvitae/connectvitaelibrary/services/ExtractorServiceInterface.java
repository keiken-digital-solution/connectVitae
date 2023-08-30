package io.connectvitae.connectvitaelibrary.services;

import io.connectvitae.connectvitaelibrary.models.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ExtractorServiceInterface {
    public CompletableFuture<Profile> getProfile(String profileId);

    public Object getUser(String profileId);

    public Object getExperiences(String profileId);

    public Object getEducations(String profileId);

    public Object getSkills(String profileId);

    public Object getCertifications(String profileId);
}
