package br.pucrs.ages.mase.plan_service.dto;

import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PlanExecutionDto extends PlanTemplateDto {
	
	private @NotNull LocalDateTime startTime;
	private @Valid @NotNull DisasterAreaDto disasterArea;
	
	public PlanExecutionDto() {}
	
	public PlanExecutionDto(String description, Map<Integer, DisasterNotificationDto> disasterNotifications,
			LocalDateTime startTime, DisasterAreaDto disasterArea) {
		super(description, disasterNotifications);
		this.startTime = startTime;
		this.disasterArea = disasterArea;
	}
	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	public DisasterAreaDto getDisasterArea() {
		return disasterArea;
	}
	
}
