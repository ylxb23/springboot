package org.zero.boot.learn.sentinel;

import com.alibaba.csp.sentinel.slots.block.AbstractRule;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

public class Rules {
    public static final String RESOURCE_A001 = "A001";
    public static final String RESOURCE_A002 = "A002";
    public static final String RESOURCE_A003 = "A003";


    public static void initRules() {
        List<FlowRule> flowRules = new ArrayList<>();
        // 规则1：QPS限流，限制为 3q/s
        FlowRule r1 = new FlowRule();
        r1.setResource(RESOURCE_A001);
        r1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        r1.setCount(3);
        flowRules.add(r1);
        //
        List<DegradeRule> degradeRules = new ArrayList<>();
        DegradeRule r2 = new DegradeRule();
        r2.setResource(RESOURCE_A002);
        r2.setCount(1);
        r2.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        r2.setMinRequestAmount(3);
        r2.setStatIntervalMs(3000);
        r2.setTimeWindow(60);
        degradeRules.add(r2);

        DegradeRule r3 = new DegradeRule();
        r3.setResource(RESOURCE_A003);
        r3.setCount(30);
        r3.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        r3.setMinRequestAmount(5);
        r3.setStatIntervalMs(3000);
        r3.setTimeWindow(30);
        r3.setSlowRatioThreshold(0.1);

        FlowRuleManager.loadRules(flowRules);
        DegradeRuleManager.loadRules(degradeRules);
    }
}
