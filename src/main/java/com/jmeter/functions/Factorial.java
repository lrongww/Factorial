package com.jmeter.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.*;

/**
 * @Author: Rong.Li
 * @Date: 2019/8/6 11:22
 * @Description:计算阶乘
 */
public class Factorial extends AbstractFunction {
    private static final List<String> desc = new LinkedList<String>();
    private static final String KEY = "__factorial";
    private Object[] values = null;


    static {
        desc.add("factorial_value");
    }

//实现对该函数的执行并返回结果
    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        String numberString = ((CompoundVariable) values[0]).execute().trim();
        int num;
        try{
            num = Integer.valueOf(numberString);
        }catch (Exception e){
            return null;
        }
        return String.valueOf(factorial(num));
    }
//对函数的参数进行检查和设置
    @Override
    public void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {
         checkMinParameterCount(parameters,1);
         checkParameterCount(parameters,1,1);
         values = parameters.toArray();
    }
//告诉JMeter该函数在框架中的引用名称
    @Override
    public String getReferenceKey() {
        return KEY;
    }

//实现对函数参数的描述
    @Override
    public List<String> getArgumentDesc() {
        return desc;
    }

    private int factorial(int num){
        int result = 1;
        if (num<0){
            return -1;
        }else{
            for(int i=num;i>0;i--){
                result*=i;
            }
        }
        return result;
    }
}
