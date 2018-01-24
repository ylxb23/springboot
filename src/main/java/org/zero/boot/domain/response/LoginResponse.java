package org.zero.boot.domain.response;

import org.zero.boot.domain.vo.UserVo;

/**
 * 登录结果对象
 * 
 * @date 2018年1月23日 下午11:32:46
 * @author zero
 */
public class LoginResponse {
	
	public static final int CODE_FAILURE = 0;
	public static final String MSG_FAILURE= "登录失败";
	public static final int CODE_SUCCESS = 1;
	public static final String MSG_SUCCESS = "登录成功";
	
	/**
	 * 登录结果状态码
	 */
	private int code;
	/**
	 * 登录结果信息
	 */
	private String msg;
	
	/**
	 * 登录来源地址
	 */
	private String fromUrl;

	/**
	 * 登录成功后用户信息
	 */
	private UserVo<?> user;
	
	/**
	 * Protected Constructor 
	 */
	protected LoginResponse() {}

	public int getCode() {
		return code;
	}

	protected void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	protected void setMsg(String msg) {
		this.msg = msg;
	}

	public UserVo<?> getUser() {
		return user;
	}

	protected void setUser(UserVo<?> user) {
		this.user = user;
	}
	
	public String getFromUrl() {
		return fromUrl;
	}

	protected void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		LoginResponse response;
		private Builder() {
			response = new LoginResponse();
		}
		
		public Builder code(int code) {
			response.setCode(code);
			return this;
		}
		
		public Builder msg(String msg) {
			response.setMsg(msg);
			return this;
		}
		
		public Builder user(UserVo<?> user) {
			response.setUser(user);
			return this;
		}
		
		public Builder fromUrl(String fromUrl) {
			response.setFromUrl(fromUrl);
			return this;
		}
		
		public LoginResponse build() {
			return response;
		}
	}

}
