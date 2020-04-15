package com.spectrum.gateway.processor;

import com.spectrum.gateway.bean.RewardPointsResponse;
import com.spectrum.gateway.bean.RewardsSummary;
import com.spectrum.gateway.bean.Transaction;

import java.math.BigDecimal;
import java.util.*;

public class RewardsProcessor {

    /**
     *
     * @param tranlist
     * @return
     */
    public static Collection<RewardPointsResponse> process(List<Transaction> tranlist) {
        Map<String, Long> monthlyMap = calculateMonthlyRewards(tranlist);
        Collection<RewardPointsResponse> rprs = constructRewardsList(monthlyMap);
        return rprs;
    }

    /**
     *
     * @param tranAmount
     * @return
     */
    public static long calculateSingleTransaction(BigDecimal tranAmount) {
        /*
         * A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.
         * A customer receives 2 points for every dollar spent over $100 in each transaction,
         * plus 1 point for every dollar spent over $50 in each transaction
         * (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
         * Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.
         * Make up a data set to best demonstrate your solution
         * Check solution into GitHub
         *
         */
        long tranPoints = 0;
        long tranAmountLong = tranAmount.longValue();
        if(tranAmountLong > 100) {
            // $120 purchase = 2x$20 + 1x$50 = 90 points
            tranPoints = 2 * (tranAmountLong - 100) + 50;
        }else if(tranAmountLong > 50) {
            tranPoints = tranAmountLong - 50;
        }
        return tranPoints;
    }

    /**
     *
     * @param tranlist
     * @return
     */
    public static Map<String, Long> calculateMonthlyRewards(List<Transaction> tranlist) {
        //key: 8e249dcb-b75a-4e42-af04-0615a29c52aa+"|"+"2020-04" value: points per month
        Map<String, Long> monthlyMap = new HashMap<>();
        for(Transaction tran: tranlist) {
            /*
             * TODO: to keep it simple, assume all the incoming data are valid and well formatted,
             * in production environment, we may need to verify, clean and transform incoming data.
             */
            String tranKey = tran.getCustomerID()+"|"+tran.getTranDate().substring(0, 7);
            if(monthlyMap.containsKey(tranKey)) {
                long lastPoints = monthlyMap.get(tranKey);
                monthlyMap.put(tranKey, lastPoints+calculateSingleTransaction(tran.getTranAmount()));
            }else {
                monthlyMap.put(tranKey, calculateSingleTransaction(tran.getTranAmount()));
            }
        }
        return monthlyMap;
    }

    /**
     *
     * @param monthlyMap
     * @return
     */
    public static Collection<RewardPointsResponse> constructRewardsList(Map<String, Long> monthlyMap) {
        Map<String, RewardPointsResponse> rprMap = new HashMap<>();
        monthlyMap.forEach(
                (key, value) ->{
                    String customerID = key.substring(0, key.indexOf("|"));
                    String rewardsMonth = key.substring(key.indexOf("|")+1);
                    RewardsSummary summary = new RewardsSummary();
                    summary.setRewardsMonth(rewardsMonth);
                    summary.setMonthlyPoints(value);
                    if(rprMap.containsKey(customerID)) {
                        RewardPointsResponse rpr = rprMap.get(customerID);
                        // add the monthly points to total points
                        long lastPoints = rpr.getTotalPoints();
                        rpr.setTotalPoints(lastPoints+value);
                        rpr.getSummarylist().add(summary);
                    }else {
                        // initialize a new RewardPointsResponse with the same customerID
                        RewardPointsResponse rpr = new RewardPointsResponse();
                        rpr.setCustomerID(customerID);
                        rpr.setTotalPoints(value);
                        List<RewardsSummary> summarylist = new ArrayList<RewardsSummary>();
                        summarylist.add(summary);
                        rpr.setSummarylist(summarylist);
                        // use RewardPointsResponse customerID as key
                        rprMap.put(customerID, rpr);
                    }
                }
        );
        return rprMap.values();
    }

}
