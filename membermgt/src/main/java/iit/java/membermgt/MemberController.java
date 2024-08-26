package iit.java.membermgt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/")
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        model.addAttribute("member",memberService.getMemberList());
        long totalCount = memberService.countTotalMembers();
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("activeCount", memberService.countActiveMembers());
        model.addAttribute("expiredCount", memberService.countExpiredMembers());
        model.addAttribute("suspendedCount", memberService.countSuspendedMembers());
        return "home";
    }

    @RequestMapping(value = "/new")
    public String newMember(Model model) {
        Member member =new Member();
        model.addAttribute("member", member);
        return "new_member";
    }
    @PostMapping
    @RequestMapping( path = "/add")
    public String addMember(@ModelAttribute("member") Member member ){
        memberService.addMember(member);
        return "redirect:/home";
    }

    @RequestMapping("/edit/{memberID}")
    public ModelAndView editMember(@PathVariable(name ="memberID") Long memberID ){
        ModelAndView modelAndView =new ModelAndView("edit_member");
        Member member =memberService.getMember(memberID);
        modelAndView.addObject("member",member);
        return modelAndView;
    }

    @RequestMapping("/delete/{memberID}")
    public String deleteMember(@PathVariable(name ="memberID") Long memberID ){
        memberService.deleteMember(memberID);
        return "redirect:/home";
    }

    @GetMapping("/search")
    public String searchMember(@RequestParam(name = "memberID") Long memberID, Model model) {
        List<Member> member = memberService.searchMembers(memberID);
        boolean memberNotFound = member.isEmpty();
        model.addAttribute("member", member);
        model.addAttribute("memberNotFound", memberNotFound);
        long totalCount = memberService.countTotalMembers();
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("activeCount", memberService.countActiveMembers());
        model.addAttribute("expiredCount", memberService.countExpiredMembers());
        model.addAttribute("suspendedCount", memberService.countSuspendedMembers());
        return "home";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam(name = "category", defaultValue = "all") String category, ModelMap model) {
        List<Member> filteredMembers = category.equals("all") ? memberService.getMemberList() : memberService.filterMembers(category);
        model.addAttribute("member", filteredMembers);
        long totalCount = memberService.countTotalMembers();
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("activeCount", memberService.countActiveMembers());
        model.addAttribute("expiredCount", memberService.countExpiredMembers());
        model.addAttribute("suspendedCount", memberService.countSuspendedMembers());
        return "home";
    }

}
