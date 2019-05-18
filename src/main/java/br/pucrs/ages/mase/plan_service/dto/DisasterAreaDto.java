package br.pucrs.ages.mase.plan_service.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class DisasterAreaDto {
	
	private @NotNull BigDecimal latitude;
	private @NotNull BigDecimal longitude;
	
	public BigDecimal getLatitude() {
		return latitude;
	}
	
	public BigDecimal getLongitude() {
		return longitude;
	}
	
}
