package android.bignerdranch.lost.bean;

public class EventMessage {

    private Object mObject;

    private int messageType;

    private int Num;

    public EventMessage(int type) {
        messageType = type;
    }

    public EventMessage(int type, Object object) {
        mObject = object;
        messageType = type;
    }

    public EventMessage(int type, Object object, int num) {
        mObject = object;
        messageType = type;
        Num = num;
    }

    public Object getmObject() {
        return mObject;
    }

    public int getMessageType() {
        return messageType;
    }

    public int getNum() {
        return Num;
    }

    public static final int LOGIN = 3;//登陆
    public static final int HEAD = 1;//头像
    public static final int LOGOUT = 2;//退出
    public static final int REFRESH = 4;//刷新
    public static final int REMOVE = 5;//删除
    public static final int ADD = 6;//添加
    public static final int MODIFY_SELF = 7;//更新用户信息
    public static final int REFRESH_SHOP_CAR = 8;
    public static final int CHOICE_ADDRESS = 9;


    public static final int ModifyHead = 10;

}
