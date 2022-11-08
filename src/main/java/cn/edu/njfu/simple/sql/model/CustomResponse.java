package cn.edu.njfu.simple.sql.model;

/**
 * 
 * ret =  0 代表服务器没问题，返回了正确的数据；
 * ret = -1 代表服务器有问题，返回了错误信息
 * @author zhuyuanfu
 *
 * @param <T>
 */
public class CustomResponse<T> {
	private int ret;
	private T data;
	
	public CustomResponse(int ret1, T data1) {
		this.ret = ret1;
		this.data = data1;
	}

	public int getRet() {
		return ret;
	}
	public void setRet(int ret) {
		this.ret = ret;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
