package com.example.demo.domain.enums;

import java.util.HashMap;
import java.util.Map;

public enum ExampaperTypeEnum {

    Fixed(1,"固定试卷"),
    TimeLimit(4,"时段试卷"),
    Task(6,"任务试卷");
    int code;
    String name;
    ExampaperTypeEnum(int code,String name){
        this.code=code;
        this.name=name;
    }

    private static final Map<Integer,ExampaperTypeEnum> keyMap=new HashMap<>();

    static {
        for (ExampaperTypeEnum item:ExampaperTypeEnum.values()){
            keyMap.put(item.getCode(),item);
        }
    }

    public static ExampaperTypeEnum fromCode(Integer code){
        return keyMap.get(code);
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
