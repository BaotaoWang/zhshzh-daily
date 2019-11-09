package cn.com.zhshzh.common.scheduleTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 测试log4j打印日志的定时任务
 * 
 * @author WBT
 * @since 2019/10/22
 */
@Component // 注册
@EnableScheduling // 开启定时任务
// @EnableAsync // 开启多线程
public class Log4jScheduleTask {

	private static final Logger logger = LogManager.getLogger(Log4jScheduleTask.class);

	/**
	 * 测试定时器1
	 */
	// @Async // 异步处理
	@Scheduled(cron = "*/3 * * * * ?")
	public void scheduleTask1() {
		logger.debug("scheduleTask1: " + System.currentTimeMillis());
		logger.info("scheduleTask1: " + System.currentTimeMillis());
		logger.error("scheduleTask1: " + System.currentTimeMillis());
		logger.error(Thread.currentThread().getName());
	}

	/**
	 * 测试定时器1
	 */
	// @Async // 异步处理
	@Scheduled(cron = "*/5 * * * * ?")
	public void scheduleTask2() {
		logger.debug("scheduleTask2: " + System.currentTimeMillis());
		logger.info("scheduleTask2: " + System.currentTimeMillis());
		logger.error("scheduleTask2: " + System.currentTimeMillis());
		logger.error(Thread.currentThread().getName());
	}

}
