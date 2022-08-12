package com.dahuatech.test.jmeter.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class MyCalFunc extends AbstractFunction {

    private static final String KEY = "__DH_CalFunc";
    private static final List<String> params = new LinkedList<String>();
    private static final int MAX = 3;
    static {
        params.add("请输入计算类型：add 或者 minus");
        params.add("请输入数字1：");
        params.add("请输入数字2：");
    }
    private Object[] values;
    private String type = null;
    private int n1 = 0;
    private int n2 = 0;

    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        int result = 0;
        if (type.equals("add")){
            result = n1+n2;
        }
        if(type.equals("minus")){
            result = n1-n2;
        }
        return String.valueOf(result);
    }

    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {

        checkParameterCount(collection,MAX);
        values = collection.toArray();
        type = ((CompoundVariable)values[0]).execute();

        n1 = Integer.parseInt(((CompoundVariable)values[1]).execute());
        n2 = Integer.parseInt(((CompoundVariable)values[2]).execute());
    }

    public String getReferenceKey() {
        return KEY;
    }

    public List<String> getArgumentDesc() {
        return params;
    }
}
