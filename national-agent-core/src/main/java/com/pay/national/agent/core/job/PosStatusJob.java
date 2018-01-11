package com.pay.national.agent.core.job;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.pay.national.agent.core.service.pos.PosService;

/**
 * @Description: 查询卡友手刷免费领取的pos订单状态
 * @see: 需要参考的类
 * @version 2017年10月13日 上午10:45:34
 * @author zhenhui.liu
 */
@Component
public class PosStatusJob{// extends AbstractJob{

	/*@Resource
	private RecommenderAwardServiceFacade recommenderAwardServiceFacade;*/
	
	@Resource
	private PosService posService;
	//@Override
	public void execute() {
		/*//查询所有状态为已绑定的订单。修改订单状态
		List<RecommenderActivityBean> recommenderActivityBeans = recommenderAwardServiceFacade.findAllTransOrders();
		//修改已绑定订单为已绑定将订单状态更新为开始奖励。
		for (RecommenderActivityBean recommenderActivityBean : recommenderActivityBeans) {
			PosOrder posOrder = new PosOrder();
			posOrder.setSalesBillNo(recommenderActivityBean.getSalesBillNo());
			posOrder.setStoreOutNo(recommenderActivityBean.getStoreOutNo());
			posOrder.setPosSn(recommenderActivityBean.getPosSn());
			posOrder.setPosStatus(PosStatus.BIND);
			posOrder.setStatus(PosOrderStatus.TRUE);
			posOrder.setCustomerNo(recommenderActivityBean.getCustomerNo());
			posOrder.setCustomerName(recommenderActivityBean.getCustomerName());
			posService.updatePosOrderBySalesBillNo(posOrder);
		}*/
		
	}

}
