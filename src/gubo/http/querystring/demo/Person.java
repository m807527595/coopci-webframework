package gubo.http.querystring.demo;

import gubo.http.querystring.QueryStringField;

import java.util.Date;

public class Person {
	
	
	@QueryStringField()
	public String name;
	
	
	@QueryStringField(
			// deserializer=LongFieldParser.class, 
			ignoreMalFormat=false,
			name="register_time"
			)
	public Date registerTime;
	
	@QueryStringField(
			//deserializer=LongFieldParser.class
			)
	public long age;
	
	@QueryStringField(
			// ignoreMalFormat=false
			)
	public boolean isVIP;
	
	@QueryStringField()
	public double height;
	
	
	@QueryStringField(
			deserializer=RomanIntegerFieldParser.class
			)
	public int tier;
}
