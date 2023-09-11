package io.connectvitae.connectvitaelibrary.services;


public interface FetcherServiceInterface {
  void authenticate(String username, String password);
  default Object fetchGeneralProfile(String profileId) {
    return null;
  }
  default Object fetchUser(String profileId) {
    return null;
  }
  default Object fetchExperiences(String profileId) {
    return null;
  }
  default Object fetchEducations(String profileId) {
    return null;
  };
  default Object fetchSkills(String profileId) {
    return null;
  }
  default Object fetchCertifications(String profileId) {
    return null;
  }
  default Object fetchLanguages(String profileId) {
    return null;
  }
}
