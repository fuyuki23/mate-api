package team.fuyuki23.mate.adapter.in.rest.issue;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workspaces/{slug}/projects/{identifier}/issues")
@SecurityRequirement(name = "jwt")
@RequiredArgsConstructor
public class PrivateIssueRestAdapter {


}
