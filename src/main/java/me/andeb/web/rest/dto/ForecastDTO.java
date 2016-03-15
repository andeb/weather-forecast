package me.andeb.web.rest.dto;

/**
 * Data Transfer Object de uma previs�o do tempo.
 * 
 * @author Anderson de Borba
 */
public class ForecastDTO {

	/**
	 * �cone para a previs�o do tempo.
	 */
	private String icon;
	/**
	 * Tipo da previs�o do tempo (ex. rain..)
	 */
	private String type;
	/**
	 * Data para a previs�o.
	 */
	private String date;
	/**
	 * Temperatura para a data em Celsius.
	 */
	private String tempCelsius;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTempCelsius() {
		return tempCelsius;
	}

	public void setTempCelsius(String tempCelsius) {
		this.tempCelsius = tempCelsius;
	}

}
