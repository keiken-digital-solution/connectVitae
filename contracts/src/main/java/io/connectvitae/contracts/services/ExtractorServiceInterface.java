package io.connectvitae.contracts.services;
//TODO: return an UnsupportedOperationException instead of null
//TODO: Add documentation
public interface ExtractorServiceInterface {
  default Object getProfile(String profileId) {
    throw new UnsupportedOperationException(this.getClass() + ".getProfile is not supported");
  }

  default Object getUser(String profileId) {
    throw new UnsupportedOperationException(this.getClass() + ".getUser is not supported");
  }

  default Object getExperiences(String profileId) {
    throw new UnsupportedOperationException(this.getClass() + ".getExperiences is not supported");
  }

  default Object getEducations(String profileId) {
    throw new UnsupportedOperationException(this.getClass() + ".getEducations is not supported");
  }

  default Object getSkills(String profileId) {
    throw new UnsupportedOperationException(this.getClass() + ".getSkills is not supported");
  }

  default Object getCertifications(String profileId) {
    throw new UnsupportedOperationException(this.getClass() + ".getCertifications is not supported");
  }

  default Object getLanguages(String profileId) {
    throw new UnsupportedOperationException(this.getClass() + ".getLanguages is not supported");
  }
  default Object getSchool(String schoolId) {
    throw new UnsupportedOperationException(this.getClass() + ".getSchool is not supported");
  }
  default Object getCompany(String companyId) {
    throw new UnsupportedOperationException(this.getClass() + ".getCompany is not supported");
  }
}
