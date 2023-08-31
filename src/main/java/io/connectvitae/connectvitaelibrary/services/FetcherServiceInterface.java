package io.connectvitae.connectvitaelibrary.services;


public interface FetcherServiceInterface {
  void authenticate(String username, String password);

  Object fetchUser(String profileId);

  Object fetchExperiences(String profileId);

  Object fetchEducations(String profileId);

  Object fetchSkills(String profileId);

  Object fetchCertifications(String profileId);

  Object fetchLanguages(String profileId);
}
