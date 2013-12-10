package org.schooldesk.dto.impl;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;


/**
 * A program element annotated &#64;UsedForMapping means that the element is probably deprecated
 * or it seems that it isn't used anywhere, but it should exist in code so it could be achieved
 * by third-party mapping libraries
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
@Target(value = {CONSTRUCTOR, METHOD, FIELD})
public @interface UsedForMapping {
}
