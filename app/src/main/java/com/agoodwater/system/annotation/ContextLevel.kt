package com.ljd.freewords.di.annotation

import javax.inject.Qualifier

/**
 * Created by lijiangdong on 12/20/17.
 */

enum class ContextLevel{APPLICATION,ACTIVITY}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class FreeWordsContext (
    val value: ContextLevel = ContextLevel.APPLICATION
)


