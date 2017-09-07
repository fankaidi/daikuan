package co.kensure.frame;

import java.util.List;

/**
 *  <p>返回消息体
 *  <p>type：                        消息类型
 *  <p>message：                     消息信息
 *  <p>exception_message：           异常消息
 *  <p>resultData：                  返回数据集
 *  @see ResultData
 * @author Yingjie yao
 *
 */
public class ResultInfo {

    /**
     * 消息类型
     */
    private String type;

    /**
     * 消息信息
     */
    private Object message;

    /**
     * 异常消息
     */
    private Object exception_message;

    private ResultData resultData;

    public ResultInfo() {}

    public ResultInfo(String type, Object message) {
        this( type,message,null,null,null,0 );
    }

    public ResultInfo(String type, Object message, Object row) {
        this( type,message,row,null,null,0 );
    }

    public ResultInfo(String type, Object message, Iterable<?> rows, int count) {
        this( type,message,null,rows,null,count );
    }

    public ResultInfo(String type, Object message, Iterable<?> rows, List<String> fields, int count) {
        this( type,message,null,rows,fields,count );
    }

    public ResultInfo(String type, Object message, Object row, Iterable<?> rows, List<String> fields, int count) {
        this.type = type;
        this.message = message;
        resultData = new ResultData( row, rows, fields, count );
    }

    public ResultInfo(boolean type, String successMessage, String warningMessage ) {
        if( type ){
            setType( ResultType.SUCCESS );
            setMessage( successMessage );
        }else{
            setType( ResultType.ERROR );
            setMessage( warningMessage );
        }
    }

    /**
     * 消息类型
     * @return 字符串
     * @see ResultType
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 消息信息
     * @return 字符串
     */
    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    /**
     * 异常信息
     * @return  字符串
     */
    public Object getException_message() {
        return exception_message;
    }

    public void setException_message(Object exception_message) {
        this.exception_message = exception_message;
    }

    /**
     * 返回数据
     * @return ResultData
     * @see ResultData
     */
    public ResultData getResultData() {
        return resultData;
    }

    public void setResultData(ResultData resultData) {
        this.resultData = resultData;
    }

}
