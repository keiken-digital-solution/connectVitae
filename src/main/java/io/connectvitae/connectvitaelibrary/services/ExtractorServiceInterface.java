package io.connectvitae.connectvitaelibrary.services;

public interface ExtractorServiceInterface {
  default Object getProfile(String profileId) {
    return null;
  }

  default Object getUser(String profileId) {
    return null;
  }

  default Object getExperiences(String profileId) {
    return null;
  }

  default Object getEducations(String profileId) {
    return null;
  }

  default Object getSkills(String profileId) {
    return null;
  }

  default Object getCertifications(String profileId) {
    return null;
  }

  default Object getLanguages(String profileId) {
    return null;
  }
}
