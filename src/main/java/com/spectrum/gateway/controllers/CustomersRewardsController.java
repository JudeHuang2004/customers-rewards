package com.spectrum.gateway.controllers;

import com.spectrum.gateway.bean.RewardPointsResponse;
import com.spectrum.gateway.bean.Transaction;
import com.spectrum.gateway.processor.RewardsProcessor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/v1/customers")
@Api(value = "customers", description = "Rest API for customers rewards", tags = "Customers Rewards API")
public class CustomersRewardsController {

    @RequestMapping(value = "/rewardpoints", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Display reward points for each customer per month and total", response = Collection.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Error")
    }
    )
    public Collection<RewardPointsResponse> calcualteRewardPoints(@RequestBody List<Transaction> tranList) {
        Collection<RewardPointsResponse> responselist =  RewardsProcessor.process(tranList);
        return responselist;
    }

    //TODO "/rewardpoints/{customerID}"

}