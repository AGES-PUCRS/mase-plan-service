package br.pucrs.ages.mase.plan_service.entity;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "planexecutions")
public class PlanExecution extends PlanTemplate {
	
	private @Indexed LocalDateTime startTime;
	private DisasterArea disasterArea;
	
	public LocalDateTime getStartTime() {
		return startTime;
	}
	
	public DisasterArea getDisasterArea() {
		return disasterArea;
	}
	
}
