package soulCode.empresa1.controllers.exceptions;

public class StandardError {
	
	//traz o modelo da excessao criada

			private Long timestamp;
			private Integer status;
			private String error;
			
			public StandardError(Long timestamp, Integer status, String error) {
				super();
				this.timestamp = timestamp;
				this.status = status;
				this.error = error;
			}

			public Long getTimestamp() {
				return timestamp;
			}

			public void setTimestamp(Long timestamp) {
				this.timestamp = timestamp;
			}

			public Integer getStatus() {
				return status;
			}

			public void setStatus(Integer status) {
				this.status = status;
			}

			public String getError() {
				return error;
			}

			public void setError(String error) {
				this.error = error;
			}
			

	

}
