package exceptionInfo;

public enum ErrorCode {

    NULL_OBJ("LUO001","对象为空"),
    ERROR_ADD_USER("LUO002","添加用户失败"),
    UNKNOWN_ERROR("LUO999","系统繁忙，请稍后再试...."),
    FILE_NOT_FOUND("FILE001","你要读取的资源找不到");
    
    private String value;
    public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	private String desc;

    private ErrorCode(String value, String desc) {
        this.setValue(value);
        this.setDesc(desc);
    }
}