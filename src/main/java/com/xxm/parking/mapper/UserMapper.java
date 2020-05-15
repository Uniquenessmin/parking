package com.xxm.parking.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xxm.parking.pojo.User;

@Mapper
public interface UserMapper {
	
	//新增
	@Insert("insert into user(tel,passwd,name,platenumber,identity) values(#{tel},#{passwd},#{name},#{platenumber},#{identity})")
	public boolean create(User u);
	
	/**查询**/
	//根据账号查询用户
	@Select("select * from user where tel=#{tel}")
	public User find(@Param("tel") String tel);
	
	//查看用户列表
	@Select("select * from user")
	public List<User> findAll();
	
	//根据id查询用户
	@Select("select * from user where id=#{id}")
	public User findById(int id);
	
	//根据车牌id查询用户
	@Select("select * from user where platenumber=#{platenumber}")
	public User findByNum(String platenumber);
	
	//验证密码
	@Select("select passwd from user where id=#{id} ")
	public String getpwd(String id);
	
	/**修改**/
	//修改密码
	@Update("update user set passwd=#{password} where id=#{id}")
	public int changepwd(@Param("id")String id,@Param("password")String password);
	
	//修改车牌号,电话，名字
	@Update("update user set platenumber=#{platenumber},tel=#{tel},name=#{name} where id=#{id}")
	public int change(User u);

	
}
