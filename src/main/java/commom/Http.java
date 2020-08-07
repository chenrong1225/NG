package commom;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;



public class Http {

	public void doget(String url) throws ClientProtocolException, IOException
	{
		HttpClientBuilder clientBuilder=HttpClientBuilder.create();
		CloseableHttpClient client=clientBuilder.build();
		
		HttpUriRequest httpget=new HttpGet(url);
		CloseableHttpResponse response=client.execute(httpget);
		HttpEntity entity=response.getEntity();
		
		if (entity!=null) {
			String ent=EntityUtils.toString(entity,"utf-8");
			System.out.println(ent);
		}
	}

	
	
	public JSONObject dopost(String url,HashMap<String, Object> map) throws ClientProtocolException, IOException, JSONException
	{
		HttpClientBuilder clientBuilder=HttpClientBuilder.create();
		CloseableHttpClient client=clientBuilder.build();
		
		JSONObject jsonObject = null;
		
		HttpPost httpPost= new HttpPost(url);
		StringEntity stringEntity=new StringEntity(map.toString());
		//stringEntity.setContentEncoding("UTF-8");
		//stringEntity.setContentEncoding("application/json;charset=UTF-8");
		stringEntity.setContentType("application/json;charset=UTF-8");
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if(entity!=null){
            //HttpResponse entityres;
            String  entityStr=EntityUtils.toString(entity,"utf-8");
			jsonObject=new JSONObject(entityStr);	
        }
        
        try {
    		client.close();
    		httpPost.clone();
    	} catch (Exception e) {
    		// TODO: handle exception
    		
    	}
        
		return jsonObject;
       
	}
	
	
	
	public static String doPost(String url, Map<String, Object> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, (String) param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }
	
	
	
	public JSONObject dopoststring(String url,String request) throws ClientProtocolException, IOException, JSONException
	{
		HttpClientBuilder clientBuilder=HttpClientBuilder.create();
		CloseableHttpClient client=clientBuilder.build();
		
		JSONObject jsonObject = null;
		
		HttpPost httpPost= new HttpPost(url);
		StringEntity stringEntity=new StringEntity(request);
		stringEntity.setContentEncoding("UTF-8");
		stringEntity.setContentEncoding("application/json;charset=UTF-8");
		stringEntity.setContentType("application/x-www-form-urlencoded");
        httpPost.setEntity(stringEntity);
        CloseableHttpResponse response = client.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if(entity!=null){
            //HttpResponse entityres;
            String  entityStr=EntityUtils.toString(entity,"utf-8");
			jsonObject=new JSONObject(entityStr);	
        }
        
        try {
    		client.close();
    		httpPost.clone();
    	} catch (Exception e) {
    		// TODO: handle exception
    		
    	}
        
		return jsonObject;
       
	}
	
	





}
