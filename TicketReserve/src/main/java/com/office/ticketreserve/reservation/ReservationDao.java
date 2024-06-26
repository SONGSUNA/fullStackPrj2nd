package com.office.ticketreserve.reservation;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReservationDao {

	List<ReservationDto> selectReservation(Map<String, Object> dateTime);

	int insertReservationSeat1(Map<String, Object> hashMap);

	int insertReservationSeat2(Map<String, Object> hashMap);

	int updateReservationForCancel(int r_no);

	String selectPId(int t_no);

	void updateNowReserve(String p_id);

	void updateNowReserveDown(String p_id);

	void selectPIdByPName(String p_name);

	int selectTNoByRNo(int r_no);

}
