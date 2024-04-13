package ru.avito.avitotest.data.network.retrofit

@Target(allowedTargets = [AnnotationTarget.CLASS])
@Retention(value = AnnotationRetention.RUNTIME)
annotation class EndpointUrl(val value: String)