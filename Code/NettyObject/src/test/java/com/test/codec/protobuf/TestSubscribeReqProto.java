package com.test.codec.protobuf;

import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.InvalidProtocolBufferException;
import com.test.codec.protobuf.SubscribeReqProto;

public class TestSubscribeReqProto {
	
	private static byte[] encode(SubscribeReqProto.SubscribeReq req){
		return req.toByteArray();
	}
	
	private static SubscribeReqProto.SubscribeReq decode(byte[] body){
		try {
			return SubscribeReqProto.SubscribeReq.parseFrom(body);
		} catch (InvalidProtocolBufferException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private  static SubscribeReqProto.SubscribeReq createSubscribeReq(){
		SubscribeReqProto.SubscribeReq.Builder builer=SubscribeReqProto.SubscribeReq.newBuilder();
		builer.setSubReqID(1);
		builer.setUserName("张三");
		builer.setProductName("netty book);
		builer.setAddress("上海市东城区");
		List<String> address=new ArrayList<String>();
		address.add("beijingshidongchengqu");
		address.add("beijingshihui");
		return builer.build();
	}
	
	public static void main(String[] arg){
		SubscribeReqProto.SubscribeReq req=createSubscribeReq();
		System.out.println("req before encode:"+req.toString());
		SubscribeReqProto.SubscribeReq req2=decode(encode(req));
		System.out.println("req after encode:"+req.toString());
		System.out.println("Assert equal--->"+req.equals(req2));
	}

}
