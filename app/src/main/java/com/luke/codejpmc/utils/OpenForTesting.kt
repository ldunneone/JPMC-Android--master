package com.luke.codejpmc.utils

@Target(AnnotationTarget.ANNOTATION_CLASS)
annotation class OpenClass

@OpenClass
@Target(AnnotationTarget.CLASS)
annotation class OpenForTesting