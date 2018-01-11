package com.pay.national.agent.common.utils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AmountUtil {

	private static final String TAX_RATE = "taxRate";// 适用税率
	
	private static final String MINUS_AMOUNT = "minusAmount";// 速算扣除数
	
	private static final String TOTAX_AMOUNT = "toTaxAmount";// 应纳税所得额
	
	private static final String TAX_AMOUNT = "taxAmount";// 纳税额


	/**
	 * 格式化字符串为2位金额
	 * 
	 * @Description 一句话描述方法用法
	 * @param amount
	 * @return
	 * @see 需要参考的类或方法
	 */
	public static BigDecimal formatAmount(String amount, int scale) {
		//BigDecimal tmp = new BigDecimal(amount);
		//tmp.setScale(scale, BigDecimal.ROUND_HALF_UP);
		return new BigDecimal(amount).setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 劳务报酬所得适用20%的比例税率，一次收入畸高的实行加成征收劳务报酬所得税率表
	 * 
	 * 应纳税所得额 税率 速算扣除数 不超过20000元 20% 0 超过20000元至50000元 30% 2000 超过50000元 40%
	 * 7000
	 * 
	 * 计算方法：
	 * 劳务报酬所得以每次取得为一次，属于一次性收入的，以取得该项收入为一次；属于同一项目连续性收入的，以一个月内取得的收入为一次。按次缴纳个人所得税：
	 * 应 纳 税 额 = 应纳税所得额 x适用税率-速算扣除数 应纳税所得额 =（每次收入额≤4000元）- 800
	 * 或者（每次收入额>4000元）x（1-20%）
	 * 劳务报酬所得每次收入不超过4000元的，减除费用800元；4000元以上的，减除20%的费用，其余额为应纳税所得额。
	 * 
	 * @Description 根据交易金额计算劳务税
	 * @param transAmount 交易金额
	 * @return
	 * @see 需要参考的类或方法
	 */
	public static BigDecimal getLaborTax(BigDecimal transAmount) {
		return getLaborTaxMap(transAmount).get(TAX_AMOUNT);
	}
	
	/**
	 * 
	 * @Description  一句话描述方法用法
	 * @param transAmount
	 * @return
	 * @see 需要参考的类或方法
	 */
	public static Map<String,BigDecimal> getLaborTaxMap(BigDecimal transAmount) {
		BigDecimal taxRate = new BigDecimal(0.00).setScale(6, BigDecimal.ROUND_HALF_UP); // 适用税率
		BigDecimal minusAmount = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP); // 速算扣除数
		BigDecimal toTaxAmount = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP); // 应纳税所得额
		BigDecimal taxAmount = new BigDecimal(0.00).setScale(2, BigDecimal.ROUND_HALF_UP); // 纳税额
		if (transAmount.doubleValue() <= 4000) {
			toTaxAmount = transAmount.subtract(BigDecimal.valueOf(800.00)).setScale(2, BigDecimal.ROUND_HALF_UP);
		} else {
			toTaxAmount = transAmount.multiply(BigDecimal.valueOf(0.80)).setScale(2, BigDecimal.ROUND_HALF_UP);
		}

		if (toTaxAmount.doubleValue() <= 20000) {
			taxRate = taxRate.add(BigDecimal.valueOf(0.20)).setScale(6, BigDecimal.ROUND_HALF_UP);
			taxAmount = toTaxAmount.multiply(taxRate).subtract(minusAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
		} else if (toTaxAmount.doubleValue() <= 50000) {
			taxRate = taxRate.add(BigDecimal.valueOf(0.30)).setScale(6, BigDecimal.ROUND_HALF_UP);
			minusAmount = minusAmount.add(new BigDecimal(2000)).setScale(2, BigDecimal.ROUND_HALF_UP);
			taxAmount = toTaxAmount.multiply(taxRate).subtract(minusAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
		} else {
			taxRate = taxRate.add(BigDecimal.valueOf(0.40)).setScale(6, BigDecimal.ROUND_HALF_UP);
			minusAmount = minusAmount.add(new BigDecimal(7000)).setScale(2, BigDecimal.ROUND_HALF_UP);
			taxAmount = toTaxAmount.multiply(taxRate).subtract(minusAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		Map<String,BigDecimal> ret = new HashMap<String,BigDecimal>();
		ret.put(TAX_RATE, taxRate);
		ret.put(MINUS_AMOUNT, minusAmount);
		ret.put(TOTAX_AMOUNT, toTaxAmount);
		ret.put(TAX_AMOUNT, taxAmount);
		return ret;
	}

	public static void main(String args[]) {
		System.out.println(AmountUtil.getLaborTax(new BigDecimal(800)).setScale(2));
		System.out.println(AmountUtil.getLaborTax(new BigDecimal(1000)).setScale(2));
		System.out.println(AmountUtil.getLaborTax(new BigDecimal(5000)).setScale(2));
		System.out.println(AmountUtil.getLaborTax(new BigDecimal(20000)).setScale(2));
		System.out.println(AmountUtil.getLaborTax(new BigDecimal(50000)).setScale(2));
		System.out.println(AmountUtil.getLaborTax(new BigDecimal(80000)).setScale(2));
		
		
		System.out.println(AmountUtil.formatAmount("111.050211",2));
		
		System.out.println(new BigDecimal("111.050211").setScale(2, BigDecimal.ROUND_HALF_UP));
		
		
		System.out.println(java.math.BigDecimal.valueOf(0.1).toString());
	}
}
