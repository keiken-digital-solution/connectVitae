package io.connectvitae.connectvitaelibrary.services;

import io.connectvitae.connectvitaelibrary.models.*;

public interface MapperServiceInterface {
    public Profile  apply(Object externalProfile);

    /**
     * Maps data from the external Profile object to the intern User object that contains
     * general information about the chosen user.
     *
     * @param externalProfile The source Profile object.
     * @return The intern model User object.
     */
    public User mapUser(Object externalProfile);

    /**
     * Maps data from the provider Education object to the intern Education object.
     *
     * @param externalEducation The source Education object.
     * @return The intern model education object.
     */
    public Education mapEducation(Object externalEducation);

    /**
     * Maps data from the external Experience object to the intern Experience object.
     *
     * @param externalSkill The source Skill object.
     * @return The intern model Skill object.
     */
    public Skill mapSkill(Object externalSkill);

    /**
     * Maps data from the external Experience object to the intern Experience object.
     *
     * @param externalExperience The source Certification object.
     * @return The intern model Experience object.
     */
    public Experience mapExperience(Object externalExperience);

    /**
     * Maps data from the external Certification object to the intern Certification object.
     *
     * @param externalCertification The source Certification object.
     * @return The intern model certification object.
     */
    public Certification mapCertification(Object externalCertification);
}

