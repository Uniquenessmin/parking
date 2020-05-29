package com.xxm.parking.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
		@Insert("insert into parking.`order`(userid,recordid,createtime,updatetime,status) values(#{userid},#{recordid},#{createtime},#{createtime},#{status});")
		public boolean createOrder(Order order);
		
		//根据id查看订单信息
		@Select("select * from parking.`order` where id = #{id}")
		public Order getOrderById(@Param("id")String id);
		
		//根据用户id查看订单记录列表
		@Select("select * from parking.`order` where userid = #{userid} order by createtime desc")
		public List<Order> getOrderByUserid(@Param("userid")int userid);
		
		//查看订单列表，支持模糊查询
		@Select("select * from parking.`order` #{wheresql}")
		public List<Order> getOrderList(String wheresql);
		
		//查看正在进行的订单数量
		@Select("select count(1) from parking.`order` where userid = #{userid} and status=1")
		public int getNowOrderCount(@Param("userid")int userid);
				
		
		//查看进行中的订单
		@Select("select * from `order` where userid = #{userid} and status=1")
		public Order getNowOrder(@Param("userid")int userid);

		//设置订单结束时间
		@Update("update parking.`order` set fee=#{fee},endtime=#{endtime},updatetime=#{endtime} where id=#{id}")
		public boolean setEndTime(@Param("fee")double fee,@Param("endtime")long endtime,@Param("id")int id);

		//订单完成--修改
		@Update("update parking.`order` set status=#{status},paytype=#{paytype}  where id=#{id}")
		public boolean updateOrder(@Param("id")int id, @Param("status")int status, @Param("paytype")int paytype);


}
