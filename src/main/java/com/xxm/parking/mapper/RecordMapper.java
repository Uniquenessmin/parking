package com.xxm.parking.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xxm.parking.pojo.Record;

@Mapper
public interface RecordMapper {
	//生成新的停车记录
	@Insert("insert into record(seatid,createtime,userid) values(#{seatid},#{createtime},#{userid})")
	public boolean createRecord(Record record);
	
	//根据id查看停车记录信息
	@Select("select * from record where id = #{id}")
	public Record getRecordById(String id);
	
	//根据用户id查看停车记录列表
	@Select("select * from record where userid = #{userid}")
	public Record getRecordByUserid(String userid);
	
	//查看停车记录列表，支持模糊查询
	@Select("select * from record #{wheresql}")
	public List<Record> getRecordList(String wheresql);
	
	//修改停车记录信息
	@Update("update record set seatid=#{seatid},createtime=#{createtime} where id=#{id}")
	public Record changeRecord(Record record);
}
