package com.example.demo;

public enum httpStatusCode {
	ok(200,"ok"),
	created(201,"created"),
	found(202,"found"),
	no_content(204,  "no content"),
	unauthorized(401, "unauthorized"),
	bad_request(400, "bad request"),
	forbidden(403, "forbidden"),
    not_found(404, "not found"),
    conflict(409, "conflict"),
    internal_server_error(500, "internal server error"),
    timeout_occurred(524, "a timeout occurred");
	
	private int code;
    private String msg;
	httpStatusCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
