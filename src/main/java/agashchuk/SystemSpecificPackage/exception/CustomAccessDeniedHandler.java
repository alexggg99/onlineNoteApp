package agashchuk.SystemSpecificPackage.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final Logger LOGGER = Logger.getLogger(CustomAccessDeniedHandler.class.getName());

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            LOGGER.info("user " + authentication.getName() + "tried to access protected resources " + httpServletRequest.getRequestURI());
        }
        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/errors/403_access_denied?redirectUrl=" + httpServletRequest.getRequestURI());
    }
}
