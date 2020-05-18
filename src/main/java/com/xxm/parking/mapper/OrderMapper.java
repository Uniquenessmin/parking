package com.xxm.parking.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.xxm.parking.pojo.Order;

/**
 * 订单
 * @author
 *
 */
@Mapper
public interface OrderMapper {
		//生成新的订单
		@Insert("insert into order(userid,recordid,createtime,updatetime) values(#{userid},#{recordid},#{createtime},#{updatetime})")
		public boolean createOrder(Order Order);
		
		//根据id查看订单信息
		@Select("select * from record where id = #{id}")
		public Order getOrderById(String id);
		
		//根据用户id查看订单停车记录列表
		@Select("select * from order where userid = #{userid}")
		public List<Order> getOrderByUserid(String userid);
		
		//查看订单列表，支持模糊查询
		@Select("select * from order #{wheresql}")
		public List<Order> getOrderList(String wheresql);
		
		//修改订单信息--查看费用时，更新时间和费用
		@Update("update record set fee=#{fee},updatetime=#{updatetime} where id=#{id}")
		public Order changeOrder1(Order order);
		
		//修改订单信息--支付完成时更新
		@Update("update record set fee=#{fee},paytype=#{paytype},status=#{status},endtime=#{endtime} where id=#{id}")
		public Order changeOrder2(Order order);

}
