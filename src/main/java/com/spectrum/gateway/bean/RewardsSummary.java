package com.spectrum.gateway.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * Monthly reward points per customer - part of response
 */
public class RewardsSummary {

    @ApiModelProperty(readOnly =true)
    private String rewardsMonth;

    @ApiModelProperty(readOnly =true)
    private long monthlyPoints;


    public String getRewardsMonth() {
        return rewardsMonth;
    }

    public void setRewardsMonth(String rewardsMonth) {
        this.rewardsMonth = rewardsMonth;
    }

    public long getMonthlyPoints() {
        return monthlyPoints;
    }

    public void setMonthlyPoints(long monthlyPoints) {
        this.monthlyPoints = monthlyPoints;
    }
}
