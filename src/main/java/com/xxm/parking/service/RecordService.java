package com.xxm.parking.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxm.parking.mapper.RecordMapper;
import com.xxm.parking.pojo.Order;
import com.xxm.parking.pojo.Plot;
import com.xxm.parking.pojo.Record;
import com.xxm.parking.pojo.Seat;

@Service
public class RecordService {

	@Autowired
	RecordMapper recordMapper;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	SeatService seatService;
	@Autowired
	PlotService plotService;
		
	public Map<String,Object> startParking(HashMap<String,Object> map,int seatid){
		long createtime = Long.valueOf(String.valueOf(map.get("createtime"))).longValue();

		int userid = Integer.valueOf((String) map.get("userid"));
		
		Map<String,Object> out = new HashMap<>();
		boolean isOk = recordMapper.createRecord(seatid, createtime, userid);
		Record r = recordMapper.getNowRecordByUserid(userid);
		//订单计费
		Order order = new Order();
		
		if(isOk) {
			out.put("recordid",r.getId());			
			out.put("msg", "生成停车记录");
			
			order.setRecordid(r.getId());
			order.setUserid(userid);
			order.setCreatetime(createtime);
			order.setStatus(1);
			Order order2 = orderService.createOrder(order);
			if(order2!=null) {
				out.put("orderid", order2.getId());
				out.put("state", 1);
				out.put("msg", "生成新订单成功");
				
			}else {
				out.put("state", 2);
				out.put("msg", "生成新订单失败");
			}
		}
		
		return out;
	}
	
	public Record updateRecord(int seatid,int recordid) {
		int n = recordMapper.changeRecord(seatid, recordid);
		Record r = null;
		if(n>0) {
			r = recordMapper.getRecordById(recordid);
		}
		return r;
	}
	
	/**
	 * 正在停车的记录
	 * @param userid
	 * @return
	 */
	public Record nowRecord(int userid) {
		return recordMapper.getNowRecordByUserid(userid);
	}
	
	/**
	 * 按钮数
	 * @param id
	 * @return
	 */
	public int getButtonNum(int id) {
		return recordMapper.getButtonNum(id);
	}

	/**
	 * 出场修改停车记录参数
	 * @param userid
	 * @param endtime
	 * @param i
	 * @return
	 */
	public boolean changeEnd(int userid, long endtime, int num) {
		return recordMapper.changeEnd(userid, endtime,  num);
	}

	/**
	 *根据id获得 停车记录
	 * @param recordid
	 * @return
	 */
	public Record getRecordById(int recordid) {
		
		return recordMapper.getRecordById(recordid);
	}

	/**
	 * 查看停车记录列表
	 * @param map
	 * @return
	 */
	public List<Record> getRecordList(int userid ) {
		
		List<Record> list = recordMapper.getRecordList(userid);
		for(Record r:list) {
			
			//设置泊位
			Seat se = seatService.getSeat(r.getSeatid());
			r.setSeat(se);
			//设置停车场
			Plot pl = plotService.getPlot(se.getParkingid());
			r.setPlot(pl);			
		}
		return list;
	}
}
