import com.taiwii.aspectdemo.aspect.CustomAspect;

@CustomAspect(description = "This is MODULE")
open module aspect.demo {
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires lombok;
    requires org.aspectj.weaver;
    requires org.slf4j;
    requires spring.context;
}