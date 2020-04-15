package com.spectrum.gateway.bean;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 * Customer reward points response pojo - response
 */
public class RewardPointsResponse {

    @ApiModelProperty(readOnly =true)
    private String customerID;

    @ApiModelProperty(readOnly =true)
    private long totalPoints;

    @ApiModelProperty(readOnly =true)
    private List<RewardsSummary> summarylist;

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public long getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(long totalPoints) {
        this.totalPoints = totalPoints;
    }

    public List<RewardsSummary> getSummarylist() {
        return summarylist;
    }

    public void setSummarylist(List<RewardsSummary> summarylist) {
        this.summarylist = summarylist;
    }
}
