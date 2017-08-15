package com.cjl.ljz.basedemo.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class MessageUtils {
	private final static Integer NUM=6; 
    private static Logger log = Logger.getLogger(MessageUtils.class);
    private static String appkey="24582511";
    private static String secret="c7a92a97bd35b29b0b6492dfeb08a2c9";
	public static Map<String, Object> sendMessage(String mobile,Map<String, Object> param,String freeSignName,String templateCode){
		  //官网的URL---必须是这个
		String url="http://gw.api.taobao.com/router/rest";
       //	String url="http://gw.api.tbsandbox.com/router/rest";
        //短信模板的内容
        String json = mapTransForString(param);
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        //需要log4j的Jar包--日志-可删
        log.info("手机号为:"+mobile+",验证码为:"+param);
        Map<String, Object> returnObjMap=new HashMap<String, Object>();
        returnObjMap.put("messageCode", param.get("code"));
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        //公共回传参数，在“消息返回”中会透传回该参数；
        //举例：用户可以传入自己下级的会员ID，在消息返回时，该会员ID会包含在内，用户可以根据该会员ID识别是哪位会员使用了你的应用
        req.setExtend("");  
        //短信类型，传入值请填写normal
        req.setSmsType("normal");

        //签名名称
        req.setSmsFreeSignName(freeSignName);

        //短信模板变量，传参规则{"key":"value"}，key的名字须和申请模板中的变量名一致，多个变量之间以逗号隔开。
		
        req.setSmsParamString(json);

        //短信接收号码
        req.setRecNum(mobile);

        //短信模板ID
        req.setSmsTemplateCode(templateCode);
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
        	returnObjMap.put("code", false);
            log.error("短信发送失败", e);
        }
        String errorCode = rsp.getErrorCode();
        returnObjMap.put("errorCode", errorCode);
        System.out.println(errorCode);
		return returnObjMap;

    }
	public static String mapTransForString(Map<String, Object> map){
		String replaceAll = map.toString().replaceAll("[{]", "{\"");
		replaceAll = replaceAll.replaceAll("[}]", "\"}");
		replaceAll =replaceAll.replaceAll("=", "\"=\"");
		replaceAll =replaceAll.replaceAll(",", "\",\"");
		replaceAll = replaceAll.replaceAll("\\s*", "");
		return replaceAll;
	}
	public static String getUUid(){
		 //随机生成 num 位验证码
        String code="";
        Random r = new Random(new Date().getTime());
        for(int i=0;i<NUM;i++){
            code = code+r.nextInt(10);
        }
        return code;
	}
	public static void main(String[] args) {
		    Map<String, Object> map=new HashMap<String, Object>();
			map.put("code", "王瑞是逗逼");
			Map<String, Object> sendMessage = sendMessage("15553241040",map,"龙鹏天下","SMS_85480015");
			System.out.println(sendMessage.toString());
	}
}
