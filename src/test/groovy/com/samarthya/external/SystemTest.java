package com.samarthya.external;

import com.samarthya.external.model.Friend;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class SystemTest {

    @Test
    public void testCreateReadDelete() {
        RestTemplate rstTemplate = new RestTemplate();
        String url = "http://localhost:8080/friend";
        Friend friend = new Friend("Gordon", "Moore");
        ResponseEntity<Friend> eFriend = rstTemplate.postForEntity(url, friend, Friend.class);

        Friend[] friends = rstTemplate.getForObject(url, Friend[].class);
        for (Friend fri : friends) {
            if (fri.getLastName().contains("Moore")) {
                Assertions.assertThat(fri).extracting(Friend::getFirstName).containsOnly("Gordon");
            }
        }


        rstTemplate.delete(url + "/" + eFriend.getBody().getId());
        Assertions.assertThat(rstTemplate.getForObject(url, Friend[].class)).isNotEmpty();
    }
}
