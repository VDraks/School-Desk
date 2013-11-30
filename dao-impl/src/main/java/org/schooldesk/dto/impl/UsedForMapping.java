package org.schooldesk.dto.impl;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.*;


/**
 * A program element annotated &#64;UsedForMapping means that the element is probably deprecated
 * but should exist in code so it could be achieved by third-party mapping libraries
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(value = {CONSTRUCTOR, METHOD})
public @interface UsedForMapping {
}
