package br.pucrs.ages.mase.plan_service.dto;

import javax.validation.constraints.NotEmpty;

public class VolunteerDto {
	
	private @NotEmpty String mobileId;
	
	public VolunteerDto() {}
	
	public VolunteerDto(String mobileId) {
		this.mobileId = mobileId;
	}
	
	public String getMobileId() {
		return mobileId;
	}
	
}
