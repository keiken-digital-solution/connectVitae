package io.connectvitae.connectvitaelibrary.services;


public interface FetcherServiceInterface {
    public void authenticate(String username, String password);

    public Object fetchUser(String profileId);

    public Object fetchExperiences(String profileId);

    public Object fetchEducations(String profileId);

    public Object fetchSkills(String profileId);

    public Object fetchCertifications(String profileId);

    public Object fetchLanguages(String profileId);
}
