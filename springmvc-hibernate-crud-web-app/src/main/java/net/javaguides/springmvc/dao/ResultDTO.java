package net.javaguides.springmvc.dao;

public class ResultDTO {

    private String token;
    private String jsessionId;
    private String messageCode;
    private String messageEn;
    private String messageJp;

    public ResultDTO() {
        super();
    }

    public ResultDTO(String token, String jsessionId) {
        super();
        this.token = token;
        this.jsessionId = jsessionId;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getJsessionId() {
        return jsessionId;
    }
    public void setJsessionId(String jsessionId) {
        this.jsessionId = jsessionId;
    }
    public String getMessageCode() {
        return messageCode;
    }
    public void setMessageCode(String errorCode) {
        this.messageCode = errorCode;
    }
	public String getMessageEn() {
		return messageEn;
	}
	public void setMessageEn(String errorMessageEn) {
		this.messageEn = errorMessageEn;
	}
	public String getMessageJp() {
		return messageJp;
	}
	public void setMessageJp(String errorMessageJp) {
		this.messageJp = errorMessageJp;
	}
}
