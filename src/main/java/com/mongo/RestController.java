package com.mongo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mongo.users.User;
import com.mongo.users.UserDao;

@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	UserDao udao;
	
	@RequestMapping(value="/getallusers",method=RequestMethod.POST)
	public String getallusers()
	{			

		JSONArray jarr = new JSONArray();		
		for( User u : udao.findAll() )
		{
				JSONObject jobj = new JSONObject();				
				jobj.put("Fname",u.getFirstName());	
				jobj.put("Lname",u.getLastName());
				jobj.put("Id",u.getId());
				jarr.add(jobj);		
		}			
		return jarr.toString();
	}
	
	@RequestMapping(value="/getuser",method=RequestMethod.POST)
	public String getuser(@RequestBody String data) 
	{		
		JSONObject jobj = new JSONObject();	
		try {				
			JSONParser jp = new JSONParser();
			JSONObject id = (JSONObject)jp.parse(data);		
			User u = udao.find(id.get("token").toString());
			jobj.put("Fname", u.getFirstName());
			jobj.put("Lname", u.getLastName());
			jobj.put("Id",u.getId());			
		}
		catch (Exception e) {
			
		}
		return jobj.toString();
	}
	
	
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public String addUser( @RequestBody String data  )
	{		
		JSONObject json = new JSONObject();	
		try
		{		
			JSONParser jp = new JSONParser();		
			JSONObject joObject = (JSONObject)jp.parse(data);	
			User u = new User();		
			u.setFirstName(joObject.get("Fname").toString());
			u.setLastName(joObject.get("Lname").toString());
			udao.create(u);
			json.put("msg", "done");
		}
		catch( Exception e )
		{
			json.put("msg", "fail");
		}
		return json.toString();
	}
	
	@RequestMapping(value = "/UpdateUser",method=RequestMethod.POST)
	public String UpdateUser(@RequestBody String data) {
		JSONObject json = new JSONObject();
		try {
			JSONParser jp = new JSONParser();
			JSONObject id = (JSONObject)jp.parse(data);		
			User u = udao.find(id.get("Id").toString());
			u.setFirstName(id.get("Fname").toString());
			u.setLastName(id.get("Lname").toString());
			udao.update(u);
			json.put("msg", "Update");
		}
		catch (Exception e) {
			json.put("msg", "failed");
		}
		return json.toString();
	}
		
	@RequestMapping(value="/del",method=RequestMethod.POST)
	public String del(@RequestBody String data) {
		JSONObject json = new JSONObject();
		try {
			JSONParser jp = new JSONParser();
			JSONObject jobj = (JSONObject)jp.parse(data);
			User u = new User();
			u.setId(jobj.get("id").toString());
			udao.delete(u);
			json.put("msg", "deleted");
		}
		catch (Exception e) {
			json.put("msg", "failed");
		}
		return json.toString();
	}
	
	
	@RequestMapping(value="/deleteall",method=RequestMethod.POST)
	public String deleteall() {
		JSONObject json = new JSONObject();
		try {
			udao.deleteAll();
			json.put("msg", "deleted");
		}
		catch (Exception e) {
			json.put("msg", "failed");
		}
		return json.toString();
	}
	
	
	@RequestMapping(value="/hello",method=RequestMethod.POST)
	public String hello()
	{		
		JSONObject json = new JSONObject();	
		try
		{		
			json.put("msg", "Hello");
		}
		catch( Exception e )
		{
			json.put("msg", "fail");
		}
		return json.toString();
	}
}
