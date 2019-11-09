package cn.com.zhshzh.system.user.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.com.zhshzh.ZhshzhApplication;
import cn.com.zhshzh.system.user.po.SysUserInfoPO;
import cn.com.zhshzh.system.user.service.SysUserInfoService;

//获取启动类，加载配置，确定装载 Spring 程序的装载方法，它回去寻找 主配置启动类（被 @SpringBootApplication 注解的）
@SpringBootTest(classes = ZhshzhApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//让 JUnit 运行 Spring 的测试环境， 获得 Spring 环境的上下文的支持
@RunWith(SpringRunner.class)
public class SysUserInfoServiceImplTest {
	private static Logger logger = LogManager.getLogger(SysUserInfoServiceImplTest.class);
	
	@Autowired
	private SysUserInfoService sysUserInfoService;
	
	@Test
	public void testGetSysUserInfo() {
		Long userInfoId = 100000L;
		SysUserInfoPO sysUserInfoPO = sysUserInfoService.getSysUserInfo(userInfoId);
		logger.error(sysUserInfoPO);
	}

	/*
	 * @Test public void testListSysUserInfo() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testInsertSysUserInfo() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testUpdateSysUserInfo() { fail("Not yet implemented"); }
	 */

}
