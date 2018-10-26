package com.samarthya.external;

import com.samarthya.external.controller.FriendController;
import com.samarthya.external.model.Friend;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.ValidationException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTests {

    @Autowired
    FriendController friendController;

    @Test
    public void testCreateReadDelete() throws ValidationException {
        Friend friend = new Friend("Gordon", "Moore");

        Friend friendResult = friendController.create(friend);
        Iterable<Friend> friends = friendController.read();

        System.out.println(">> " + friendResult.getFirstName() + " : " + friendResult.getId());

        for (Friend x : friends) {
            System.out.println("> " + x.getFirstName() + " : " + x.getId());
        }
        Assertions.assertThat(friends).last().hasFieldOrPropertyWithValue("firstName", "Moore");

        friendController.delete(friendResult.getId());
        Assertions.assertThat(friendController.read()).doesNotContain(friendResult);

    }
}
