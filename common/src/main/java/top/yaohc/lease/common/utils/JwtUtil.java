package top.yaohc.lease.common.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import top.yaohc.lease.common.exception.LeaseException;
import top.yaohc.lease.common.result.ResultCodeEnum;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * ClassName: JwtUtil
 * Description:
 *
 * @Author 所谓独醉
 * @Create 2024/10/10 下午7:53
 * @Version 1.0
 */
public class JwtUtil {

    //    private static long tokenExpiration = 60 * 60 * 1000L;
    private static long tokenExpiration = 60 * 60 * 1000L;
    //    private static SecretKey tokenSignKey = Keys.hmacShaKeyFor("M0PKKI6pYGVWWfDZw90a0lTpGYX1d4AQ".getBytes());
    private static SecretKey tokenSignKey = Keys.hmacShaKeyFor("zeAxoEBayO5jyLLyklTcjCt2bATolEUT".getBytes());

    public static String createToken(Long userId, String username) {
        System.out.println("执行到这里了--");
        String token = Jwts.builder().
                setSubject("USER_INFO").
                setExpiration(new Date(System.currentTimeMillis() + tokenExpiration)).
                claim("userId", userId).
                claim("username", username).
                signWith(tokenSignKey).
                compact();

        return token;
    }

    public static void main(String[] args) {
        String token = JwtUtil.createToken(1L, "zhangsan");
        System.out.println(token);
    }

    public static Claims parseToken(String token) {

        if (token == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_LOGIN_AUTH);
        }

//        try {
//            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(tokenSignKey).build();
//            return jwtParser.parseClaimsJws(token).getBody();
//        } catch (Exception e) {
//            throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
//        }
        try {
//            JwtParser jwtParser = Jwts.parserBuilder().setSigningKey(tokenSignKey).build();
            JwtParser jwtParser = Jwts.parser().setSigningKey(tokenSignKey).build();
            return jwtParser.parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_EXPIRED);
        } catch (JwtException e) {
            throw new LeaseException(ResultCodeEnum.TOKEN_INVALID);
        }
    }
}
