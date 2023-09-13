package io.connectvitae.contracts.services;


import io.connectvitae.contracts.models.*;

/**
 * Mapper Service Interface is a generic interface that takes the external models as type parameters
 * and has the methods that need to be implemented on each provider external model to map it to the internal
 * ConnectVitae models.
 */
public interface MapperServiceInterface<
    ExternalGeneralProfile,
    ExternalCertification,
    ExternalEducation,
    ExternalExperience,
    ExternalLanguage,
    ExternalSkill,
    ExternalUser,
    ExternalSchool,
    ExternalCompany> {

  /**
   * Maps data from the external General Profile Object to the intern GeneralProfile Object that contains
   * all information about the profile gathered together.
   *
   * @param externalGeneralProfile The source General Profile object.
   * @return The intern model User object.
   */
  default GeneralProfile mapGeneralProfile(ExternalGeneralProfile externalGeneralProfile) {
    throw new UnsupportedOperationException(this.getClass() + ".mapGeneralProfile is not supported");
  }
  /**
   * Maps data from the external User object to the intern User object that contains
   * general information about the chosen user.
   *
   * @param externalUser The source Profile object.
   * @return The intern model User object.
   */
  default User mapUser(ExternalUser externalUser) {
    throw new UnsupportedOperationException(this.getClass() + ".mapUser is not supported");
  }
  /**
   * Maps data from the provider Education object to the intern Education object.
   *
   * @param externalEducation The source Education object.
   * @return The intern model education object.
   */
  default Education mapEducation(ExternalEducation externalEducation) {
    throw new UnsupportedOperationException(this.getClass() + ".mapEducation is not supported");
  }
  /**
   * Maps data from the external Experience object to the intern Experience object.
   *
   * @param externalSkill The source Skill object.
   * @return The intern model Skill object.
   */
  default Skill mapSkill(ExternalSkill externalSkill) {
    throw new UnsupportedOperationException(this.getClass() + ".mapmapSkill is not supported");
  }
  /**
   * Maps data from the external Experience object to the intern Experience object.
   *
   * @param externalExperience The source Certification object.
   * @return The intern model Experience object.
   */
  default Experience mapExperience(ExternalExperience externalExperience) {
    throw new UnsupportedOperationException(this.getClass() + ".mapExperience is not supported");
  }
  /**
   * Maps data from the external Certification object to the intern Certification object.
   *
   * @param externalCertification The source Certification object.
   * @return The intern model certification object.
   */
  default Certification mapCertification(ExternalCertification externalCertification) {
    throw new UnsupportedOperationException(this.getClass() + ".mapCertification is not supported");
  }
  /**
   * Maps data from the external Language object to the intern Language object.
   *
   * @param externalLanguage The source Language object.
   * @return The intern model Language object.
   */
  default Language mapLanguage(ExternalLanguage externalLanguage) {
    throw new UnsupportedOperationException(this.getClass() + ".mapLanguage is not supported");
  }
  /**
   * Maps data from the external School object to the intern School object.
   *
   * @param externalSchool The source School object.
   * @return The intern model School object.
   */
  default School mapSchool(ExternalSchool externalSchool) {
    throw new UnsupportedOperationException(this.getClass() + ".mapSchool is not supported");
  }
  /**
   * Maps data from the external Company object to the intern Company object.
   *
   * @param externalCompany The source Company object.
   * @return The intern model Company object.
   */
  default Company mapCompany(ExternalCompany externalCompany) {
    throw new UnsupportedOperationException(this.getClass() + ".mapCompany is not supported");
  }
}
