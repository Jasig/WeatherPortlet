/**
 * Licensed to Apereo under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Apereo licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.portlet.weather.dao.yahoo;

import org.jasig.portlet.weather.domain.Location;
import org.jasig.portlet.weather.domain.Weather;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/testContext.xml")
public class TestYahooWeatherDaoImpl {

    YahooWeatherDaoImpl dao;

    @Autowired(required = true)
    private IYahooWeatherParsingService weatherParsingService;
    
    @Autowired(required = true)
    private IYahooLocationParsingService locationParsingService;
    
    @Autowired(required = true)
    private ApplicationContext applicationContext;
    
    @Test
    public void test() throws Exception {
        InputStream is = applicationContext.getResource("classpath:/2502265YQL.xml").getInputStream();
        Weather weather = weatherParsingService.parseWeather(is);
        Assert.assertEquals(weather.getForecast().size(), 10);
        Assert.assertEquals(weather.getCurrentWeather().getCondition(), "Mostly Sunny");
    }
    
    @Test
    public void testSearch() throws Exception {
        InputStream is = applicationContext.getResource("classpath:/yahooLondonSearchYQL.xml").getInputStream();
        List<Location> locations = locationParsingService.parseLocations(is);
        Assert.assertEquals(locations.size(), 10);
    }

}
