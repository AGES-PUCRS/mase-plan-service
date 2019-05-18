package br.pucrs.ages.mase.plan_service.dto;

import java.util.Map;

public class PlanTemplateDto {
	
	private String description;
	private Map<Integer, DisasterNotificationDto> disasterNotifications;
	
	public String getDescription() {
		return description;
	}
	
	public Map<Integer, DisasterNotificationDto> getDisasterNotifications() {
		return disasterNotifications;
	}
	
}
