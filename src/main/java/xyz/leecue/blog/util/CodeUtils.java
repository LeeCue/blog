package xyz.leecue.blog.util;

import com.google.code.kaptcha.Constants;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LeeCue
 * @date 2020-02-05
 */
public class CodeUtils {
    private static String getString(HttpServletRequest request,
                                    String key) {
        try {
            String result = request.getParameter(key);
            if (!StringUtils.isEmpty(result)) {
                result = result.trim();
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }

    private static boolean verifyCode(HttpServletRequest request) {
        String verifyCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String verifyCodeActual = CodeUtils.getString(request, "verifyCodeActual");
        if (verifyCodeActual == null || !verifyCodeActual.equalsIgnoreCase(verifyCode)) {
            return false;
        }
        return true;
    }
}
