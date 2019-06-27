package br.pucrs.ages.mase.plan_service.dto;

public class NotificationDto {
	
	private String to;
	private String title;
	private String body;
	
	public NotificationDto() {}
	
	public NotificationDto(String to, String title, String body) {
		this.to = to;
		this.title = title;
		this.body = body;
	}
	
	public String getTo() {
		return to;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getBody() {
		return body;
	}
	
}
