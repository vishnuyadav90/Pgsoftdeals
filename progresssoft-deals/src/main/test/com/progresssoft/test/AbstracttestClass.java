package com.progresssoft.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=com.progresssoft.test.DealsValidatorTest.class)
public abstract class AbstracttestClass {
	Logger logger = LoggerFactory.getLogger(this.getClass());

}
