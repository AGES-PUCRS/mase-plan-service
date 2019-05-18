package br.pucrs.ages.mase.plan_service.entity;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "plantemplates")
public class PlanTemplate {
	
	private @Id String id;
	private String description;
	private Map<Integer, DisasterNotification> disasterNotifications;
	
	public String getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Map<Integer, DisasterNotification> getDisasterNotifications() {
		return disasterNotifications;
	}
	
}
