/*
 * Copyright 2017 HomeAdvisor, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

package com.homeadvisor.kafdrop.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.core.env.*;
import org.springframework.stereotype.*;
import org.springframework.web.servlet.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.*;

import javax.servlet.http.*;

@Component
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {

  @Autowired
  private Environment environment;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new ProfileHandlerInterceptor());
  }


  public class ProfileHandlerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
      final String[] activeProfiles = environment.getActiveProfiles();
      if (modelAndView != null && activeProfiles != null && activeProfiles.length > 0) {
        modelAndView.addObject("profile", String.join(",", activeProfiles));
      }
    }
  }

}
