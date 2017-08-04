package com.ct.caipiao.test.lottery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.CronExpression;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  
public class CronTest {

	
	@Test
	public void cronTest() {  
        try {  
            CronExpression exp = new CronExpression("1 0/30 * * * ?");  
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            Date d = new Date();  
            int i = 0;  
            // 循环得到接下来n此的触发时间点，供验证  
            while (i < 10) {  
                d = exp.getNextValidTimeAfter(d);  
                System.out.println(df.format(d));  
                ++i;  
            }  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
    }  
}
