package hello.core.Scan.Filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class));
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    // ANNOTATION: 기본값, 애노테이션을 인식해서 동작한다.
    // ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다.
    // ASPECTJ: AspectJ 패턴사용
    // REGEX : 정규 표현식
    // CUSTOM: TypeFilter이라는 인터페이스를 구현해서 처리
    
    // includeFilters를 사용할 일은 거의 없다. @Component면 충분하다
    // excludeFilters는 간혹 사용한다.

    static class ComponentFilterAppConfig{

    }
}
