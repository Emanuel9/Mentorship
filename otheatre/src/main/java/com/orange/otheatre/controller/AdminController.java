package com.orange.otheatre.controller;

import com.orange.otheatre.entities.User;
import com.orange.otheatre.model.UserRole;
import com.orange.otheatre.repositories.UserRepository;
import com.orange.otheatre.service.CustomUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class AdminController {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CustomUserDetailsService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/admin")
    public String adminPage(HttpServletRequest request, Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        String email = request.getUserPrincipal().getName();
        User user = (User) userService.loadUserByUsername(email);
        UserRole userRole = user.getRole();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            model.addAttribute("principal", principal);
        }

        if (userRole.equals(UserRole.ADMIN)) {
            model.addAttribute("users", userService.findAll());
            model.addAttribute("userRoles", UserRole.values());
            return "adminPage";
        }

        return "redirect:/access-denied";
    }


    @RequestMapping(method = RequestMethod.PUT, value="/admin/ChangeRole")
    public String changeRole(@RequestParam(value="forEmail") String email,
                             @RequestParam(value="newRole") UserRole newRole) {
        try {
            User user = (User) userService.loadUserByUsername(email);
            user.setRole(newRole);
            userService.saveUser(user);
        }catch (Exception e) {
                LOGGER.error(e.getMessage());
        }
        return "redirect:/";
    }

}
