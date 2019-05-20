package me.biezhi.weixin;

import java.awt.EventQueue;
import java.io.File;
import java.util.*;
import javax.swing.UIManager;
import blade.kit.DateKit;
import blade.kit.StringKit;
import blade.kit.http.HttpRequest;
import blade.kit.json.JSON;
import blade.kit.json.JSONArray;
import blade.kit.json.JSONObject;
import blade.kit.logging.Logger;
import blade.kit.logging.LoggerFactory;
import me.biezhi.weixin.util.CookieUtil;
import me.biezhi.weixin.util.Matchers;

/**
 * Hello world!
 *
 */
public class App {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
	
	private String uuid;
	private int tip = 0;
	private String base_uri, redirect_uri, webpush_url = "https://webpush2.weixin.qq.com/cgi-bin/mmwebwx-bin";
	
	private String skey, synckey, wxsid, wxuin, pass_ticket, deviceId = "e" + DateKit.getCurrentUnixTime();
	
	private String cookie;
	private QRCodeFrame qrCodeFrame;
	
	private JSONObject SyncKey, User, BaseRequest;
	
	// 微信联系人列表，可聊天的联系人列表
	private JSONArray MemberList, ContactList;
	
	// 微信特殊账号
	private List<String> SpecialUsers = Arrays.asList("newsapp", "fmessage", "filehelper", "weibo", "qqmail", "fmessage", "tmessage", "qmessage", "qqsync", "floatbottle", "lbsapp", "shakeapp", "medianote", "qqfriend", "readerapp", "blogapp", "facebookapp", "masssendapp", "meishiapp", "feedsapp", "voip", "blogappweixin", "weixin", "brandsessionholder", "weixinreminder", "wxid_novlwrv3lqwv11", "gh_22b87fa7cb3c", "officialaccounts", "notification_messages", "wxid_novlwrv3lqwv11", "gh_22b87fa7cb3c", "wxitil", "userexperience_alarm", "notification_messages");
	
	public App() {
		System.setProperty("jsse.enableSNIExtension", "false");
	}
	
	/**
	 * 获取UUID
	 * @return
	 */
	public String getUUID() {
		String url = "https://login.weixin.qq.com/jslogin";
		HttpRequest request = HttpRequest.get(url, true, 
				"appid", "wx782c26e4c19acffb", 
				"fun", "new",
				"lang", "zh_CN",
				"_" , DateKit.getCurrentUnixTime());
		
		LOGGER.info("[*] " + request);
		
		String res = request.body();
		request.disconnect();
		
		if(StringKit.isNotBlank(res)){
			String code = Matchers.match("window.QRLogin.code = (\\d+);", res);
			if(null != code){
				if(code.equals("200")){
					this.uuid = Matchers.match("window.QRLogin.uuid = \"(.*)\";", res);
					return this.uuid;
				} else {
					LOGGER.info("[*] 错误的状态码: %s", code);
				}
			}
		}
		return null;
	}
	
