package com.sh.wxapp.jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.sh.wxapp.enm.BusinessExceptionCode;
import com.sh.wxapp.exception.BusinessException;
import net.minidev.json.JSONObject;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: carorder
 * @description:
 * @author: xuqie
 * @create: 2019-02-22 14:50
 **/
public class TokenUtils {

    /*密钥*/
    private static final byte[] secret = "hellomynameisshaohuiilovemycatanddogs".getBytes();
    public static final String EXPIRETIME = "exp";
    public static final String DATA = "data";
    public static final String USERID = "userId";
    public static final String RESULT = "result";
    public static final int TOKEN_PASS = 1;
    public static final int TOKEN_FAIL = 0;
    //过期
    public static final long TOKE_EXPIRE = 2;
    //token更新时间
    public static final long TOKEN_REFRESH_TIME = 5*60*1000;//5分钟
    //token有效时间
    public static final long TOKEN_VALID_TIME=10*60*1000;//10分钟

    /*生成token*/
    public static String createToken(Map<String, Object> payLoad) {
//3.先建立一个头部Header
        /**
         * JWSHeader参数：1.加密算法法则,2.类型，3.。。。。。。。
         * 一般只需要传入加密算法法则就可以。
         * 这里则采用HS256
         *
         * JWSAlgorithm类里面有所有的加密算法法则，直接调用。
         */
        try {
            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

            //建立一个载荷Payload
            Payload payload = new Payload(new JSONObject(payLoad));
            //将头部和载荷结合在一起
            JWSObject jwsObject = new JWSObject(jwsHeader, payload);
            //建立一个密匙
            JWSSigner jwsSigner = new MACSigner(secret);
            //签名
            jwsObject.sign(jwsSigner);
            //生成token
            return jwsObject.serialize();
        } catch (KeyLengthException kle) {
            throw new BusinessException(BusinessExceptionCode.NORMAL.getCode(), kle.getMessage());
        } catch (JOSEException je) {
            throw new BusinessException(BusinessExceptionCode.NORMAL.getCode(), je.getMessage());
        }
    }

    /*验证token*/
    public static Map<String, Object> parseToken(String token) {
        try {
//        解析token
            JWSObject jwsObject = JWSObject.parse(token);
            Payload payload = jwsObject.getPayload();
            //建立一个解锁密匙
            JWSVerifier jwsVerifier = new MACVerifier(secret);
            Map<String, Object> resultMap = new HashMap();
            //判断token
            if (jwsObject.verify(jwsVerifier)) {
                resultMap.put(RESULT, TOKEN_PASS);
                //载荷的数据解析成json对象。
                JSONObject jsonObject = payload.toJSONObject();
                resultMap.put(DATA, jsonObject);
                //验证过期
                if (jsonObject.containsKey(EXPIRETIME)) {
                    Long expTime = Long.valueOf(jsonObject.get(EXPIRETIME).toString());
                    Long currTime = new Date().getTime();
                    if (expTime < currTime) {
                        resultMap.clear();
                        resultMap.put(RESULT, TOKE_EXPIRE);
                    }
                }
            } else {
                //验证失败
                resultMap.put(RESULT, TOKEN_FAIL);
            }
            return resultMap;
        } catch (ParseException pe) {
            throw new BusinessException(BusinessExceptionCode.NORMAL.getCode(), pe.getMessage());
        } catch (JOSEException je) {
            throw new BusinessException(BusinessExceptionCode.NORMAL.getCode(), je.getMessage());
        }
    }
}
