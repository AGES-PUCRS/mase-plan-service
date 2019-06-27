package br.pucrs.ages.mase.plan_service.dto;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PlanTemplateDto {
	
	private @NotEmpty String description;
	private @NotEmpty Map<Integer, @Valid @NotNull DisasterNotificationDto> disasterNotifications;
	
	public PlanTemplateDto() {}
	
	public PlanTemplateDto(String description, Map<Integer, DisasterNotificationDto> disasterNotifications) {
		this.description = description;
		this.disasterNotifications = disasterNotifications;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Map<Integer, DisasterNotificationDto> getDisasterNotifications() {
		return disasterNotifications;
	}
	
}
