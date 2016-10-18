
package org.java8recipes.chapter02.recipe2_04;

import java.lang.annotation.Repeatable;

/**
 * Recipe 2-4:  @Repeatable
 * @author Juneau
 */
@Repeatable(value=Roles.class)
public @interface Role {
    String name() default "AUTHOR";
}
