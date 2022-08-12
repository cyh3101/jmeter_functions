package com.dahuatech.test.jmeter.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class MyRandomFunc extends AbstractFunction{
    private static final List<String> desc = new LinkedList<String>();
    static {
        desc.add("Get a random int within specified parameter value.");
    }
    private static final String KEY = "__DH_RandomFunc";

    private static final int MAX_PARA_COUNT =1;
    private static final int MIN_PARA_COUNT = 1;

    private Object[] values;
    private Random r = new Random();

    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        try {
            int max = new Integer(((CompoundVariable)values[0]).execute().trim());
            int val = r.nextInt(max);
            return String.valueOf(val);
        } catch (Exception e) {
            throw new InvalidVariableException(e);
        }
    }

    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {
        checkParameterCount(collection,MIN_PARA_COUNT,MAX_PARA_COUNT);
        values = collection.toArray();
    }

    public String getReferenceKey() {
        return KEY;
    }

    public List<String> getArgumentDesc() {
        return desc;
    }
}
