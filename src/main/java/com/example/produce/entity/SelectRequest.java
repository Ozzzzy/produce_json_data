package com.example.produce.entity;

/**
 * @author hou
 * @date 2020/8/25
 */
public class SelectRequest {

    private int produceId;
    private int anotherId;

    public int getProduceId() {
        return produceId;
    }

    public void setProduceId(int produceId) {
        this.produceId = produceId;
    }

    public int getAnotherId() {
        return anotherId;
    }

    public void setAnotherId(int anotherId) {
        this.anotherId = anotherId;
    }

    public SelectRequest(int produceId,int anotherId){
        super();
        this.produceId = produceId;
        this.anotherId = anotherId;
    }
}
