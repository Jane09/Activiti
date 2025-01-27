/*
 * Copyright 2010-2020 Alfresco Software, Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.activiti.core.common.spring.security.test;

import org.activiti.core.common.spring.security.AuthenticationPrincipalRolesProvider;
import org.activiti.core.common.spring.security.SimpleGrantedAuthoritiesResolver;
import org.activiti.core.common.spring.security.SimpleGrantedAuthoritiesRolesMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class AuthenticationPrincipalRolesProviderTest {

    private AuthenticationPrincipalRolesProvider subject;

    @BeforeEach
    public void setUp() {
        subject = new AuthenticationPrincipalRolesProvider(new SimpleGrantedAuthoritiesResolver(),
            new SimpleGrantedAuthoritiesRolesMapper());
    }

    @Test
    public void testGetRoles() {
        // given
        Authentication authentication = new UsernamePasswordAuthenticationToken("username",
            "password",
            AuthorityUtils.createAuthorityList("ROLE_user",
                "GROUP_users"));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // when
        List<String> result = subject.getRoles(authentication);

        // then
        assertThat(result).isNotEmpty()
            .containsExactly("user");
    }

}
