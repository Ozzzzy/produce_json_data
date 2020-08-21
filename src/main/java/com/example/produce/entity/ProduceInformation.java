package com.example.produce.entity;

/**
 * @author hou
 * @date 2020/8/21
 */
public class ProduceInformation {

    private int produceId;
    private String produceName;
    private String produceIndustry;
    private String deviceNode;
    private String accessProtocol;
    private String networkingMode;
    private String produceModel;
    private String produceDescription;

    public int getProduceId() {
        return produceId;
    }

    public void setProduceId(int produceId) {
        this.produceId = produceId;
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
    }

    public String getProduceIndustry() {
        return produceIndustry;
    }

    public void setProduceIndustry(String produceIndustry) {
        this.produceIndustry = produceIndustry;
    }

    public String getDeviceNode() {
        return deviceNode;
    }

    public void setDeviceNode(String deviceNode) {
        this.deviceNode = deviceNode;
    }

    public String getAccessProtocol() {
        return accessProtocol;
    }

    public void setAccessProtocol(String accessProtocol) {
        this.accessProtocol = accessProtocol;
    }

    public String getNetworkingMode() {
        return networkingMode;
    }

    public void setNetworkingMode(String networkingMode) {
        this.networkingMode = networkingMode;
    }

    public String getProduceModel() {
        return produceModel;
    }

    public void setProduceModel(String produceModel) {
        this.produceModel = produceModel;
    }

    public String getProduceDescription() {
        return produceDescription;
    }

    public void setProduceDescription(String produceDescription) {
        this.produceDescription = produceDescription;
    }
}
