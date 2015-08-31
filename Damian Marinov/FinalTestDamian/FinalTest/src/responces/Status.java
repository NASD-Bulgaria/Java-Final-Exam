package responces;

public class Status {
	private String status;
	
	public Status(){	
	}
	
	public void setStatusOK(){
		setStatus("OK");
	}
	
	public void setStatusFailed(){
		setStatus("Failed");
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
