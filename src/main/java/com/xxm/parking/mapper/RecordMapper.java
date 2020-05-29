package com.xxm.parking.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xxm.parking.pojo.Record;

@Mapper
public interface RecordMapper {
	// 生成新的停车记录
	@Insert("insert into record(seatid,createtime,userid) values(#{seatid},#{createtime},#{userid})")
	public boolean createRecord(@Param("seatid") int seatid, @Param("createtime") long createtime,
			@Param("userid") int userid);

	// 根据id查看停车记录信息
	@Select("select * from parking.record   where id = #{id}" 
			)
	public Record getRecordById(@Param("id")int id);

	// 根据id查看按钮次数
	@Select("select button from parking.record   where id = #{id}")
	public int getButtonNum(@Param("id")int id);
	// 根据用户id查看停车记录列表
	@Select("select * from record where userid = #{userid}")
	public Record getRecordByUserid(@Param("userid")int userid);

	// 根据用户id查看进行中的停车记录
	@Select("select * from record  where userid = #{userid} and endtime = 0 ")
	public Record getNowRecordByUserid(@Param("userid")int userid);

	// 查看停车记录列表，支持模糊查询
	@Select("select * from record #{wheresql}")
	public List<Record> getRecordList(String wheresql);

	// 修改停车记录信息
	@Update("update record set seatid=#{seatid} where id=#{id}")
	public int changeRecord(@Param("seatid") int seatid, @Param("id") int id);

	// 修改出场的停车记录信息
	@Update("update record set endtime=#{endtime},button=button+#{num} where userid=#{userid} and endtime=0")
	public boolean changeEnd(@Param("userid")int userid, @Param("endtime")long endtime, @Param("num")int num);
}
