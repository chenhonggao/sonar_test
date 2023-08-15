package cn.simple;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PathPatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.pattern.PathPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@Slf4j
public class SimpleController {

    @Autowired
    private Environment environment;

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping
    public String index() {
        StringBuilder sb = new StringBuilder();
        final String format = "<a href='%s'>%s</a></br>";
        List<String> urls = simpleUrls();
        Set<String> aSet = new HashSet<>();
        urls.forEach(url -> {
            String[] split = url.split(" ");
            if(split.length >= 2) {
                String a = String.format(format, split[1], split[1]);
                aSet.add(a);
            }
        });
        return aSet.stream().collect(Collectors.joining());
    }

    @GetMapping("/simple_urls")
    public List<String> simpleUrls() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
        List<String> resultList = new ArrayList<>();
        for(Map.Entry<RequestMappingInfo, HandlerMethod> handler: handlerMethods.entrySet()) {
            RequestMappingInfo mappingInfo = handler.getKey();

            String url = null;
            PathPatternsRequestCondition pathPatternsCondition = mappingInfo.getPathPatternsCondition();
            if(pathPatternsCondition != null) {
                for(PathPattern pathPattern: pathPatternsCondition.getPatterns()) {
                    url = pathPattern.getPatternString();
                }
            }
            String method = null;
            RequestMethodsRequestCondition methodsCondition = mappingInfo.getMethodsCondition();
            if(methodsCondition != null) {
                for(RequestMethod requestMethod: methodsCondition.getMethods()) {
                    method = requestMethod.toString();
                }
            }
            resultList.add(String.format("%s %s", method, url));
        }
        return resultList;
    }

    @GetMapping("/details_urls")
    public Object detailsUrls() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();
        List<Map<String, String>> resultList = new ArrayList<>();
        for(Map.Entry<RequestMappingInfo, HandlerMethod> handler: handlerMethods.entrySet()) {
            RequestMappingInfo mappingInfo = handler.getKey();
            HandlerMethod  handlerMethod = handler.getValue();
            HashMap<String, String> result = new HashMap<>();
            PathPatternsRequestCondition pathPatternsCondition = mappingInfo.getPathPatternsCondition();
            if(pathPatternsCondition != null) {
                for(PathPattern pathPattern: pathPatternsCondition.getPatterns()) {
                    result.put("url", pathPattern.getPatternString());
                }
            }
            result.put("className", handlerMethod.getMethod().getDeclaringClass().getName());
            result.put("methodName", handlerMethod.getMethod().getName());
            RequestMethodsRequestCondition methodsCondition = mappingInfo.getMethodsCondition();
            if(methodsCondition != null) {
                for(RequestMethod requestMethod: methodsCondition.getMethods()) {
                    result.put("requestMethod", requestMethod.toString());
                }
            }
            resultList.add(result);
        }
        return resultList;
    }

    @GetMapping("/echo/{word}")
    public String echo(@PathVariable(value = "word") String word) {
        return word;
    }

    @GetMapping("/value/{key}")
    public String value(@PathVariable String key) {
        String value = environment.getProperty(key, "not found");
        return "\"" +value+"\"";
    }
}
