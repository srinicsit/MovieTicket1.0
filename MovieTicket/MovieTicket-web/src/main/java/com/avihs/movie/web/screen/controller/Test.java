package com.avihs.movie.web.screen.controller;

import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.codehaus.jackson.map.ObjectWriter;

import com.avihs.movie.business.rows.model.Rows;
import com.avihs.movie.business.seat_class_type.model.SeatClassType;
import com.avihs.movie.business.seats.model.Seats;

public class Test {

	public static void main(String[] args) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter objectWriter = mapper.writer();
		SeatClassType seatClassType = new SeatClassType();
		Rows rows = new Rows();
		rows.getSeats().add(new Seats());
		seatClassType.getRowsList().add(rows);
		String result = objectWriter.writeValueAsString(seatClassType);
		System.out.println(result);

		ObjectReader reader = mapper.reader(Map.class);
		System.out
				.println("reader"
						+ reader.readValue("[{\"isActive\":\"Y\",\"rowList\":[{\"isActive\":\"Y\",\"seats\":[{\"isActive\":\"Y\"}]}]},{\"isActive\":\"Y\",\"rowList\":[{\"isActive\":\"Y\",\"seats\":[{\"isActive\":\"Y\"}]}]}]"));
	}
}
