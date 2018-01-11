/**
 *
 */
package com.pay.national.agent.core.test.context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pay.national.agent.core.context.SpringRootConfig;

/**
 * @Description: 这里用一句话描述这个类的作用
 * @see: BaseTest 此处填写需要参考的类
 * @version 2015年12月9日 上午11:32:59
 * @author dong.lian
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringRootConfig.class })
@ActiveProfiles("development")
@Commit
public class BaseTest {

	@Test
	public void testConfig()
	{
	}

}