	/**
	 * 显示二维码
	 * @return
	 */
	public void showQrCode() {
		
		String url = "https://login.weixin.qq.com/qrcode/" + this.uuid;
		
		final File output = new File("temp.jpg");
		
		HttpRequest.post(url, true, 
				"t", "webwx", 
				"_" , DateKit.getCurrentUnixTime())
				.receive(output);
		
		if(null != output && output.exists() && output.isFile()){
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
						qrCodeFrame = new QRCodeFrame(output.getPath());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	/**
	 * 等待登录
	 */
	public String waitForLogin(){
		this.tip = 1;
		String url = "https://login.weixin.qq.com/cgi-bin/mmwebwx-bin/login";
		HttpRequest request = HttpRequest.get(url, true, 
				"tip", this.tip, 
				"uuid", this.uuid,
				"_" , DateKit.getCurrentUnixTime());
		
		LOGGER.info("[*] " + request.toString());
		
		String res = request.body();
		request.disconnect();
		
		if(null == res){
			LOGGER.info("[*] 扫描二维码验证失败");
			return "";
		}
		
		String code = Matchers.match("window.code=(\\d+);", res);
		if(null == code){
			LOGGER.info("[*] 扫描二维码验证失败");
			return "";
		} else {
			if(code.equals("201")){
				LOGGER.info("[*] 成功扫描,请在手机上点击确认以登录");
				tip = 0;
			} else if(code.equals("200")){
				LOGGER.info("[*] 正在登录...");
				String pm = Matchers.match("window.redirect_uri=\"(\\S+?)\";", res);
				this.redirect_uri = pm + "&fun=new";
				LOGGER.info("[*] redirect_uri=%s", this.redirect_uri);
				this.base_uri = this.redirect_uri.substring(0, this.redirect_uri.lastIndexOf("/"));
				LOGGER.info("[*] base_uri=%s", this.base_uri);
			} else if(code.equals("408")){
				LOGGER.info("[*] 登录超时");
			} else {
				LOGGER.info("[*] 扫描code=%s", code);
			}
		}
		return code;
	}
	
	private void closeQrWindow() {
		qrCodeFrame.dispose();
	}
	
	/**
	 * 登录
	 */
	public boolean login(){
		
		HttpRequest request = HttpRequest.get(this.redirect_uri);
		
		LOGGER.info("[*] " + request);
		
		String res = request.body();
		this.cookie = CookieUtil.getCookie(request);
		
		request.disconnect();
		
		if(StringKit.isBlank(res)){
			return false;
		}
		
		this.skey = Matchers.match("<skey>(\\S+)</skey>", res);
		this.wxsid = Matchers.match("<wxsid>(\\S+)</wxsid>", res);
		this.wxuin = Matchers.match("<wxuin>(\\S+)</wxuin>", res);
		this.pass_ticket = Matchers.match("<pass_ticket>(\\S+)</pass_ticket>", res);
		
		LOGGER.info("[*] skey[%s]", this.skey);
		LOGGER.info("[*] wxsid[%s]", this.wxsid);
		LOGGER.info("[*] wxuin[%s]", this.wxuin);
		LOGGER.info("[*] pass_ticket[%s]", this.pass_ticket);
		
		this.BaseRequest = new JSONObject();
		BaseRequest.put("Uin", this.wxuin);
		BaseRequest.put("Sid", this.wxsid);
		BaseRequest.put("Skey", this.skey);
		BaseRequest.put("DeviceID", this.deviceId);
		
		return true;
	}
	
	/**
	 * 微信初始化
	 */
	public boolean wxInit(){
		
		String url = this.base_uri + "/webwxinit?r=" + DateKit.getCurrentUnixTime() + "&pass_ticket=" + this.pass_ticket +
				"&skey=" + this.skey;
		
		JSONObject body = new JSONObject();
		body.put("BaseRequest", this.BaseRequest);
		
		HttpRequest request = HttpRequest.post(url)
				.header("Content-Type", "application/json;charset=utf-8")
				.header("Cookie", this.cookie)
				.send(body.toString());
		
		LOGGER.info("[*] " + request);
		String res = request.body();
		request.disconnect();
		
		if(StringKit.isBlank(res)){
			return false;
		}
		
		try {
			JSONObject jsonObject = JSON.parse(res).asObject();
			if(null != jsonObject){
				JSONObject BaseResponse = jsonObject.getJSONObject("BaseResponse");
				if(null != BaseResponse){
					int ret = BaseResponse.getInt("Ret", -1);
					if(ret == 0){
						this.SyncKey = jsonObject.getJSONObject("SyncKey");
						this.User = jsonObject.getJSONObject("User");
						
						StringBuffer synckey = new StringBuffer();
						
						JSONArray list = SyncKey.getJSONArray("List");
						for(int i=0, len=list.size(); i<len; i++){
							JSONObject item = list.getJSONObject(i);
							synckey.append("|" + item.getInt("Key", 0) + "_" + item.getInt("Val", 0));
						}
						
						this.synckey = synckey.substring(1);
						
						return true;
					}
				}
			}
		} catch (Exception e) {
		}
		return false;
	}
	
	/**
	 * 微信状态通知
	 */
	public boolean wxStatusNotify (){
		
		String url = this.base_uri + "/webwxstatusnotify?lang=zh_CN&pass_ticket=" + this.pass_ticket;
		
		JSONObject body = new JSONObject();
		body.put("BaseRequest", BaseRequest);
		body.put("Code", 3);
		body.put("FromUserName", this.User.getString("UserName"));
		body.put("ToUserName", this.User.getString("UserName"));
		body.put("ClientMsgId", DateKit.getCurrentUnixTime());
		
		HttpRequest request = HttpRequest.post(url)
				.header("Content-Type", "application/json;charset=utf-8")
				.header("Cookie", this.cookie)
				.send(body.toString());
		
		LOGGER.info("[*] " + request);
		String res = request.body();
		request.disconnect();
		
		if(StringKit.isBlank(res)){
			return false;
		}
		
		try {
			JSONObject jsonObject = JSON.parse(res).asObject();
			JSONObject BaseResponse = jsonObject.getJSONObject("BaseResponse");
			if(null != BaseResponse){
				int ret = BaseResponse.getInt("Ret", -1);
				return ret == 0;
			}
		} catch (Exception e) {
		}
		return false;
	}
	
	/**
	 * 获取联系人
	 */
	public boolean getContact(){
		
		String url = this.base_uri + "/webwxgetcontact?pass_ticket=" + this.pass_ticket + "&skey=" + this.skey + "&r=" + DateKit.getCurrentUnixTime();
		
		JSONObject body = new JSONObject();
		body.put("BaseRequest", BaseRequest);
		
		HttpRequest request = HttpRequest.post(url)
				.header("Content-Type", "application/json;charset=utf-8")
				.header("Cookie", this.cookie)
				.send(body.toString());
		
		LOGGER.info("[*] " + request);
		String res = request.body();
		request.disconnect();
		
		if(StringKit.isBlank(res)){
			return false;
		}
		
		try {
			JSONObject jsonObject = JSON.parse(res).asObject();
			JSONObject BaseResponse = jsonObject.getJSONObject("BaseResponse");
			if(null != BaseResponse){
				int ret = BaseResponse.getInt("Ret", -1);
				if(ret == 0){
					this.MemberList = jsonObject.getJSONArray("MemberList");
					this.ContactList = new JSONArray();
					if(null != MemberList){
						for(int i=0, len=MemberList.size(); i<len; i++){
							JSONObject contact = this.MemberList.getJSONObject(i);
							//公众号/服务号
							if(contact.getInt("VerifyFlag", 0) == 8){
								continue;
							}
							//特殊联系人
							if(SpecialUsers.contains(contact.getString("UserName"))){
								continue;
							}
							//群聊
							if(contact.getString("UserName").indexOf("@@") != -1){
								continue;
							}
							//自己
							if(contact.getString("UserName").equals(this.User.getString("UserName"))){
								continue;
							}
							ContactList.add(contact);

						}
						System.out.println(ContactList.toString());
						return true;
					}
				}
			}
		} catch (Exception e) {
		}
		return false;
	}
	
	/**
	 * 消息检查
	 */
	public int[] syncCheck(){
		
		int[] arr = new int[2];
		
		String url = this.webpush_url + "/synccheck";
		
		JSONObject body = new JSONObject();
		body.put("BaseRequest", BaseRequest);
		
		HttpRequest request = HttpRequest.get(url, true,
				"r", DateKit.getCurrentUnixTime() + StringKit.getRandomNumber(5),
				"skey", this.skey,
				"uin", this.wxuin,
				"sid", this.wxsid,
				"deviceid", this.deviceId,
				"synckey", this.synckey,
				"_", System.currentTimeMillis())
				.header("Cookie", this.cookie);
		
		LOGGER.info("[*] " + request);
		String res = request.body();
		request.disconnect();
		
		if(StringKit.isBlank(res)){
			return arr;
		}
		
		String retcode = Matchers.match("retcode:\"(\\d+)\",", res);
		String selector = Matchers.match("selector:\"(\\d+)\"}", res);
		if(null != retcode && null != selector){
			arr[0] = Integer.parseInt(retcode);
			arr[1] = Integer.parseInt(selector);
			return arr;
		}
		System.out.println("arr"+arr.toString());
		return arr;
	}
	
	private void webwxsendmsg(String content, String to) {
		
		String url = this.base_uri + "/webwxsendmsg?lang=zh_CN&pass_ticket=" + this.pass_ticket;
		
		JSONObject body = new JSONObject();
		
		String clientMsgId = DateKit.getCurrentUnixTime() + StringKit.getRandomNumber(5);
		JSONObject Msg = new JSONObject();
		Msg.put("Type", 1);
		Msg.put("Content", content);
		Msg.put("FromUserName", User.getString("UserName"));
		Msg.put("ToUserName", to);
		Msg.put("LocalID", clientMsgId);
		Msg.put("ClientMsgId", clientMsgId);
		
		body.put("BaseRequest", this.BaseRequest);
		body.put("Msg", Msg);
		
		HttpRequest request = HttpRequest.post(url)
				.header("Content-Type", "application/json;charset=utf-8")
				.header("Cookie", this.cookie)
				.send(body.toString());
		
		LOGGER.info("[*] " + request);
		request.body();
		request.disconnect();
	}
	
	/**
	 * 获取最新消息
	 */
	public JSONObject webwxsync(){
		
		String url = this.base_uri + "/webwxsync?lang=zh_CN&pass_ticket=" + this.pass_ticket
				 + "&skey=" + this.skey + "&sid=" + this.wxsid + "&r=" + DateKit.getCurrentUnixTime();
		System.out.println("url:"+url);
		JSONObject body = new JSONObject();
		body.put("BaseRequest", BaseRequest);
		body.put("SyncKey", this.SyncKey);
		body.put("rr", DateKit.getCurrentUnixTime());
		
		HttpRequest request = HttpRequest.post(url)
				.header("Content-Type", "application/json;charset=utf-8")
				.header("Cookie", this.cookie)
				.send(body.toString());
		System.out.println("body"+body.toString());
		LOGGER.info("[*] " + request);
		String res = request.body();
		request.disconnect();
		
		if(StringKit.isBlank(res)){
			return null;
		}
		
		JSONObject jsonObject = JSON.parse(res).asObject();
		JSONObject BaseResponse = jsonObject.getJSONObject("BaseResponse");
		System.out.println(BaseResponse);
		if(null != BaseResponse){
			int ret = BaseResponse.getInt("Ret", -1);
			if(ret == 0){
				this.SyncKey = jsonObject.getJSONObject("SyncKey");
				
				StringBuffer synckey = new StringBuffer();
				JSONArray list = SyncKey.getJSONArray("List");
				for(int i=0, len=list.size(); i<len; i++){
					JSONObject item = list.getJSONObject(i);
					synckey.append("|" + item.getInt("Key", 0) + "_" + item.getInt("Val", 0));
				}
				this.synckey = synckey.substring(1);
			}
		}
		return jsonObject;
	}
	
	/**
	 * 获取最新消息
	 */
	public void handleMsg(JSONObject data){
		if(null == data){
			return;
		}
		
		JSONArray AddMsgList = data.getJSONArray("AddMsgList");
		
		for(int i=0,len=AddMsgList.size(); i<len; i++){
			LOGGER.info("[*] 你有新的消息，请注意查收");
			JSONObject msg = AddMsgList.getJSONObject(i);
			int msgType = msg.getInt("MsgType", 0);
			String name = getUserRemarkName(msg.getString("FromUserName"));
			String content = msg.getString("Content");

			UUID uuid=new UUID(1,2);
		//	for (int i1=0; i1 < 100; i1++) {
			List list=new ArrayList();
			String s1="叫了个保洁来打扫房子，阿姨进门要穿鞋套。我赶紧说： 不用不用可以直接踩进来！保洁阿姨：不是，我怕把我鞋弄脏了";
			String s2="青年向大师哭诉：“大师，我高考失利，考不上大学，父母责骂我，女朋友也离我而去，请您收下我，让我皈依佛门吧！”只见大师拿出一叠高考资料，青年恍然大悟：“大师是叫我不要放弃高考，明年再战，是吗？”大师摇头说道：“施主，我们这里只招本科以上，你还是先回去考上本科再过来面试吧！”";
			String s3="你今天买不起新鲜水果，就攒钱过几天买，你图便宜买下的烂水果，吃起来时候根本就是负担和垃圾，绝不会为你带来任何愉悦，现在得不到就忍，就等，就去努力争取，人生短短几十年，爱和吃能别凑合就别凑合，你的心和胃都很小 ，别亏欠它们。";
			String s4="最近买了电子烟，使用感觉还不错。昨天出门坐公交随手就塞到牛仔裤兜里了，可能是牛仔裤太紧，压到开关了，整个车厢的人都看我胯下部位一直在冒烟，我还专心看手机根本没发觉，直到一个好心的哥们拍拍我，哥们你好像屌炸了。";
			String s5="小时候，我妈不止一次跟我说，亲嘴会怀孕，亲嘴会怀孕，结果我家猫跳起来抢肉吃的时候好死不死亲到了我的嘴，过了几个月它还生下了三只小猫……出于责任感，有我一口肉吃，我就不会让那三只小猫吃素！";
			String s6="都说女儿找了男朋友，父母会有自己辛辛苦苦种的白菜被猪拱了的伤感。可我弟弟自从找了女朋友，连家里都不回来住了，天天岳母娘给做好吃的，各种的乐不思蜀啊。老妈45°角仰望天空说：“白菜有没有拱着不知道，反正养了20多年的猪肯定是丢了。。。 ”";
			String s7="西天取经，六耳猕猴混来进来，真假美猴王只有唐僧能分辨出来。 唐僧说:“为师想吃桃子。” 两猴犹豫了一下，都变成了桃子。 突然唐僧大喊:“八戒，给我拿下那只猕猴桃！”";
			String s8="小时候我一次在家写作业，突然停电了，耶耶耶耶耶！终于不用写作业了，可以看电视了。还特二的去开电视，在黑暗的角落里我爸淡淡的说了说声：丑就够倒霉了，还这么傻！";
			String s9="我是个送快递的，有个事情我必须说说！那个住2号大院7楼的女孩纸，你一直在单子上注明：本人孕妇请上门送货。我已经给你送了整整一年的上门送货了，我就想问问你：你怀的是不是哪吒？！";
			String s10="办公室一美女刚休完产假第一天上班， 一男同事快速冲杯咖啡端到美女面前，美女受宠说: 我在哺乳期，不能喝咖啡！ 男同事：谁让你喝了，让你给加点奶。";
			String s11="一人问：如果蹦极的时候绳索断了，你马上要掉进悬崖底下，只让你说三个字，你会说什么？ 有人回答：救命阿救救我、草泥马、要死了。。。之类的。 这时一大神默默回了一句：筋斗云。。。 秒杀全场！";
			String s12="一次出去买东西，迎面看见了几年不见的同学和一女的抱一小孩，我：操什么时候结婚的，小孩都这么大了，长的真像你小子，两人脸色有点尴尬，很慌张，同学就干笑了几声，留了电话号码，我说你们忙去吧，我也有点事，同学：好的，电话联系，我刚转身走了几步就听见那女的说：姐夫现在怎么办！";
			String s13="第一次去女朋友家，吃饭期间二货女友不停的各种夸她妈妈做饭手艺怎么怎么样，我也都笑脸附和着各种菜怎么怎么好吃。 突然女友来一句：“知道我妈是怎么把我爸拴在家里的么？” 心不在焉的我张嘴就来了一句：“用狗链么？”";
			String s14="一哥们儿去考雅思，口试的时候看过题目之后习惯性的说了句，我日。 考官懂一点中文，就问什么意思。 他说这是我们中国人在遇到重大问题时，借助太阳的力量激励自己！";
			String s15="男：“大师，我失散多年的儿子不肯叫我爹怎么办？” 大师：“带他去股票交易所！” 男：“您是说让他先开阔眼界，然后自然就叫我啦？” 大师：“不，去了他自然会叫跌！";
			String s16="一次出去买东西，迎面看见了几年不见的同学和一女的抱一小孩，我：操什么时候结婚的，小孩都这么大了，长的真像你小子，两人脸色有点尴尬，很慌张，同学就干笑了几声，留了电话号码，我说你们忙去吧，我也有点事，同学：好的，电话联系，我刚转身走了几步就听见那女的说：姐夫现在怎么办！";
			String s17="妻子和丈夫吵架之后，妻子要带着儿子回娘家，可是妻子怎么和儿子说，儿子都不和妻子一起走，妻子只好自己走了，妻子走后丈夫看着儿子感动的说：“我儿子就是我儿子，到什么时候也不忘了他爹”，儿子这是说到：“爸爸，你不会做饭，妈妈不在，你就可以带我出去吃大餐了”，爸爸的一句话让儿子后悔万分，爸爸说到：“儿子，对不起，你妈没有给留饭钱啊”。";
			String s18="一位女士坐公交车，不小心公交车关门把他的右手食指给夹断了，她要起诉公交公司，要求公交公司赔偿200万元的赔偿金，律师说：“不好意思，女士，一根手指头应该是赔偿不了那么多的”，女士不乐意了，一直说必须赔偿200万，因为她知道值这个钱，律师疑惑的问到为什么，女士说：“怎么就不值了，我的那根手指是指挥我丈夫的呀”，律师一头黑线。";
			String s19="一天丈夫下班回家，妻子已经做好了饭菜，妻子说：“亲爱的你辛苦了，今天的菜你可以选择”，丈夫一听这个高兴，妻子今天对他太好了，于是丈夫问到：“那今天都是什么菜啊？”，妻子说：“西红柿炒鸡蛋”，丈夫说：“一个菜我有什么可以选择的？”，妻子这是说：“你可以选择吃或者是不吃”。";
			String s20="、一天休息，闲来无事，丈夫这是就问妻子说：“亲爱的老婆，你知道鱼为什么是哑巴吗？”，这是妻子也感到疑惑了，为什么鱼是哑巴呢？妻子回答到：“我也不知道，你说为什么是哑巴呢？”，丈夫一句话说完妻子拿着棒子追了他两条街，丈夫说：“你把脑袋潜入水中，说话试试看能不能说出话来”。";

			Collections.addAll(list,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15,s16,s17,s18,s19,s20);



			 int random2=new Random().nextInt(20)+1;
				webwxsendmsg(list.get(random2).toString(), msg.getString("FromUserName"));
			//}
			/*try {
				String s=com.alibaba.fastjson.JSONArray.toJSONString(bytes);*/
				//	for (int i1=0; i1 < 3; i1++) {
			/*	webwxsendmsg("hello，你好呀~~", msg.getString("FromUserName"));

			System.out.println("msgType"+msgType);
			System.out.println("UserName"+User.getString("UserName"));*/
		/*	}catch (Exception e){
				e.printStackTrace();
			}*/
		//	}


			/*if(msgType == 51){
				LOGGER.info("[*] 成功截获微信初始化消息");
			} else if(msgType == 1){
				if(SpecialUsers.contains(msg.getString("ToUserName"))){
					continue;
				} else if(msg.getString("FromUserName").equals(User.getString("UserName"))){
					continue;
				} else if (msg.getString("ToUserName").indexOf("@@") != -1) {
					String[] peopleContent = content.split(":<br/>");
					LOGGER.info("|" + name + "| " + peopleContent[0] + ":\n" + peopleContent[1].replace("<br/>", "\n"));
				} else {
					LOGGER.info(name + ": " + content);
					String ans = xiaodoubi(content);
					webwxsendmsg(ans, msg.getString("FromUserName"));
					LOGGER.info("自动回复 " + ans);
				}
			} else if(msgType == 3){
				webwxsendmsg("二蛋还不支持图片呢", msg.getString("FromUserName"));
			} else if(msgType == 34){
				webwxsendmsg("二蛋还不支持语音呢", msg.getString("FromUserName"));
			} else if(msgType == 42){
				LOGGER.info(name + " 给你发送了一张名片:");
				LOGGER.info("=========================");
			}*/
		}
	}
	
	private final String ITPK_API = "http://i.itpk.cn/api.php";
	
	// 这里的api_key和api_secret可以自己申请一个
	//private final String KEY = "?api_key=你的api_key&api_secret=你的api_secret";
	private final String KEY = "?api_key=95c830d81a8f4680b91af3214f09cc66&api_secret=7e64bab88df0a41b";

	private String xiaodoubi(String msg) {
		String url = ITPK_API + KEY + "&question=" + msg;
		String result = HttpRequest.get(url).body();
		return result;
	}

	private String getUserRemarkName(String id) {
		String name = "这个人物名字未知";
		for(int i=0, len=MemberList.size(); i<len; i++){
			JSONObject member = this.MemberList.getJSONObject(i);
			if(member.getString("UserName").equals(id)){
				if(StringKit.isNotBlank(member.getString("RemarkName"))){
					name = member.getString("RemarkName");
				} else {
					name = member.getString("NickName");
				}
				return name;
			}
		}
		return name;
	}
	
	public void listenMsgMode(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				LOGGER.info("[*] 进入消息监听模式 ...");
				int playWeChat = 0;
				while(true){
					
					int[] arr = syncCheck();

					LOGGER.info("[*] retcode=%s,selector=%s", arr[0], arr[1]);
					
					if(arr[0] == 1100){
//						LOGGER.info("[*] 你在手机上登出了微信，债见");
//						break;
						arr = syncCheck();
					}
					System.out.println();
					if(arr[0] == 0){
						if(arr[1] == 2){
							JSONObject data = webwxsync();
							handleMsg(data);
						} else if(arr[1] == 6){
							JSONObject data = webwxsync();
							handleMsg(data);
						} else if(arr[1] == 7){
							playWeChat += 1;
							LOGGER.info("[*] 你在手机上玩微信被我发现了 %d 次", playWeChat);
							webwxsync();
						} else if(arr[1] == 3){
						} else if(arr[1] == 0){
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					} else {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}, "listenMsgMode").start();
	}
	
	public static void main(String[] args) throws InterruptedException {
		App app = new App();
		String uuid = app.getUUID();
		if(null == uuid){
			LOGGER.info("[*] uuid获取失败");
		} else {
			LOGGER.info("[*] 获取到uuid为 [%s]", app.uuid);
			app.showQrCode();
			while(!app.waitForLogin().equals("200")){
				Thread.sleep(2000);
			}
			app.closeQrWindow();
			
			if(!app.login()){
				LOGGER.info("微信登录失败");
				return;
			}

			LOGGER.info("[*] 微信登录成功");
			
			if(!app.wxInit()){
				LOGGER.info("[*] 微信初始化失败");
				return;
			}
			
			LOGGER.info("[*] 微信初始化成功");
			
			if(!app.wxStatusNotify()){
				LOGGER.info("[*] 开启状态通知失败");
				return;
			}
			
			LOGGER.info("[*] 开启状态通知成功");
			
			if(!app.getContact()){
				LOGGER.info("[*] 获取联系人失败");
				return;
			}
			
			LOGGER.info("[*] 获取联系人成功");
			LOGGER.info("[*] 共有 %d 位联系人", app.ContactList.size());
			
			// 监听消息
			app.listenMsgMode();
			
			//mvn exec:java -Dexec.mainClass="me.biezhi.weixin.App"
		}
	}
	
}