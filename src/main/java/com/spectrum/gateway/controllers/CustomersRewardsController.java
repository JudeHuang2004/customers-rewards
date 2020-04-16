package com.spectrum.gateway.controllers;

import com.spectrum.gateway.bean.RewardPointsResponse;
import com.spectrum.gateway.processor.RewardsProcessor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/v1/customers")
@Api(value = "customers", description = "Rest API for customers rewards", tags = "Customers Rewards API")
public class CustomersRewardsController {

    @RequestMapping(value = "/{customerID}/rewardpoints", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Display reward points for expected customer per month and total", response = Collection.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "The resource not found"),
            @ApiResponse(code = 500, message = "Internal Error")
    }
    )
    public RewardPointsResponse queryCustomerRewardPoints(@PathVariable String customerID) {
        RewardPointsResponse response =  RewardsProcessor.querySingleCustomer(customerID);
        return response;
    }

}