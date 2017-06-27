package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class GreetingController {

    private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

    @RequestMapping("/greeting")
    public String greeting(Model model, HttpServletRequest request) {

        logger.info("Saying hello");
        String name = (String) request.getSession().getAttribute("name");

        if (name == null)
            return "login";
        else {
            model.addAttribute("name", name);
            return "greeting";
            }
    }

    @RequestMapping(value="/greeting", method = RequestMethod.POST)
    public String whoareyou(@RequestParam(value="name", required=true) String name, Model model, HttpServletResponse response, HttpServletRequest request) {

        logger.info("Remembering "+name);
        request.getSession().setAttribute("name", name);

        model.addAttribute("name", name);
        return "greeting";

    }

    @RequestMapping(value = "/kill")
    public void kill() {
        logger.info("Forcing application exit");
        System.exit(1);
    }

}
