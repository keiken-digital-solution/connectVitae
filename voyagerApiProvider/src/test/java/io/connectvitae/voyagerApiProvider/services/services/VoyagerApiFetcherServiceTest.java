package io.connectvitae.voyagerApiProvider.services.services;


import io.connectvitae.voyagerApiProvider.services.VoyagerApiFetcherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class VoyagerApiFetcherServiceTest {
  @Autowired
  private VoyagerApiFetcherService voyagerApiFetcherService;

  @Test
  public void testGetCookies() {
    List<String> cookieList = Arrays.asList("JSESSIONID=mockedJSessionID", "li_at=mockedLiAt");
    // Creating HttpHeaders with the cookies
    HttpHeaders headers = new HttpHeaders();
    headers.addAll(HttpHeaders.SET_COOKIE, cookieList);

    // Creating a ResponseEntity with the headers
    ResponseEntity<String> responseEntity =
        new ResponseEntity<>("response body here", headers, HttpStatus.OK);

    // Calling the getCookies method with the mock ResponseEntity
    Map<String, String> cookiesMap = VoyagerApiFetcherService.getCookies(responseEntity);

    assertEquals("mockedJSessionID", cookiesMap.get("JSESSIONID"));
    assertEquals("mockedLiAt", cookiesMap.get("li_at"));
  }
}
