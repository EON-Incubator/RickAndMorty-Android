package com.example.rickandmorty.domain

data class Paginate(
    val next: Int?,
    val pages: Int?,
    val prev: Int?,
    val count: Int?,

)