package com.demo.tests;

import com.demo.config.BasicTestConfig;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.demo.properties.TestData.fileName;
import static com.demo.scripts.api.convertor.CurrencyRates.*;


public class TestCase_05 extends BasicTestConfig {


    @Test
    public void convert(Method method) throws Exception {
        String convertTo     = "BGN";
        String convertFrom   = "EUR";
        String convertAmount = "5";
        fileName = method.getName() + ".json";
        getRates(fileName, convertTo, convertFrom, convertAmount);
    }
}