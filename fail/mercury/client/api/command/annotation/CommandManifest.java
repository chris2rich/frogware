package fail.mercury.client.api.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CommandManifest {
   String label();

   String[] aliases() default {};

   String description() default "";

   String usage() default "";
}
