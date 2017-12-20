package agashchuk.SystemSpecificPackage.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PostHandler extends HandlerInterceptorAdapter {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy");

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            modelAndView.getModelMap().addAttribute("today", format.format(new Date()));
        }
    }
}
