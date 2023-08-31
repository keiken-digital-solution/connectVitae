package io.connectvitae.connectvitaelibrary.services;

import io.connectvitae.connectvitaelibrary.models.Profile;

import java.util.concurrent.CompletableFuture;

public interface ExtractorServiceInterface {
  CompletableFuture<Profile> getProfile(String profileId);

  Object getUser(String profileId);

  Object getExperiences(String profileId);

  Object getEducations(String profileId);

  Object getSkills(String profileId);

  Object getCertifications(String profileId);
}
