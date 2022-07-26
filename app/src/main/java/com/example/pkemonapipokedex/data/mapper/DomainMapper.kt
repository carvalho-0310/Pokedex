package com.example.pkemonapipokedex.data.mapper

interface DomainMapper<in T, out Model> {
    fun toDomain(from: T): Model
}