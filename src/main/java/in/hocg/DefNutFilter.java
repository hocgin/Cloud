package in.hocg;

import org.nutz.mvc.NutFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hocgin on 16-11-30.
 */
public class DefNutFilter extends NutFilter {

    protected Set<String> prefixs = new HashSet<String>();
    @Override
    public void init(FilterConfig conf) throws ServletException {
        super.init(conf);
        //去掉druid监控url监控
        prefixs.add(conf.getServletContext().getContextPath() + "/druid");
        prefixs.add(conf.getServletContext().getContextPath() + "/rs");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest) {
            String uri = ((HttpServletRequest) req).getRequestURI();
            for (String prefix : prefixs) {
                if (uri.startsWith(prefix)) {
                    chain.doFilter(req, resp);
                    return;
                }
            }
        }
        super.doFilter(req, resp, chain);
    }
}
