package cn.heyuantao.devicemanagement.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author he_yu
 * 该用工具类用于生成JWT的信息
 */

@Service
public class JsonWebTokenUtil {
    private String secret = "test";

    /**
     * 生成密钥
     * @param username
     * @return
     *
     */
    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();
        return createToken(claims,username);
    }

    /**
     * 生成Token
     * @param username
     * @param claims
     * @return
     */
    public String generateToken(String username,Map<String,Object> claims){
        return createToken(claims,username);
    }

    /**
     * 用Token与用户名进行验证
     * @param token
     * @param username
     * @return
     */
    public Boolean validateToken(String token,String username){
        final String usernameInToken = extractUsername(token);
        if( (usernameInToken.equals(username))&&(!isTokenExpired(token)) ){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    public String extractUsername(String token){
        return extraClaim(token,Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extraClaim(token,Claims::getExpiration);
    }

    public <T> T extraClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private String createToken(Map<String,Object> claims, String subject){
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }
}
