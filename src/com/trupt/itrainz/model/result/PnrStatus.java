package com.trupt.itrainz.model.result;

import java.util.ArrayList;

public class PnrStatus extends Result {
	private String pnrNumber;
	private String trainNumber;
	private String trainName;
	private String date;
	private String fromStation;
	private String toStation;
	private String reservedUptoStation;
	private String boardingPointStation;
	private String classs;
	private boolean chartPrepared;
	private ArrayList<SeatStatus> seatStatus;
	
	public String getPnrNumber() {
		return pnrNumber;
	}
	public void setPnrNumber(String pnrNumber) {
		this.pnrNumber = pnrNumber;
	}
	public String getTrainNumber() {
		return trainNumber;
	}
	public void setTrainNumber(String trainNumber) {
		this.trainNumber = trainNumber;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getFromStation() {
		return fromStation;
	}
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}
	public String getToStation() {
		return toStation;
	}
	public void setToStation(String toStation) {
		this.toStation = toStation;
	}
	public String getReservedUptoStation() {
		return reservedUptoStation;
	}
	public void setReservedUptoStation(String reservedUptoStation) {
		this.reservedUptoStation = reservedUptoStation;
	}
	public String getBoardingPointStation() {
		return boardingPointStation;
	}
	public void setBoardingPointStation(String boardingPointStation) {
		this.boardingPointStation = boardingPointStation;
	}
	public String getClasss() {
		return classs;
	}
	public void setClasss(String classs) {
		this.classs = classs;
	}
	public boolean isChartPrepared() {
		return chartPrepared;
	}
	public void setChartPrepared(boolean chartPrepared) {
		this.chartPrepared = chartPrepared;
	}
	public ArrayList<SeatStatus> getSeatStatus() {
		return seatStatus;
	}
	public void setSeatStatus(ArrayList<SeatStatus> pnrStatus) {
		this.seatStatus = pnrStatus;
	}
	@Override
	public String toString() {
		return "PnrInfo [pnrNumber=" + pnrNumber + ", trainNumber="
				+ trainNumber + ", trainName=" + trainName + ", date=" + date
				+ ", fromStation=" + fromStation + ", toStation=" + toStation
				+ ", reservedUptoStation=" + reservedUptoStation
				+ ", boardingPointStation=" + boardingPointStation
				+ ", classs=" + classs + ", chartPrepared=" + chartPrepared
				+ ", pnrStatus=" + seatStatus + "]";
	}
	
}
