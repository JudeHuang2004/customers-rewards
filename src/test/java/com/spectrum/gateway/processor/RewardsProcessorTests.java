package com.spectrum.gateway.processor;

import com.spectrum.gateway.bean.RewardPointsResponse;
import com.spectrum.gateway.bean.RewardsSummary;
import com.spectrum.gateway.bean.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@SpringBootTest
public class RewardsProcessorTests {

    private List<Transaction> tranlist = new ArrayList<>();
    private String customerID = "8e249dcb-b75a-4e42-af04-0615a29c52aa";

    @Before
    public void setTestData() {
        tranlist = RewardsProcessor.generateTestTransactions();
    }

    @Test
    public void testGenerateTestTransactions() {
        List<Transaction> tranlist = RewardsProcessor.generateTestTransactions();
        assertEquals(tranlist.size(), 8);
    }

    @Test
    public void testCalculateSingleTransaction() {
        assertEquals(RewardsProcessor.calculateSingleTransaction(BigDecimal.valueOf(120.00)), 90);
        assertEquals(RewardsProcessor.calculateSingleTransaction(BigDecimal.valueOf(86.54)), 36);
        assertEquals(RewardsProcessor.calculateSingleTransaction(BigDecimal.valueOf(50.00)), 0);
    }

    @Test
    public void testCalculateMonthlyRewards() {
        Map<String, Long> monthlyMap = RewardsProcessor.calculateMonthlyRewards(tranlist);
        String testKey = customerID+"|"+"2020-04";
        assertTrue(monthlyMap.containsKey(testKey));
        assertTrue(monthlyMap.get(testKey)==124);
    }

    @Test
    public void testCreateCustomerRewardsMap() {
        Map<String, Long> monthlyMap = RewardsProcessor.calculateMonthlyRewards(tranlist);
        Map<String, RewardPointsResponse> rprMap = RewardsProcessor.createCustomerRewardsMap(monthlyMap);
        assertTrue(rprMap.size()==2);
        RewardPointsResponse rpr = rprMap.get(this.customerID);
        assertEquals(rpr.getCustomerID(), this.customerID);
    }

    @Test
    public void testQuerySingleCustomer() {
        RewardPointsResponse response = RewardsProcessor.querySingleCustomer(this.customerID);
        assertTrue(response.getTotalPoints()==226);
        assertTrue(response.getSummarylist().size()==3);
        RewardsSummary summary = response.getSummarylist().get(0);
        assertEquals(summary.getRewardsMonth(), "2020-04");
        assertEquals(summary.getMonthlyPoints(), 124);
    }

}
