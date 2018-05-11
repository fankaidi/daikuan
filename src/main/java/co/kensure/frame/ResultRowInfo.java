package co.kensure.frame;


/**
 * <p>
 * 返回消息体
 * @see ResultRowInfo
 * @author fankaidi
 *
 */
public class ResultRowInfo extends ResultInfo {

	public ResultRowInfo() {
		super(ResultType.SUCCESS, Const.SELECT_SUCCESS, null, null, null, 0);
	}
	
	public ResultRowInfo(Object row) {
		super(ResultType.SUCCESS, Const.SELECT_SUCCESS, row, null, null, 0);
	}
	


}
