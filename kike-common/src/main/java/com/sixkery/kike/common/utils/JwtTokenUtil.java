package com.sixkery.kike.common.utils;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.util.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Map;

/**
 * JWT 生成的工具类
 * JWT token的格式：header.payload.signature
 * <p>
 * JWT token的格式：header.payload.signature
 * header(头部)的格式（算法、token的类型）：
 * {"alg": "HS512","typ": "JWT"}
 * payload(负载)的格式（用户名、创建时间、生成时间）：
 * {"sub":"wang","created":1489079981393,"exp":1489684781}
 * signature(签名)的生成算法：
 * HMACSHA512(base64UrlEncode(header) + "." +base64UrlEncode(payload),secret)
 *
 * @author sixkery
 * @date 2020/4/16
 */

@Slf4j
public class JwtTokenUtil {

    /**
     * 签名密钥
     */
    private static final String SECRET = "qYYjXt7s1C*nEC%9RCwQGFA$YwPr$Jrj";

    /**
     * 签名算法
     */
    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    /**
     * token前缀
     */
    private static final String TOKEN_PREFIX = "Bearer ";


    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final int THIRTY_MINUTES = 30 * 60;

    @Value("${JWT.secret}")
    private String secret;

    @Value("${JWT.expiration}")
    private Long expiration;

    @Value("${JWT.tokenHead}")
    private String tokenHead;

    /**
     * 根据负载生成JWT的token
     *
     * @param claims 负载
     * @return token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从token中获取JWT的负载
     *
     * @param token token
     * @return 负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败：{}", token);
        }
        return claims;
    }

    /**
     * 生成token 的过期时间
     *
     * @return 时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从 token 中获取登陆用户名
     *
     * @param token token
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }


    /**
     * 判断token是否已经失效
     *
     * @param token token
     * @return 布尔值
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 当原来的token没有过期是可以刷新的
     *
     * @param oldToken 带token hard 的 token
     * @return 新的token
     */
    public String refreshHardToken(String oldToken) {
        if (StringUtil.isEmpty(oldToken)) {
            return null;
        }
        String token = oldToken.substring(tokenHead.length());
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        // token 中负载检验
        Claims claims = getClaimsFromToken(token);
        if (claims == null) {
            return null;
        }
        // token 过期不支持刷新
        if (isTokenExpired(token)) {
            return null;
        }
        // 判断 token 是否在30分钟内刷新过，返回原来的token
        if (tokenRefreshJustBefore(token)) {
            return token;
        } else {
            claims.put(CLAIM_KEY_CREATED, new Date());
            return generateToken(claims);
        }

    }

    private boolean tokenRefreshJustBefore(String token) {
        Claims claims = getClaimsFromToken(token);
        Date created = claims.get(CLAIM_KEY_CREATED, Date.class);
        Date refreshDate = new Date();
        return refreshDate.after(created) && refreshDate.before(DateUtil.offsetSecond(created, JwtTokenUtil.THIRTY_MINUTES));
    }

}
