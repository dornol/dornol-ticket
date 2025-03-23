//package dev.dornol.ticket.admin.api.config.data
//
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.data.web.config.EnableSpringDataWebSupport
//import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer
//
//@Configuration
//@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
//class PagingConfig{
//
//    @Bean
//    fun customizePageable(
//        @Value("\${spring.data.web.pageable.page-parameter:page}")
//        pageParameter: String,
//        @Value("\${spring.data.web.pageable.size-parameter:size}")
//        sizeParameter: String,
//    ): PageableHandlerMethodArgumentResolverCustomizer {
//        return PageableHandlerMethodArgumentResolverCustomizer { resolver ->
//            resolver.setPageParameterName(pageParameter);  // 원하는 페이지 파라미터 이름
//            resolver.setSizeParameterName(sizeParameter);     // 원하는 사이즈 파라미터 이름
//        }
//    }
//
//}