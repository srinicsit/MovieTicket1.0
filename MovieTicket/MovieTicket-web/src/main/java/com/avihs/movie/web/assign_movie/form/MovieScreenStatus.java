package com.avihs.movie.web.assign_movie.form;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.avihs.movie.business.model.SeatsStatusCount;
import com.avihs.movie.business.screen.model.Screen;

@JsonSerialize(include = Inclusion.NON_NULL)
public class MovieScreenStatus {

	private Screen screen;

	private List<TimeAndStatusInfo> timingsAndAvailableInfo = new ArrayList<TimeAndStatusInfo>(
			0);

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}

	public List<TimeAndStatusInfo> getTimingsAndAvailableInfo() {
		return timingsAndAvailableInfo;
	}

	public static class TimeAndStatusInfo {

		private String time;

		private Integer movieScreenId;

		private List<SeatsStatusCount> seatsStatusCounts;

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public List<SeatsStatusCount> getSeatsStatusCounts() {
			return seatsStatusCounts;
		}

		public void setSeatsStatusCounts(
				List<SeatsStatusCount> seatsStatusCounts) {
			this.seatsStatusCounts = seatsStatusCounts;
		}

		public Integer getMovieScreenId() {
			return movieScreenId;
		}

		public void setMovieScreenId(Integer movieScreenId) {
			this.movieScreenId = movieScreenId;
		}
	}

}
