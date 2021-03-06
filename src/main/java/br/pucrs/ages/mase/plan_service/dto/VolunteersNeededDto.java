package br.pucrs.ages.mase.plan_service.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class VolunteersNeededDto {
	
	private @NotEmpty String occupationNeeded;
	private @Positive Integer numberOfVolunteersNeeded;
	
	public VolunteersNeededDto() {}
	
	public VolunteersNeededDto(String occupationNeeded, Integer numberOfVolunteersNeeded) {
		this.occupationNeeded = occupationNeeded;
		this.numberOfVolunteersNeeded = numberOfVolunteersNeeded;
	}
	
	public String getOccupationNeeded() {
		return occupationNeeded;
	}
	
	public Integer getNumberOfVolunteersNeeded() {
		return numberOfVolunteersNeeded;
	}
	
}
