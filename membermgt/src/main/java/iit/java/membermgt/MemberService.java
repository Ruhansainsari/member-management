package iit.java.membermgt;

import jdk.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    MemberRepository memberRepository;
    public void addMember(Member member){
        memberRepository.save(member);
    }

    public List<Member> getMemberList(){
        return memberRepository.findAll();
    }

    public Member getMember(Long memberID) {
        return memberRepository.findById(memberID).get();
    }

    public void deleteMember(Long memberID) {
        memberRepository.deleteById(memberID);
    }

    public List<Member> searchMembers(Long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        return member != null ? Collections.singletonList(member) : Collections.emptyList();
    }

    public List<Member> filterMembers(String category) {
        List<Member> allMembers = memberRepository.findAll();

        switch (category) {
            case "Active":
                return allMembers.stream()
                        .filter(member -> "Active".equalsIgnoreCase(member.getMembStatus()))
                        .collect(Collectors.toList());
            case "Expired":
                return allMembers.stream()
                        .filter(member -> "Expired".equalsIgnoreCase(member.getMembStatus()))
                        .collect(Collectors.toList());
            case "Suspended":
                return allMembers.stream()
                        .filter(member -> "Suspended".equalsIgnoreCase(member.getMembStatus()))
                        .collect(Collectors.toList());
            case "Basic":
                return allMembers.stream()
                        .filter(member -> "Basic".equalsIgnoreCase(member.getMembType()))
                        .collect(Collectors.toList());
            case "Premium":
                return allMembers.stream()
                        .filter(member -> "Premium".equalsIgnoreCase(member.getMembType()))
                        .collect(Collectors.toList());
            default:
                return allMembers;
        }
    }

    public long countTotalMembers() {
        return memberRepository.count();
    }

    public long countActiveMembers() {
        return memberRepository.countByMembStatus("active");
    }

    public long countExpiredMembers() {
        return memberRepository.countByMembStatus("expired");
    }

    public long countSuspendedMembers() {
        return memberRepository.countByMembStatus("suspended");
    }

}
