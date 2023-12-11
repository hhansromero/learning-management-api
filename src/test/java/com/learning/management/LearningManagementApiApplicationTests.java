package com.learning.management;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

//@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application.yml")
@ContextConfiguration(classes = {LearningManagementApiApplicationTests.class})
class LearningManagementApiApplicationTests {

    @Test
    void contextLoads() {
    }

}
