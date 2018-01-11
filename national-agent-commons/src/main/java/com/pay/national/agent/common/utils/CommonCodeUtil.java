package com.pay.national.agent.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @Description: 编号生成器
 * @see: CommonCodeUtil 此处填写需要参考的类
 * @version 2016年2月26日 下午1:47:40
 * @author meng.ren
 */
public class CommonCodeUtil {

	/**
	 * @Description 生成32位16进制数据库表主键
	 * @return
	 * @see 需要参考的类或方法
	 */
	public static String getPkId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	/** 
	 * @Description  生成32位唯一订单号
	 * @return
	 * @see 需要参考的类或方法
	 */
	public static String getOrderNo() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
	
	/** 生产 n 位随机数
   *  min 最小的n为数
   *  max 最大的n位数
   */
  public static int NextInt(final int min, final int max)
  {
      int tmp = Math.abs( new Random().nextInt());
      return tmp % (max - min + 1) + min;
  }
}
