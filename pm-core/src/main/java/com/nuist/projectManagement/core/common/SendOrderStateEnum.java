package com.pactera.esc.core.common;

/**
 * 送货单状态类型
 *
 * @author minhao.cai
 * @date 2020/04/24
 */
public enum SendOrderStateEnum implements BaseEnum {
    No_Finished("未录入", 0),
    No_Delivery("未送货",  1),
    In_Transit("在途中",  2),
    Registered("已登记",  3),
    Waitfor_Park("等待进园",  4),
    Entering_Park("正在进园",  5),
    In_Park("园区中",  6),
    Enter_Storage("已进厂", 7),
    Outerter_Storage("已出厂", 10),
    Leaveing_Park("正在出园",  13),
    Leave_Park("已出园", 14),
    Completed("已完成", 15),
    Cancel("已撤销", 12);

    private Integer code;
    private String msg;

    SendOrderStateEnum( String msg,Integer code) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    public static String getSendOrderStateMsg(Integer code){
        String name = "";
        for (SendOrderStateEnum stateEnum : SendOrderStateEnum.values()) {
            if (stateEnum.getCode() .equals(code) )
                name = stateEnum.getMsg();
        }
        return name;
    }
}
