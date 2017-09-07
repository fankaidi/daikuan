package co.kensure.frame;

/**
 * Const
 *
 * @author Yingjie yao
 * @date 2016/11/10
 */
public class Const {

	public static String DEFAULT_PATH = "";

	public static String DEFAULT_URL = "";

	public static final String MESSAGE = "message";

	public static final String DISABLE = "DISABLE";

	public static final String IS_DEFAULT = "IS_DEFAULT";

	public static final String MENU_CACHE = "menuCache";

	public static final String DICTIONARY_CACHE = "dictionaryCache";

	public static final String DICTIONARY_CACHE_MAP = "dictionaryCacheMap";

	public static final String DIRECTORY_CACHE = "directoryCache";

	public static final String CONNECTION_CACHE = "connectionCache";

	public final static String TRANSFORMATION = "trans";

	public final static String JOB = "job";

	public final static String DIRECTORY = "directory";

	public final static String ENCODING = "utf-8";

	public final static String XML_TAG_TRANSDEBUGPARAM = "TransDebugParam";

	public final static String SELECT_SUCCESS = "查询成功";

	public final static String SELECT_FAIL = "查询失败";

	public final static String SAVE_SUCCESS = "保存成功";

	public final static String SAVE_FAIL = "保存失败";

	public final static String DELETE_SUCCESS = "删除成功";

	public final static String DELETE_FAIL = "删除失败";

	public final static String PAUSE_SUCCESS = "暂停成功";

	public final static String PAUSE_FAIL = "暂停失败";

	public final static String RESUME_SUCCESS = "继续成功";

	public final static String RESUME_FAIL = "继续失败";

	public final static String STARTUP_SUCCESS = "启动成功";

	public final static String STARTUP_FAIL = "启动失败";

	public final static String STOP_SUCCESS = "已停止";

	public final static String STOP_FAIL = "停止失败";

	public final static String DEPLOY_SUCCESS = "部署成功";

	public final static String DEPLOY_FAIL = "部署失败";

	public final static String VALIDATE_PASSED = "验证通过";

	public final static String CODE_EXISTED = "编号已存在";

	public final static String ID_IS_NULL = "id不得为空";

	public final static String CODE_IS_NULL = "编号不得为空";

	public final static String STATUS_IS_NULL = "状态不得为空";

	public final static String NAME_IS_NULL = "请给这个数据库连接一个名称";

	public final static String TYPE_IS_NULL = "类型不得为空";

	public final static String PARAM_IS_NULL = "参数不得为空";

	public final static String LIMIT_IS_NULL = "条数不得为空";

	public final static String CURRENT_IS_NULL = "页数不得为空";

	public final static String JSON_IS_NULL = "json不得为空";

	public final static String XML_IS_NULL = "xml不得为空";

	public final static String SORT_IS_NULL = "排序不得为空";

	public final static String CONTENT_IS_NULL = "正文不得为空";

	public final static String INDEX_IS_NULL = "索引不得为空";

	public final static String DATABASE_NAME_IS_NULL = "连接名称不得为空";

	public final static String CONNECT_NORMAL = "连接正常";

	public final static String CONNECT_FAIL = "连接失败";

	public final static String START_TIME_IS_NULL = "开始时间不得为空";

	public final static String END_TIME_IS_NULL = "结束时间不得为空";

	public final static int THRESHOLD = 99;

	// 当前用户key
	public final static String CURRENT_USER = "user";

	// 用户下所有可用的一级菜单
	public final static String PARENT_MENUS = "parent_menus";

	// 用户下所有可用的二级菜单
	public final static String SECOND_CHILDREN_MENUS = "second_children_menus";

	public final static String DIRECTORY_TREE = "directoryTree";

	public final static String CONNECTIONS = "connections";

	public final static String RUNNING = "running";

	public final static String STOPPED = "stopped";

	public final static String PAUSED = "paused";

	public final static String SCHEDULER_TYPE = "SchedulerType";

	public final static String WEEK = "WEEK";

	public final static String ROOT_MENU = "ROOT_MENU";

	public final static String HBASE = "HBASE";
	/*
	 * 资源类型 作业：job转换：trans链接：conn
	 * 
	 * 字典：dict报表：report数据库元数据：datameta对象元数据：objmeta数据地图：datamap
	 */
	public final static String[] RESOURCEMETA = { "job", "trans", "conn", "dict", "report", "datameta", "objmeta", "datamap" };

}
