package com.example.nhu_shukki_comp304_sec002_lab03.navigation

interface ContentType {
    sealed interface ContentType {
        object List : ContentType
        object ListAndDetail : ContentType
    }
}