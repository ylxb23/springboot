package org.zero.boot.learn.sentinel;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

public class Rules {
    public static final String RESOURCE_A001 = "A001";


    public static void initRules() {
        List<FlowRule> rules = new ArrayList<>();
        // 规则1：QPS限流，限制为 3q/s
        FlowRule r1 = new FlowRule();
        r1.setResource(RESOURCE_A001);
        r1.setGrade(RuleConstant.FLOW_GRADE_QPS);
        r1.setCount(3);
        rules.add(r1);


        FlowRuleManager.loadRules(rules);
    }
}
