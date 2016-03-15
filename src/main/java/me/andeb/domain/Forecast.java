package me.andeb.domain;

import java.util.List;

/**
 * Previs�o do tempo.
 * 
 * Classe de dom�nio baseada na resposta JSON da API do OpenWeather.
 * 
 * @author Anderson de Borba
 */
public class Forecast {

	public static class ForecastCity {

		/**
		 * Nome da cidade.
		 */
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	public static class ForecastMainDetail {

		/**
		 * Temperatura.
		 */
		private double temp;

		public double getTemp() {
			return temp;
		}

		public void setTemp(double temp) {
			this.temp = temp;
		}

	}

	public static class WeatherDetail {

		/**
		 * �cone para representa��o da previs�o.
		 */
		private String icon;
		/**
		 * Descri��o da previs�o.
		 */
		private String description;

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}

	public static class ForecastDetail {

		/**
		 * Data da previs�o.
		 */
		private String dt_txt;
		/**
		 * Informa��es sobre a cidade da previs�o.
		 */
		private ForecastMainDetail main;
		/**
		 * Lista com as previs�es.
		 */
		private List<WeatherDetail> weather;

		public String getDt_txt() {
			return dt_txt;
		}

		public void setDt(String dt_txt) {
			this.dt_txt = dt_txt;
		}

		public ForecastMainDetail getMain() {
			return main;
		}

		public void setMain(ForecastMainDetail main) {
			this.main = main;
		}

		public List<WeatherDetail> getWeather() {
			return weather;
		}

		public void setWeather(List<WeatherDetail> weather) {
			this.weather = weather;
		}

	}

	/**
	 * Informa��es sobre a cidade da previs�o.
	 */
	private ForecastCity city;

	/**
	 * Lista com as previs�es.
	 */
	private List<ForecastDetail> list;

	public List<ForecastDetail> getList() {
		return list;
	}

	public void setList(List<ForecastDetail> list) {
		this.list = list;
	}

	public ForecastCity getCity() {
		return city;
	}

	public void setCity(ForecastCity city) {
		this.city = city;
	}

}
