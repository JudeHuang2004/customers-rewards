package com.spectrum.gateway.processor;

import com.spectrum.gateway.bean.RewardPointsResponse;
import com.spectrum.gateway.bean.RewardsSummary;
import com.spectrum.gateway.bean.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
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
        Transaction tran1 = new Transaction();
        tran1.setCustomerID(customerID);
        tran1.setTranAmount(BigDecimal.valueOf(56.50));
        tran1.setTranDate("2020-04-14 19:52:13Z");
        Transaction tran2 = new Transaction();
        tran2.setCustomerID(customerID);
        tran2.setTranAmount(BigDecimal.valueOf(78.20));
        tran2.setTranDate("2020-04-13 19:52:13Z");
        Transaction tran3 = new Transaction();
        tran3.setCustomerID(customerID);
        tran3.setTranAmount(BigDecimal.valueOf(120.00));
        tran3.setTranDate("2020-04-12 19:52:13Z");
        tranlist.add(tran1);
        tranlist.add(tran2);
        tranlist.add(tran3);
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
        String testkey = customerID+"|"+"2020-04";
        assertTrue(monthlyMap.containsKey(testkey));
        assertTrue(monthlyMap.get(testkey)==124);
    }

    @Test
    public void testConstructRewardsList() {
        Map<String, Long> monthlyMap = RewardsProcessor.calculateMonthlyRewards(tranlist);
        Collection<RewardPointsResponse> responselist = RewardsProcessor.constructRewardsList(monthlyMap);
        assertTrue(responselist.size()==1);
        RewardPointsResponse rpr = (RewardPointsResponse)responselist.toArray()[0];
        assertEquals(rpr.getCustomerID(), this.customerID);
        assertTrue(rpr.getTotalPoints()==124);
        assertTrue(rpr.getSummarylist().size()==1);
        RewardsSummary summary = rpr.getSummarylist().get(0);
        assertEquals(summary.getRewardsMonth(), "2020-04");
        assertEquals(summary.getMonthlyPoints(), 124);
    }

}
