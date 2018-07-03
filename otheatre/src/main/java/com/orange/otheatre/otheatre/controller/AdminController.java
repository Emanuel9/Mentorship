package com.orange.otheatre.otheatre.controller;

import com.orange.otheatre.otheatre.entities.User;
import com.orange.otheatre.otheatre.model.UserRole;
import com.orange.otheatre.otheatre.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
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

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/admin")
    public String adminPage(HttpServletRequest request, Model model) {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        String email = request.getUserPrincipal().getName();
        Optional<User> user = userRepository.findByEmail(email);
        UserRole userRole = user.get().getRole();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            model.addAttribute("principal", principal);
        }

        if (userRole.equals(UserRole.ADMIN)) {
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("userRoles", UserRole.values());
            return "adminPage";
        }

        return "redirect:/access-denied";
    }

    @RequestMapping(method = RequestMethod.PUT, value="/admin/ChangeRole")
    public String changeRole(@RequestParam(value="forEmail") String email,
                             @RequestParam(value="newRole") UserRole newRole) {
        try {
            Optional<User> optionalUser =userRepository.findByEmail(email);
            User user = optionalUser.orElseThrow(()-> new UsernameNotFoundException("Username was not found!"));
            user.setRole(newRole);
            userRepository.saveAndFlush(user);
        }catch (UsernameNotFoundException e) {

        }
        return "redirect:/";
    }

}
