package com.spectrum.gateway.bean;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Purchase transaction pojo - request data
 */
public class Transaction {

    @ApiModelProperty(required =true)
    private String customerID; // e.g. UUID

    @ApiModelProperty(required =true)
    private String tranID; // e.g. UUID

    @ApiModelProperty(required =true)
    private BigDecimal tranAmount;

    @ApiModelProperty(required =true)
    private String tranDate; // expected format: "yyyy-MM-dd'T'HH:mm:ss'Z'", Timezone: "UTC"

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getTranID() {
        return tranID;
    }

    public void setTranID(String tranID) {
        this.tranID = tranID;
    }

    public BigDecimal getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(BigDecimal tranAmount) {
        this.tranAmount = tranAmount;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }

}
