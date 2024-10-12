package top.yaohc.lease.web.admin.custom.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import top.yaohc.lease.common.utils.JwtUtil;

/**
 * ClassName: AuthenticationInterceptor
 * Description:
 *
 * @Author 所谓独醉
 * @Create 2024/10/12 上午8:57
 * @Version 1.0
 */
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("access-token");
//        JwtUtil.parseToken(token);
//        JwtUtil.parseToken()
        return true;
    }
}