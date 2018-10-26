package com.samarthya.external

import com.samarthya.external.controller.FriendController
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner)
@SpringBootTest
class ExternalApplicationTests {

    @Autowired
    FriendController friendController

    @Test
	void contextLoads() {
        print(' runing test ')
        Assert.assertNotNull(friendController)
	}

}
