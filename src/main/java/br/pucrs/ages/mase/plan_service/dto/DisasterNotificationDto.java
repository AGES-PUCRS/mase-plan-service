package br.pucrs.ages.mase.plan_service.dto;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class DisasterNotificationDto {
	
	private @NotEmpty List<@Valid @NotNull VolunteersNeededDto> volunteersNeeded;
	
	public DisasterNotificationDto() {}
	
	public DisasterNotificationDto(List<VolunteersNeededDto> volunteersNeeded) {
		this.volunteersNeeded = volunteersNeeded;
	}
	
	public List<VolunteersNeededDto> getVolunteersNeeded() {
		return volunteersNeeded;
	}
	
}
