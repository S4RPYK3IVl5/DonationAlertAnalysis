package ru.quillaer.daa.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.quillaer.daa.domains.Donate;
import ru.quillaer.daa.domains.Token;
import ru.quillaer.daa.domains.User;
import ru.quillaer.daa.repositories.DonationRepository;
import ru.quillaer.daa.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class DonationsService {

    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final Gson gson = new GsonBuilder().create();
    private final RestTemplate restTemplate;

    @Autowired
    public DonationsService(DonationRepository donationRepository, UserRepository userRepository, RestTemplate restTemplate) {
        this.donationRepository = donationRepository;
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    public List<Donate> getDonations(UserDetails userDetails) {

        User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException("No such a user by username : " + userDetails.getUsername())
        );
        Token token = user.getToken();

        List<Donate> donates = new ArrayList<>();
        getDonationsFromAPI(donates,token);

        return donates;

    }

    private void getDonationsFromAPI(List<Donate> donates, Token token) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token.getAccess_token());
        HttpEntity req = new HttpEntity(httpHeaders);

        int page = 1;
        String url = "https://www.donationalerts.com/api/v1/alerts/donations?page=";

        while (true) {
            ResponseEntity<String> daUserResponseEntity = this.restTemplate.exchange(url + page, HttpMethod.GET, req, String.class, 1);
            JSONObject jsonObject = new JSONObject(daUserResponseEntity.getBody());
            int last_page = jsonObject.getJSONObject("meta").getInt("last_page");
            jsonObject.getJSONArray("data").iterator().forEachRemaining(data -> donates.add(gson.fromJson(data.toString(), Donate.class)));
            if (last_page == page)
                break;
            page++;
        }
    }
}
