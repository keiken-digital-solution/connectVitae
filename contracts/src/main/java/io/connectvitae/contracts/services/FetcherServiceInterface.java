package io.connectvitae.contracts.services;
//TODO: return an UnsupportedOperationException instead of null
//TODO: Add documentation
public interface FetcherServiceInterface {
  default void authenticate(String username, String password) {
    throw new UnsupportedOperationException(this.getClass() + ".authenticate is not supported");
  }
  default Object fetchGeneralProfile(String profileId) {
    throw new UnsupportedOperationException(this.getClass() + ".fetchGeneralProfile is not supported");
  }
  default Object fetchUser(String profileId) {
    throw new UnsupportedOperationException(this.getClass() + ".fetchUser is not supported");
  }
  default Object fetchExperiences(String profileId) {
    throw new UnsupportedOperationException(this.getClass() + ".fetchExperiences is not supported");
  }
  default Object fetchEducations(String profileId) {
    throw new UnsupportedOperationException(this.getClass() + ".fetchEducations is not supported");
  }
  default Object fetchSkills(String profileId) {
    throw new UnsupportedOperationException(this.getClass() + ".fetchSkills is not supported");
  }
  default Object fetchCertifications(String profileId) {
    throw new UnsupportedOperationException(this.getClass() + ".fetchCertifications is not supported");
  }
  default Object fetchLanguages(String profileId) {
    throw new UnsupportedOperationException(this.getClass() + ".fetchLanguages is not supported");
  }
  default Object fetchSchool(String schoolId) {
    throw new UnsupportedOperationException(this.getClass() + ".fetchSchool is not supported");
  }
  default Object fetchCompany(String companyId) {
    throw new UnsupportedOperationException(this.getClass() + ".fetchCompany is not supported");
  }
}
