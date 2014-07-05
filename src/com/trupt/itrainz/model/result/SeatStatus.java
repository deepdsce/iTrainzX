package com.trupt.itrainz.model.result;

public class SeatStatus {
	private String bookingStatus;
	private String currentStatus;
	public String getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}
	@Override
	public String toString() {
		return "PnrStatus [bookingStatus=" + bookingStatus + ", currentStatus="
				+ currentStatus + "]";
	}
	
}
