package br.edu.utfpr.pb.labquimica.backend.security;

import br.edu.utfpr.pb.labquimica.backend.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtils {

    private final String AUDIENCE_WEB = "web";
    private final String AUDIENCE_MOBILE = "mobile";
    private final String AUDIENCE_TABLET = "tablet";

    private String secret = "PhofxuuenEa0qT2pSoBwoA==LxqIrkPvDE6Kc3ZUXP1yvQ==";

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            username = this.getClaimsFromToken(token).getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getCreatedDateFromToken(String token) {
        Date createdDate;
        try {
            createdDate = new Date((Long) this.getClaimsFromToken(token).get("created"));
        } catch (Exception e) {
            createdDate = null;
        }
        return createdDate;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expirationDate;
        try {
            expirationDate = this.getClaimsFromToken(token).getExpiration();
        } catch (Exception e) {
            expirationDate = null;
        }
        return expirationDate;
    }

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            audience = this.getClaimsFromToken(token).getAudience();
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Date generateExpirationDate() {
        var calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    private Boolean isTokenExpired(String token) {
        return this.getExpirationDateFromToken(token).before(this.generateCurrentDate());
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    public Boolean ignoreTokenExpired(String token) {
        String audience = this.getAudienceFromToken(token);
        return (this.AUDIENCE_MOBILE.equals(audience) || this.AUDIENCE_TABLET.equals(audience));
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", userDetails.getUsername());
        claims.put("audience", this.AUDIENCE_WEB);
        claims.put("created", this.generateCurrentDate());

        return this.generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(this.generateExpirationDate())
                .signWith(SignatureAlgorithm.HS256, this.secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = this.getCreatedDateFromToken(token);
        return (!this.isCreatedBeforeLastPasswordReset(created, lastPasswordReset))
                &&
                (!(this.isTokenExpired(token)) || this.ignoreTokenExpired(token));

    }

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = this.getClaimsFromToken(token);
            claims.put("created", this.generateCurrentDate());
            refreshedToken = this.generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        Usuario usuario = (Usuario) userDetails;
        final String username = this.getUsernameFromToken(token);
        final Date createdDate = this.getCreatedDateFromToken(token);

        return (username.equals(usuario.getUsername()) &&
                !(this.isTokenExpired(token)) &&
                !(this.isCreatedBeforeLastPasswordReset(createdDate, usuario.getLastPasswordReset()))
        );
    }
}
