package com.myfinances.accounts;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Sam Moore
 * @since 8/8/13 2:06 AM
 */
@Controller
@RequestMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriesEndpoint {

}
